package chatsystemerweiterung.GUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import chatsystemerweiterung.rsa.Security;
import chatsystemerweiterung.server.Message;

public class Listen extends Thread {

    private Socket connection;
    private boolean end;
    private Message message;
    ChatFenster cFenster;
    private Security security;

    public Listen(Socket connection, ChatFenster cFenster) {
        this.connection = connection;
        this.cFenster = cFenster;
        this.end = false;
        this.security = new Security();
    }

    public void run() {
        try {
            while (!this.end) {
                ObjectInputStream ois = new ObjectInputStream(this.connection.getInputStream());
                Message msg = security.decryptMessage((Message) ois.readObject());
                if (msg.getType().equals("MSG")) {
                    cFenster.printMsg(msg);
                } else if (msg.getType().equals("SENT")) {
                    cFenster.sent(msg);
                    // this.chatsession.sent(msg.getText());
                } else if (msg.getType().equals("LEFT")) {
                    this.cFenster.onLeft();
                    this.end = true;
                } else if (msg.getType().equals("LOGOUT")) {
                    this.end = true;
                    this.cFenster.onLogout();
                    System.out.println(msg.getText());
                    this.connection.close();
                } else {
                    System.out.println(msg.getText());
                }
            }
        } catch (IOException e) {
            System.out.println("Verbindung zum Server getrennt...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Message getMessage() {
        return message;
    }
}