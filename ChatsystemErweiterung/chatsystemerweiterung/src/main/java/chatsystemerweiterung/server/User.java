package chatsystemerweiterung.server;

import java.util.ArrayList;

public class User {

    private String kennung;
    private String password;
    private Connection con;
    private Chat activeChat;
    private ArrayList<Chat> chats;
    private boolean online;

    public User(String kennung, String password) {
        this.kennung = kennung;
        this.password = password;
        this.chats = new ArrayList<Chat>();
        this.online = false;
    }

    // login

    public boolean login(String password, Connection con) {
        if (this.password.equals(password)) {
            this.con = con;
            this.online = true;
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        this.con = null;
        this.activeChat = null;
        this.online = false;
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

    public Chat getChatWith(ArrayList<User> userlist) {
        Chat c = null;
        int i = 0;
        while (i < this.chats.size() && c == null) {
            ArrayList<User> tmp = this.chats.get(i).getUsers();
            if (tmp.size() == userlist.size()) {
                boolean same = true;
                for (int ii = 0; ii < tmp.size(); ii++) {
                    User u = tmp.get(ii);
                    if (!userlist.contains(u)) {
                        same = false;
                    }
                }
                if (same) {
                    c = this.chats.get(i);
                }
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
        if (this.con != null) {
            this.con.send(msg);
        }
    }

    // getter

    public String getKennung() {
        return this.kennung;
    }

    public boolean isOnline() {
        return this.online;
    }

    public Chat getActiveChat() {
        return this.activeChat;
    }

    public ArrayList<Chat> getChats() {
        return this.chats;
    }

    public void serverlogin(Connection con) {
        if (con.serverconnection) {
            this.online = true;
        }
    }

    public ArrayList<ArrayList<String>> getChatOverview() {
        ArrayList<ArrayList<String>> chatlist = new ArrayList<>();
        for (int i = 0; i < this.chats.size(); i++) {
            Chat c = this.chats.get(i);
            chatlist.add(this.toChatnames(c));
        }
        return chatlist;
    }

    private ArrayList<String> toChatnames(Chat chat) {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < chat.getUsers().size(); i++) {
            User u = chat.getUsers().get(i);
            if (u != this) {
                names.add(u.getKennung());
            }
        }
        return names;
    }
}