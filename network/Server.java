package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 

public class Server implements Runnable, SocketListener {
    private final List<SocketManager> sockets;
    private final ExecutorService pool;
    private final Map<SocketManager, User> activeUsers;
    private final Map<String, Integer> questions;

    public Server() {
        this.sockets = new ArrayList<SocketManager>();
        this.activeUsers = new HashMap<SocketManager, User>();
        this.pool = Executors.newCachedThreadPool();
        this.questions = new HashMap<String, Integer>();
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
        for (SocketManager sm : sockets) {
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
        questions.put(channel, game.Quiz.getInstance().randomQuestionId());
        Payload payload = new Payload(Payload.Type.QUESTION);
        payload.addProperty("id", questions.get(channel) + "");
        broadcastChannel(payload, channel);
    }

    public void sendMessage(String message, String channel) {
        Payload payload = new Payload(Payload.Type.ANSWER);
        payload.addProperty("message", message);
        payload.addProperty("user", "BOT");
        payload.addProperty("smile", false + "");
        payload.addProperty("avatar", "avatar/2.png");
        broadcastChannel(payload, channel);
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
        }
        if (payload.getType() == Payload.Type.CHANNEL && activeUsers.get(sm) != null) {
            System.out.println(activeUsers.get(sm).getName() + " is in " + payload.getProps().get("channel"));
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
                    sendMessage(activeUsers.get(sm).getName() + " has the answer!", activeUsers.get(sm).getChannel());
                    chooseQuestion(activeUsers.get(sm).getChannel());
                }
                else
                    sendMessage(activeUsers.get(sm).getName() + " try again!", activeUsers.get(sm).getChannel());
            }
            
        }
        if (payload.getType() == Payload.Type.ONGOING) {
            chooseQuestion(activeUsers.get(sm).getChannel());
        }
    }
}