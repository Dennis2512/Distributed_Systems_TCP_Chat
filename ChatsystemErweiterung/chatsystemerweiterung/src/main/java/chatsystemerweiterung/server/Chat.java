package chatsystemerweiterung.server;

import java.util.ArrayList;
import java.util.Collections;

public class Chat {

    private ArrayList<User> users;
    private ArrayList<Message> chat;

    public Chat(ArrayList<User> users) {
        this.users = users;
        this.chat = new ArrayList<Message>();
    }

    public void send(Message msg) {
        this.chat.add(msg);
        // nachricht an die richtige position bringen
        Collections.sort(this.chat);
        // allen usern die diesen chat offen haben und online sind die aktuelle
        // nachricht schicken
        for (int i = 0; i < this.users.size(); i++) {
            User tmp = this.users.get(i);
            if (tmp.getActiveChat() == this && !tmp.getKennung().equals(msg.getSender()) && tmp.isOnline()) {
                tmp.send(msg);
            }
        }
    }

    // getter

    public ArrayList<Message> getChat() {
        return this.chat;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public boolean hasUser(User user) {
        boolean gefunden = false;
        int i = 0;
        while (i < this.users.size() && !gefunden) {
            if (this.users.get(i) == user) {
                gefunden = true;
            }
            i++;
        }
        return gefunden;
    }

    public void setChat(ArrayList<Message> chat) {
        this.chat = chat;
    }

    // gibt user der nicht der anfragende ist aus
    public User getChatPartner(User user) {
        User u = null;
        int i = 0;
        while (u == null && i < this.users.size()) {
            if (this.users.get(i) != user) {
                u = this.users.get(i);
            }
            i++;
        }
        return u;
    }
}