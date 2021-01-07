package network;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import game.Question;
import game.Quiz;
import network.Payload.Type;

public class Client implements SocketListener, Runnable {
    private SocketManager sm;
    private String user;
    private String avatar;
    private Thread thread;
    // private LinkedBlockingQueue<String> messages = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Payload> payloads = new LinkedBlockingQueue<>();
    private static final Client client = new Client();
    private String channel = "Team Violetta";

    public Client(String user) {
        this.user = user;
        start();
    }
    
    public Client() {
    }

    // public void setUser(String user) {
    //     Client cl = Client.getInstance();
    //     this.user = user;
    //     cl.sendConnection();
    //     cl.sendChannel(channel);
    // }

    public static Client getInstance() {
        return (client);
    }

    public void start() {
        try {
            // Socket socket = new Socket("135.181.151.73", 6869);
            Socket socket = new Socket("localhost", 6869);
            sm = new SocketManager(socket, this);
            thread = new Thread(sm);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // Scanner scanner = new Scanner(System.in);
        // while (scanner.hasNextLine()) {
        //     String message = scanner.nextLine();
        //     Payload payload = buildPayloadMessage(message, false, "Team Violetta");
        //     sm.send(payload);
        // }
        // scanner.close();
    }

    // private Payload buildPayloadMessage(String message, boolean isSmile, String channel) {
    //     Payload payload = new Payload(Payload.Type.ANSWER);
    //     payload.addProperty("message", message);
    //     payload.addProperty("user", user);
    //     payload.addProperty("smile", isSmile + "");
    //     payload.addProperty("channel", channel);
    //     return (payload);
    // }

 

    public void sendMessage(String message, boolean isSmile) {
        Payload payload = new Payload(Payload.Type.ANSWER);
        payload.addProperty("message", message);
        payload.addProperty("user", this.user);
        payload.addProperty("smile", isSmile + "");
        payload.addProperty("avatar", this.avatar);
        sm.send(payload);
    }

    public void sendChannel(String channel) {
        System.out.println(channel);
        Payload payload = new Payload(Type.CHANNEL);
        payload.addProperty("channel", channel);
        this.channel = channel;
        sm.send(payload);
    }

    public void sendGo() {
        Payload payload = new Payload(Payload.Type.ONGOING);
        sm.send(payload);
    }

    public void sendConnection(String user, String avatar) {
        this.user = user;
        this.avatar = avatar;
        Payload payload = new Payload(Payload.Type.CONNECTION);
        payload.addProperty("user", user);
        payload.addProperty("avatar", avatar);
        sm.send(payload);
    }

    // public void publishMessage(String message) {
    //     messages.offer(message);
    // }

    public static void main(String[] argv) {
        new gui.GUI().setVisible(true);
        Client cl = Client.getInstance();
        cl.start();
        // cl.sendMessage();
        cl.run();
    }

    @Override
    public void onDisconnection(SocketManager sm) {
        System.out.println("Server disconnected");
    }

    @Override
    public void onMessage(SocketManager sm, Payload payload) {
        switch (payload.getType()) {
            case CONNECTION:
                System.out.println(payload.getProps().get("user") + " connected.");
                break;
            case DISCONNECTION:
                System.out.println(payload.getProps().get("user") + " left.");
                break;
            case ANSWER:
                if (payload.getProps().get("smile").equals("true")) {
                    gui.Service.addSmiley(payload.getProps().get("message"), payload.getProps().get("user"), this.channel, payload.getProps().get("avatar"));
                } else {
                    System.out.println(payload.getProps().get("user") + ": " + payload.getProps().get("message"));
                    gui.Service.addMessage(payload.getProps().get("message"), payload.getProps().get("user"), this.channel, payload.getProps().get("avatar"));
                    payloads.add(payload);
                }
                break;
            case LEADERBOARD:
                System.out.println(payload.toString());
                String users[] = payload.getProps().get("users").split("\2");
                String avatars[] = payload.getProps().get("avatars").split("\2");
                String score[] = payload.getProps().get("score").split("\2");
                if (users != null && users.length != 0)
                    gui.Service.updateUsersConnected(users, score, avatars, this.channel);
                break;
            case QUESTION:
                Question question = Quiz.getInstance().get(Integer.parseInt(payload.getProps().get("id")));
                System.out.println(this.channel +  question.getMedia() + question.getQuestion());
                if (question.getType() == Question.Type.IMAGE)
                    gui.Service.addImage(this.channel, question.getMedia(), question.getQuestion());
                else
                    gui.Service.addMessage(question.getQuestion(), "BOT", this.channel, "avatar/2.png");
                break;
            case ONGOING:
                String channels[] = payload.getProps().get("blockedChannels").split("\2");
                System.out.println(payload.toString());
                break;
            case CLOSE:
                // revenir à l'écran avec channels
                System.out.println("END.");
                break;
        }
    }
}