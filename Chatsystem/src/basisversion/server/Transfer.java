package basisversion.server;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Transfer extends Thread {

    private ObjectOutputStream oos;
    private Socket connection;
    private Users users;

    public Transfer(Socket connection, Users users) {
        this.connection = connection;
        this.users = users;
    }

    public void run() {
        try {
            ArrayList<Chat> chats = this.getAllChats();
            for (int i = 0; i < chats.size(); i++) {
                Chat chat = chats.get(i);
                // send users
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "NEWCHAT", this.chatuserstring(chat), "time"));
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(chat.getChat());
            }
            // mitteilen dass init zuende ist
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(new Message("server", "DONE", "Initialized " + chats.size() + " chats.", "time"));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private String chatuserstring(Chat chat) {
        String erg = "";
        for (int i = 0; i < chat.getUsers().size(); i++) {
            erg += chat.getUsers().get(i).getKennung() + "_";
        }
        return erg;
    }

    private ArrayList<Chat> getAllChats() {
        ArrayList<Chat> chats = new ArrayList<Chat>();
        for (int i = 0; i < this.users.getUsers().size(); i++) {
            User user = this.users.getUsers().get(i);
            for (int ii = 0; ii < user.getChats().size(); ii++) {
                Chat chat = user.getChats().get(ii);
                if (!chats.contains(chat)) {
                    chats.add(chat);
                }
            }
        }
        return chats;
    }
}