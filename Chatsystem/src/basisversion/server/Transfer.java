package basisversion.server;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Transfer extends Thread {

    private ObjectOutputStream oos;
    private Socket connection;

    public Transfer(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            // hier alle daten übertragen
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(new Message("server", "LOAD", "Implement Chats here.", "time"));
            // mitteilen dass init zuende ist
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(new Message("server", "DONE", "Done.", "time"));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}