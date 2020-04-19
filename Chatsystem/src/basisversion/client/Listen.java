package basisversion.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import basisversion.server.Message;

public class Listen extends Thread {

    private Socket connection;
    private Chatsession chatsession;
    private boolean end;

    public Listen(Socket connection, Chatsession chatsession) {
        this.connection = connection;
        this.chatsession = chatsession;
        this.end = false;
    }

    public void run() {
        try {
            while (!this.end) {
                ObjectInputStream ois = new ObjectInputStream(this.connection.getInputStream());
                Message msg = (Message) ois.readObject();
                if (msg.getType().equals("MSG")) {
                    this.chatsession.printMsg(msg);
                } else if (msg.getType().equals("SENT")) {
                    this.chatsession.sent(msg.getText());
                } else if (msg.getType().equals("LEFT")) {
                    this.end = true;
                } else {
                    System.out.println(msg.getText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.chatsession.onConnLost();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}