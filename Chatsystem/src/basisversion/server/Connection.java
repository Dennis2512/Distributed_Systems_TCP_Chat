package basisversion.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Connection extends Thread {

    private User user;
    private Users users;
    private Socket connection;
    private ArrayList<Connection> connections;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public boolean serverconnection;

    public Connection(Socket connection, ArrayList<Connection> connections, Users users, boolean serverconnection)
            throws IOException {
        this.connection = connection;
        this.connections = connections;
        this.users = users;
        this.serverconnection = serverconnection;
    }

    public void run() {
        try {

            while (true) {
                this.ois = new ObjectInputStream(this.connection.getInputStream());
                Message msg = (Message) ois.readObject();
                switch (msg.getType()) {
                    case "LOGIN":
                        if (this.serverconnection) {
                            this.serverlogin(msg);
                        } else {
                            this.login(msg);
                        }
                        break;
                    case "REGISTER":
                        if (this.serverconnection) {
                            this.serverregister(msg);
                        } else {
                            this.register(msg);
                        }
                        break;
                    case "LOGOUT":
                        if (this.serverconnection) {
                            this.serverlogout(msg);
                        } else {
                            this.logout();
                        }
                        break;
                    case "CONNECT":
                        if (this.serverconnection) {
                            this.serverconnect(msg);
                        } else {
                            this.connect(msg);
                        }
                        break;
                    case "MSG":
                        if (this.serverconnection) {
                            this.servermessage(msg);
                        } else {
                            this.message(msg);
                        }
                        break;
                    case "LEAVE":
                        if (this.serverconnection) {
                            this.serverleave(msg);
                        } else {
                            this.leave();
                        }
                        break;
                    case "SERVERINIT":
                        this.serverinit();
                        break;
                    case "INIT":
                        this.init();
                        break;
                    case "DISCONNECT":
                        this.disconnect(msg);
                        break;
                    default:
                        System.out.println("Unexpected input.");
                }
            }

        } catch (IOException e) {
            if (this.serverconnection) {
                this.connections.remove(this);
                System.out.println("Verbindung zum Server wurde verloren.");
            } else {
                if (this.user != null) {
                    this.sync(new Message(this.user.getKennung(), "DISCONNECT", "", new Date()));
                    System.out.println("Verbindung zu " + this.user.getKennung() + " wurde getrennt.");
                    this.user.logout();
                    this.connections.remove(this);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void login(Message msg) {
        try {
            // user mit kennung suchen
            User tmp = this.users.getUser(msg.getSender());
            if (tmp == null) { // prüfen ob user gefunden wurde
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "error", "Nutzer existiert nicht", Customtime.get()));
            } else if (tmp.isOnline()) { // prüfen ob nutzer bereits angemeldet ist
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(
                        new Message("server", "error", "Nutzer ist bereits angemeldet.", Customtime.get()));
            } else {
                // prüfen ob passwort korrekt war
                if (tmp.login(msg.getText(), this)) {
                    // aktuellen nutzer dieser verbindung setzen
                    this.sync(msg);
                    this.user = tmp;
                    this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                    this.oos.writeObject(new Message("server", "SUCCESS", "Angemeldet.", Customtime.get()));
                    System.out.println(msg.getSender() + " hat sich angemeldet.");
                    // alle bisherigen chats des angemeldeten nutzers mitsenden
                    this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                    this.oos.writeObject(this.user.getChatOverview());
                } else { // falsches passwort
                    this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                    this.oos.writeObject(new Message("server", "error", "Falsches Passwort", Customtime.get()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void register(Message msg) {
        try {
            // prüfen ob nutzer bereits existiert
            if (this.users.getUser(msg.getSender()) != null) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "error", "Nutzer existiert bereits.", Customtime.get()));
            } else {
                if (this.users.register(msg.getSender(), msg.getText())) { // registrierung erfolgreich
                    this.sync(msg);
                    // danach wird der nutzer direkt eingeloggt
                    this.user = this.users.getUser(msg.getSender());
                    this.user.login(msg.getText(), this);
                    this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                    this.oos.writeObject(
                            new Message("server", "SUCCESS", "Registriert und angemeldet.", Customtime.get()));
                    System.out.println(msg.getSender() + " hat sich registriert und angemeldet.");
                } else { // fehlschlag
                    this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                    this.oos.writeObject(
                            new Message("server", "error", "Registrierung fehlgeschlagen", Customtime.get()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void logout() {
        try {
            if (this.user != null) { // prüfen ob nutzer angemeldet ist
                this.sync(new Message(this.user.getKennung(), "LOGOUT", "", Customtime.get()));
                System.out.println(this.user.getKennung() + " hat sich abgemeldet.");
                // logout
                this.user.logout();
                this.user = null;
                this.connections.remove(this);
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "SUCCESS", "Abgemeldet.", Customtime.get()));
                // verbindungen schließen
                this.oos.close();
                this.ois.close();
                this.connection.close();
            } else { // nicht angemeldet
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(
                        new Message("server", "error", "Abmelden geht nur für angemeldete Nutzer", Customtime.get()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect(Message msg) {
        try {
            // prüfe ob es nutzer gibt
            User receiver = this.users.getUser(msg.getText());
            if (receiver == null) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(
                        new Message("server", "error", "Nutzer konnte nicht gefunden werden.", Customtime.get()));
            } else if (this.user == receiver) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(
                        new Message("server", "error", "Chatten mit sich selber nicht möglich.", Customtime.get()));
            } else if (this.user.getActiveChat() != null) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(
                        new Message("server", "error", "Erst bisherigen Chat verlassen.", Customtime.get()));
            } else {
                this.sync(msg);
                Chat c = this.user.getChatWith(receiver);
                if (c == null) {
                    c = new Chat(new ArrayList<User>(Arrays.asList(this.user, receiver)));
                    this.user.addChat(c);
                    this.user.setActiveChat(c);
                    receiver.addChat(c);
                    this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                    this.oos.writeObject(
                            new Message("server", "NEWCHAT", "Es wurde ein neuer Chat begonnen.", Customtime.get()));
                } else {
                    this.user.setActiveChat(c);
                    this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                    this.oos.writeObject(
                            new Message("server", "LOADCHAT", "Chatverlauf wird geladen", Customtime.get()));
                    this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                    this.oos.writeObject(c.getChat());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void message(Message msg) {
        try {
            if (this.user == null) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "error", "Nicht angemeldet.", Customtime.get()));
            } else if (this.user.getActiveChat() == null) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "error", "Chat nicht gefunden.", Customtime.get()));
            } else {
                Date time = Customtime.get();
                msg.setTime(time);
                this.user.write(msg);
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "SENT", time + " " + msg.getSender() + ": " + msg.getText(),
                        Customtime.get()));
                this.sync(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leave() {
        try {
            if (this.user == null) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "error", "Nicht angemeldet.", Customtime.get()));
            } else if (this.user.getActiveChat() == null) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "error", "Kein aktiver Chat gefunden.", Customtime.get()));
            } else {
                this.sync(new Message(this.user.getKennung(), "LEAVE", "", Customtime.get()));
                this.user.leaveChat();
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "LEFT", "Chat verlassen.", Customtime.get()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Message msg) {
        try {
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection getServerconnection() {
        Connection c = null;
        int i = 0;
        while (i < this.connections.size() && c == null) {
            if (this.connections.get(i).serverconnection) {
                c = this.connections.get(i);
            }
            i++;
        }
        return c;
    }

    // server funktionen

    // syncing data when something happens

    private void sync(Message msg) {
        Connection c = this.getServerconnection();
        if (c != null) {
            c.send(msg);
        }
    }

    private void serverlogin(Message msg) {
        this.users.getUser(msg.getSender()).login(msg.getText(), null);
        System.out.println(msg.getSender() + " hat sich angemeldet.");
    }

    private void serverregister(Message msg) {
        this.users.adduser(new User(msg.getSender(), msg.getText()));
    }

    private void serverconnect(Message msg) {
        User user = this.users.getUser(msg.getSender());
        User receiver = this.users.getUser(msg.getText());
        if (user != null && receiver != null) {
            Chat c = user.getChatWith(receiver);
            if (c != null) {
                user.setActiveChat(c);
            } else {
                c = new Chat(new ArrayList<User>(Arrays.asList(user, receiver)));
                user.addChat(c);
                receiver.addChat(c);
                user.setActiveChat(c);
            }
        }
    }

    private void servermessage(Message msg) {
        User user = this.users.getUser(msg.getSender());
        user.write(msg);
    }

    private void serverleave(Message msg) {
        User user = this.users.getUser(msg.getSender());
        user.leaveChat();
    }

    private void serverlogout(Message msg) {
        User user = this.users.getUser(msg.getSender());
        user.logout();
        System.out.println(user.getKennung() + " hat sich abgemeldet.");
    }

    private void disconnect(Message msg) {
        User user = this.users.getUser(msg.getSender());
        user.logout();
        System.out.println("Verbindung zu " + user.getKennung() + " wurde getrennt.");
    }
    // init

    @SuppressWarnings("unchecked")
    private void init() {
        try {
            boolean done = false;
            while (!done) {
                this.ois = new ObjectInputStream(this.connection.getInputStream());
                Message msg = (Message) this.ois.readObject();
                if (msg.getType().equals("DONE")) {
                    done = true;
                    System.out.println(msg.getText());
                } else if (msg.getType().equals("NEWCHAT")) {
                    this.ois = new ObjectInputStream(this.connection.getInputStream());
                    ArrayList<Message> chat = (ArrayList<Message>) this.ois.readObject();
                    this.addChattoUsers(createChat(msg.getText(), chat));
                } else if (msg.getType().equals("ONLINE")) {
                    User user = this.users.getUser(msg.getSender());
                    user.serverlogin(this);
                    if (!msg.getText().equals("null")) {
                        User akt = this.users.getUser(msg.getText());
                        user.setActiveChat(user.getChatWith(akt));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void serverinit() {
        try {
            this.serverconnection = true;
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(new Message("server", "INIT", "Starting init...", Customtime.get()));
            // chats senden
            ArrayList<Chat> chats = this.getAllChats();
            for (int i = 0; i < chats.size(); i++) {
                Chat chat = chats.get(i);
                // send users
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(new Message("server", "NEWCHAT", this.chatuserstring(chat), Customtime.get()));
                // send messages
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                this.oos.writeObject(chat.getChat());
            }
            // user status etc anzeigen
            for (int i = 0; i < this.users.getUsers().size(); i++) {
                User u = this.users.getUsers().get(i);
                if (u.isOnline()) {
                    String tmp = "null";
                    if (u.getActiveChat() != null) {
                        tmp = u.getActiveChat().getChatPartner(u).getKennung();
                    }
                    this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                    this.oos.writeObject(new Message(u.getKennung(), "ONLINE", tmp, Customtime.get()));
                }
            }
            // mitteilen dass init zuende ist
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(
                    new Message("server", "DONE", "Initialized " + chats.size() + " chats.", Customtime.get()));
        } catch (Exception e) {
            e.printStackTrace();
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

    private Chat createChat(String userstrings, ArrayList<Message> chat) {
        Chat c = new Chat(stringtouserarray(userstrings));
        c.setChat(chat);
        return c;
    }

    private void addChattoUsers(Chat chat) {
        for (int i = 0; i < chat.getUsers().size(); i++) {
            User user = chat.getUsers().get(i);
            user.addChat(chat);
        }
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