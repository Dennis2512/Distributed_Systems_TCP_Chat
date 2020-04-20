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
                System.out.println("Erfolgreich abgemeldet.");
            } else {
                System.out.println(msg.getText());
            }
        } catch (IOException e) {
            try {
                System.out.println("Server connection was lost, trying to reconnect...");
                this.connection = new Socket("localhost", this.connection.getPort() == 187 ? 188 : 187);
                System.out.println("Verbunden mit Server " + (this.connection.getPort() == 187 ? 1 : 2));
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("client", "LOGOUT", "", Customtime.get()));
                this.ois = new ObjectInputStream(connection.getInputStream());
                Message msg = (Message) this.ois.readObject();
                if (msg.getType().equals("SUCCESS")) {
                    this.success = true;
                    System.out.println("Erfolgreich abgemeldet.");
                } else {
                    System.out.println(msg.getText());
                }
            } catch (Exception err) {
                System.out.println("Servers went offline.");
                System.exit(0);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean offline() {
        return this.success;
    }

    public Socket getConnection() {
        return this.connection;
    }
}
