package basisversion.server;

import java.util.ArrayList;

public class User {

    private String kennung;
    private String password;
    private Connection con;
    private Chat activeChat;
    private ArrayList<Chat> chats;

    public User(String kennung, String password) {
        this.kennung = kennung;
        this.password = password;
        this.chats = new ArrayList<Chat>();
    }

    // login

    public boolean login(String password, Connection con) {
        if (this.password.equals(password)) {
            this.con = con;
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        this.con = null;
        this.activeChat = null;
    }

    public Chat getChatWith(User user) {
        Chat c = null;
        int i = 0;
        while (i < this.chats.size() && c == null) {
            if (this.chats.get(i).hasUser(user)) {
                c = this.chats.get(i);
            }
            i++;
        }
        return c;
    }

    public void setActiveChat(Chat chat) {
        if (this.chats.contains(chat)) {
            this.activeChat = chat;
        }
    }

    public void addChat(Chat chat) {
        this.chats.add(chat);
    }

    public void write(Message msg) {
        this.activeChat.send(msg);
    }

    public void leaveChat() {
        this.activeChat = null;
    }

    // send to this user
    public void send(Message msg) {
        this.con.send(msg);
    }

    // getter

    public String getKennung() {
        return this.kennung;
    }

    public boolean isOnline() {
        return this.con != null;
    }

    public Chat getActiveChat() {
        return this.activeChat;
    }

    public ArrayList<Chat> getChats() {
        return this.chats;
    }
}