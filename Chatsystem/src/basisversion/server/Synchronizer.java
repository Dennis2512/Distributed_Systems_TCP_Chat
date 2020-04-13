package basisversion.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Synchronizer extends Thread {

    private Socket connection;
    private ObjectInputStream ois;
    private Users users;

    public Synchronizer(Socket connection, Users users) {
        this.connection = connection;
        this.users = users;
    }

    public void run() {
        try {
            while (true) {
                this.ois = new ObjectInputStream(this.connection.getInputStream());
                Message msg = (Message) this.ois.readObject();
                switch (msg.getType()) {
                    case "MSG":
                        this.message(msg);
                        break;
                    case "CONNECT":
                        this.connect(msg);
                        break;
                    default:
                        System.out.println(msg.getText());
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    private void message(Message msg) {
        User user = this.users.getUser(msg.getSender());
        if (user != null && user.getActiveChat() != null) {
            user.write(msg);
        }
    }

    private void connect(Message msg) {
        User user = this.users.getUser(msg.getSender());
        User receiver = this.users.getUser(msg.getText());
        Chat chat = user.getChatWith(receiver);
        if (chat == null) {
            // neuen chat erstellen
            chat = new Chat(new ArrayList<User>(Arrays.asList(user, receiver)));
            user.addChat(chat);
            receiver.addChat(chat);
            user.setActiveChat(chat);
        } else {
            user.setActiveChat(chat);
        }
    }

}// class