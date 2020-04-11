package basisversion.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Init extends Thread {

    private Socket connection;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean done;

    public Init(Socket connection) {
        this.connection = connection;
        this.done = false;
    }

    public void run() {
        try {
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(new Message("server", "SERVER", "text", "time"));
            while (!done) {
                this.ois = new ObjectInputStream(this.connection.getInputStream());
                Message msg = (Message) this.ois.readObject();
                System.out.println(msg.getText());
                if (msg.getType().equals("DONE")) {
                    this.done = true;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

} // class