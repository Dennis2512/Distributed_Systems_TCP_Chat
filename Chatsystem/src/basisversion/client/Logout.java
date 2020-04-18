package basisversion.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import basisversion.server.Customtime;
import basisversion.server.Message;

public class Logout extends Thread {

    private Socket connection;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean success = false;

    public Logout(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(new Message("client", "LOGOUT", "", Customtime.get()));
            this.ois = new ObjectInputStream(connection.getInputStream());
            Message msg = (Message) this.ois.readObject();
            if (msg.getType().equals("SUCCESS")) {
                this.success = true;
            } else {
                System.out.println(msg.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean offline() {
        return this.success;
    }
}