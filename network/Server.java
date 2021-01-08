package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors;
import java.util.Timer;
import java.util.TimerTask;

public class Server implements Runnable, SocketListener {
    private final List<SocketManager> sockets;
    private final ExecutorService pool;
    private final Map<SocketManager, User> activeUsers;
    private final Map<String, Integer> questions;
    private final Map<String, Timer> timers;

    final int POINTS = 3;
    final int SECONDSCLOSE = 10;
    final int SECONDSTIMEOUT = 3;

    public Server() {
        this.sockets = new ArrayList<SocketManager>();
        this.activeUsers = new HashMap<SocketManager, User>();
        this.pool = Executors.newCachedThreadPool();
        this.questions = new HashMap<String, Integer>();
        this.timers = new HashMap<String, Timer>();
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(6869)) {
            while (!serverSocket.isClosed()) {
                SocketManager tmp = new SocketManager(serverSocket.accept(), this);
                sockets.add(tmp);
                pool.execute(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            pool.shutdown();
        }
    }

    public void broadcast(Payload payload) {
        for (SocketManager sm : sockets) {
            sm.send(payload);
        }
    }

    public void broadcastChannel(Payload payload, String channel) {
        for (SocketManager sm : activeUsers.keySet()) {
            System.out.println("sm: " + sm);
            System.out.println(activeUsers.get(sm));
            if (activeUsers.get(sm).getChannel().equals(channel))
                sm.send(payload);
        }
    }

    public void broadcastLeaderboard(String channel) {
        Payload payload = new Payload(Payload.Type.LEADERBOARD);
        String users = "";
        String score = "";
        String avatars = "";
        for (SocketManager sm : activeUsers.keySet()) {
            if (activeUsers.get(sm) != null &&
                activeUsers.get(sm).getChannel().equals(channel)) {
                users += activeUsers.get(sm).getName() + "\2";
                score += activeUsers.get(sm).getScore() + "\2";
                avatars += activeUsers.get(sm).getAvatar() + "\2";
            }
        }
        payload.addProperty("users", users);  
        payload.addProperty("score", score); 
        payload.addProperty("avatars", avatars);
        broadcastChannel(payload, channel);
    }

    public void chooseQuestion(String channel) {
        timers.put(channel, new Timer());
        timers.get(channel).schedule(new Task(channel), SECONDSTIMEOUT * 1000);
        questions.put(channel, game.Quiz.getInstance().randomQuestionId(channel));
        Payload payload = new Payload(Payload.Type.QUESTION);
        payload.addProperty("id", questions.get(channel) + "");
        broadcastChannel(payload, channel);
    }

    public void timesUp(String channel) {
        sendMessage("Time is up!", channel);
        chooseQuestion(channel);
    }

    class Task extends TimerTask {
        private String channel;

        public Task(String channel) {
            this.channel = channel;
        }

        public void run() {
            timers.get(channel).cancel();
            timesUp(channel);
        }
    }

    public void sendMessage(String message, String channel) {
        Payload payload = new Payload(Payload.Type.ANSWER);
        payload.addProperty("message", message);
        payload.addProperty("user", "BOT");
        payload.addProperty("smile", false + "");
        payload.addProperty("avatar", "../game/assets/images/bot.png");
        broadcastChannel(payload, channel);
    }

    public void sendOngoing(SocketManager sm) { // blocked channels
        Payload payload = new Payload(Payload.Type.ONGOING);
        String channels = "";
        for (String channel : questions.keySet()) {
            if (questions.get(channel) != -1)
                channels += channel + "\2";
        }
        payload.addProperty("blockedChannels", channels);
        sm.send(payload);
    }

    public void sendOngoing() { // blocked channels
        Payload payload = new Payload(Payload.Type.ONGOING);
        String channels = "";
        for (String channel : questions.keySet()) {
            if (questions.get(channel) != -1)
                channels += channel + "\2";
        }
        payload.addProperty("blockedChannels", channels);
        broadcast(payload);
    }

    public void closeChannel(String channel) {
        Payload payload = new Payload(Payload.Type.CLOSE);
        broadcastChannel(payload, channel);
        questions.remove(channel);
        timers.remove(channel);
        sendOngoing();
    }

    public void endGame(String channel, User u) {
        String msg = u.getName() + " has won! You have one minute to leave the room. See you!";
        sendMessage(msg, channel);
        questions.put(channel, -1);
        timers.get(channel).cancel();
        timers.put(channel, new Timer());
        timers.get(channel).schedule(new CloseTask(channel), SECONDSCLOSE * 1000);
        Payload payload = new Payload(Payload.Type.QUIT);
        broadcastChannel(payload, channel);
    }

    class CloseTask extends TimerTask {
        private String channel;

        public CloseTask(String channel) {
            this.channel = channel;
        }

        public void run() {
            timers.get(channel).cancel();
            closeChannel(channel);
        }
    }

    public static void main(String[] argv) {
        Server server = new Server();
        server.run();
    }

    @Override
    public void onDisconnection(SocketManager sm) {
        sockets.remove(sm);
        broadcastLeaderboard(activeUsers.get(sm).getChannel());
        activeUsers.remove(sm);
        Payload payload = new Payload(Payload.Type.DISCONNECTION);
        broadcast(payload);
    }

    @Override
    public void onMessage(SocketManager sm, Payload payload) {
        if (payload.getType() == Payload.Type.CONNECTION ||
            payload.getType() == Payload.Type.DISCONNECTION)
            broadcast(payload);
        System.out.println(payload.toString());
        if (payload.getType() == Payload.Type.CONNECTION) {
            activeUsers.put(sm, new User(payload.getProps().get("user"),
                payload.getProps().get("avatar")));
            System.out.println("New sm: " + sm);
            sendOngoing(sm);
        }
        if (payload.getType() == Payload.Type.CHANNEL && activeUsers.get(sm) != null) {
            activeUsers.get(sm).setChannel(payload.getProps().get("channel"));
            questions.put(payload.getProps().get("channel"), -1);
            broadcastLeaderboard(payload.getProps().get("channel"));
        }
        if (payload.getType() == Payload.Type.ANSWER) {
            // checker la réponse
            broadcastChannel(payload, activeUsers.get(sm).getChannel());
            if (questions.get(activeUsers.get(sm).getChannel()) != -1) // check if the game has started
            {
                if (payload.getProps().get("message").length() == 2) {
                    timers.get(activeUsers.get(sm).getChannel()).cancel();
                    sendMessage(activeUsers.get(sm).getName() + " has the answer!", activeUsers.get(sm).getChannel());
                    activeUsers.get(sm).incrementScore();
                    broadcastLeaderboard(activeUsers.get(sm).getChannel());
                    for (SocketManager m : activeUsers.keySet()) {
                        if (activeUsers.get(m).getScore() == POINTS && activeUsers.get(m).getChannel() == activeUsers.get(sm).getChannel()) {
                            endGame(activeUsers.get(sm).getChannel(), activeUsers.get(m));
                            return ;
                        }

                    }
                    chooseQuestion(activeUsers.get(sm).getChannel());
                }
                else
                    sendMessage(activeUsers.get(sm).getName() + " try again!", activeUsers.get(sm).getChannel());
            }
            
        }
        if (payload.getType() == Payload.Type.ONGOING) {
            chooseQuestion(activeUsers.get(sm).getChannel());
            Payload go = new Payload("GO");
            broadcastChannel(go, activeUsers.get(sm).getChannel());

        }
    }
}