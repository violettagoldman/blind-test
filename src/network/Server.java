package src.network;

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

    public Server() {
        sockets = new ArrayList<SocketManager>();
        activeUsers = new HashMap<SocketManager, User>();
        this.pool = Executors.newCachedThreadPool();
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
        System.out.println(activeUsers.size() + " users");
        for (SocketManager sm : activeUsers.keySet()) {
            System.out.println("ch: " + activeUsers.get(sm).getChannel());
            if (activeUsers.get(sm) != null &&
                activeUsers.get(sm).getChannel().equals(channel)) {
                System.out.println("Here bc");
                users += activeUsers.get(sm).getName() + "\2";
                score += activeUsers.get(sm).getScore() + "\2";
            }
        }
        payload.addProperty("users", users);  
        payload.addProperty("score", score); 
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
        broadcast(payload);
        System.out.println(payload.toString());
        if (payload.getType() == Payload.Type.CONNECTION) {
            activeUsers.put(sm, new User(payload.getProps().get("user"),
                payload.getProps().get("avatar")));
        }
        if (payload.getType() == Payload.Type.CHANNEL && activeUsers.get(sm) != null) {
            System.out.println(activeUsers.get(sm).getName() + " is in " + payload.getProps().get("channel"));
            activeUsers.get(sm).setChannel(payload.getProps().get("channel"));
            broadcastLeaderboard(payload.getProps().get("channel"));
        }
    }
}