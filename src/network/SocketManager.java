package network;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Runnable;

public class SocketManager implements Runnable {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private SocketListener listener;


    public SocketManager(Socket socket, SocketListener listener) {
        this.listener = listener;
        try {
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public void send(Payload payload) {
        try {
            oos.writeObject(payload.toString());
        } catch (IOException e) {
            System.out.println("OIS error send");
        }
    }

    public void run() {
        try {
            while (true) {
                String message = (String)ois.readObject();
                this.listener.onMessage(this, new Payload(message));
            }
        }
        catch (IOException e) {
            this.listener.onDisconnection(this);
        } catch (ClassNotFoundException e) {
                System.out.println("CNFE error");
        }
    }
}