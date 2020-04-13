package basisversion.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

public class Init extends Thread {

    private Socket connection;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private boolean done;
    private Users users;

    public Init(Socket connection, Users users) {
        this.connection = connection;
        this.done = false;
        this.users = users;
    }

    @SuppressWarnings("unchecked")
    public void run() {
        try {
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(new Message("server", "SERVER", "text", "time"));
            while (!done) {
                this.ois = new ObjectInputStream(this.connection.getInputStream());
                Message msg = (Message) this.ois.readObject();
                if (msg.getType().equals("DONE")) {
                    this.done = true;
                    System.out.println(msg.getText());
                } else if (msg.getType().equals("NEWCHAT")) {
                    this.ois = new ObjectInputStream(this.connection.getInputStream());
                    ArrayList<Message> chat = (ArrayList<Message>) this.ois.readObject();
                    this.addChattoUsers(createChat(msg.getText(), chat));
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void addChattoUsers(Chat chat) {
        for (int i = 0; i < chat.getUsers().size(); i++) {
            User user = chat.getUsers().get(i);
            user.addChat(chat);
        }
    }

    private Chat createChat(String userstrings, ArrayList<Message> chat) {
        Chat c = new Chat(stringtouserarray(userstrings));
        c.setChat(chat);
        return c;
    }

    private ArrayList<User> stringtouserarray(String userstrings) {
        if (userstrings == null)
            return null;
        else if (userstrings.length() == 0)
            return null;
        else {
            ArrayList<User> userlist = new ArrayList<User>();
            while (userstrings.length() != 0) {
                // kennung holen, nach user suchen und mit zu den usern hinzufügen
                String akt = userstrings.substring(0, userstrings.indexOf('_'));
                User tmp = this.users.getUser(akt);
                if (tmp != null) {
                    userlist.add(tmp);
                }
                // string update
                userstrings = userstrings.substring(userstrings.indexOf('_') + 1);
            }
            return userlist;
        }
    }

} // class