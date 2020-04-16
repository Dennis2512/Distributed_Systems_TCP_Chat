package chatsystemerweiterung.GUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import chatsystemerweiterung.server.Message;

public class Listen extends Thread {

    private Socket connection;
    private boolean end;
    private Message message;
    ChatFenster cFenster;

    public Listen(Socket connection, ChatFenster cFenster) {
        this.connection = connection;
        this.cFenster = cFenster;
        this.end = false;
    }

    public void run() {
        try {
            while (!this.end) {
                ObjectInputStream ois = new ObjectInputStream(this.connection.getInputStream());
                Message msg = (Message) ois.readObject();
                if (msg.getType().equals("MSG")) {
                    cFenster.printMsg(msg);
                } else if (msg.getType().equals("SENT")) {
                    cFenster.sent(msg);
                    // this.chatsession.sent(msg.getText());
                } else if (msg.getType().equals("LEFT")) {
                    this.end = true;
                } else {
                    System.out.println(msg.getText());
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    public Message getMessage() {
        return message;
    }
}