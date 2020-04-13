package basisversion.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Connection extends Thread {

    User user;
    Users users;
    Socket connection;
    ArrayList<Connection> connections;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    ServerConnection serverconnection;

    public Connection(Socket connection, ArrayList<Connection> connections, Users users,
            ServerConnection serverconnection) throws IOException {
        this.connection = connection;
        this.connections = connections;
        this.users = users;
        this.serverconnection = serverconnection;
    }

    public void run() {
        try {

            while (true) {
                this.ois = new ObjectInputStream(this.connection.getInputStream());
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                Message msg = (Message) ois.readObject();
                switch (msg.getType()) {
                    case "LOGIN":
                        this.login(msg);
                        break;
                    case "REGISTER":
                        this.register(msg);
                        break;
                    case "LOGOUT":
                        this.logout();
                        break;
                    case "CONNECT":
                        this.connect(msg);
                        break;
                    case "MSG":
                        this.message(msg);
                        break;
                    case "LEAVE":
                        this.leave();
                        break;
                    case "SERVER":
                        this.server();
                        break;
                    default:
                        System.out.println("Unexpected input.");
                }
            }

        } catch (IOException e) {
            if (this.user != null) {
                System.out.println("Verbindung zu " + this.user.getKennung() + " wurde getrennt.");
                this.user.logout();
                this.connections.remove(this);
            }
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    private void login(Message msg) {
        try {
            // user mit kennung suchen
            User tmp = this.users.getUser(msg.getSender());
            if (tmp == null) { // prüfen ob user gefunden wurde
                this.oos.writeObject(new Message("server", "error", "Nutzer existiert nicht", Customtime.get()));
            } else if (tmp.isOnline()) { // prüfen ob nutzer bereits angemeldet ist
                this.oos.writeObject(
                        new Message("server", "error", "Nutzer ist bereits angemeldet.", Customtime.get()));
            } else {
                // prüfen ob passwort korrekt war
                if (tmp.login(msg.getText(), this)) {
                    // aktuellen nutzer dieser verbindung setzen
                    this.user = tmp;
                    this.oos.writeObject(new Message("server", "SUCCESS", "Angemeldet.", Customtime.get()));
                    System.out.println(msg.getSender() + " hat sich angemeldet.");
                } else { // falsches passwort
                    this.oos.writeObject(new Message("server", "error", "Falsches Passwort", Customtime.get()));
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void register(Message msg) {
        try {
            // prüfen ob nutzer bereits existiert
            if (this.users.getUser(msg.getSender()) != null) {
                this.oos.writeObject(new Message("server", "error", "Nutzer existiert bereits.", Customtime.get()));
            } else {
                if (this.users.register(msg.getSender(), msg.getText())) { // registrierung erfolgreich
                    // danach wird der nutzer direkt eingeloggt
                    this.user = this.users.getUser(msg.getSender());
                    this.user.login(msg.getText(), this);
                    this.oos.writeObject(
                            new Message("server", "SUCCESS", "Registriert und angemeldet.", Customtime.get()));
                    System.out.println(msg.getSender() + " hat sich registriert und angemeldet.");
                } else { // fehlschlag
                    this.oos.writeObject(
                            new Message("server", "error", "Registrierung fehlgeschlagen", Customtime.get()));
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    private void logout() {
        try {
            if (this.user != null) { // prüfen ob nutzer angemeldet ist
                System.out.println(this.user.getKennung() + " hat sich abgemeldet.");
                // logout
                this.user.logout();
                this.user = null;
                this.connections.remove(this);
                this.oos.writeObject(new Message("server", "SUCCESS", "Abgemeldet.", Customtime.get()));
                // verbindungen schließen
                this.oos.close();
                this.ois.close();
                this.connection.close();
            } else { // nicht angemeldet
                this.oos.writeObject(
                        new Message("server", "error", "Abmelden geht nur für angemeldete Nutzer", Customtime.get()));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void connect(Message msg) {
        try {
            // prüfe ob es nutzer gibt
            User receiver = this.users.getUser(msg.getText());
            if (receiver == null) {
                this.oos.writeObject(
                        new Message("server", "error", "Nutzer konnte nicht gefunden werden.", Customtime.get()));
            } else if (this.user == receiver) {
                this.oos.writeObject(
                        new Message("server", "error", "Chatten mit sich selber nicht möglich.", Customtime.get()));
            } else if (this.user.getActiveChat() != null) {
                this.oos.writeObject(
                        new Message("server", "error", "Erst bisherigen Chat verlassen.", Customtime.get()));
            } else {
                Chat c = this.user.getChatWith(receiver);
                if (c == null) {
                    c = new Chat(new ArrayList<User>(Arrays.asList(this.user, receiver)));
                    this.user.addChat(c);
                    this.user.setActiveChat(c);
                    receiver.addChat(c);
                    this.oos.writeObject(
                            new Message("server", "NEWCHAT", "Es wurde ein neuer Chat begonnen.", Customtime.get()));
                } else {
                    this.user.setActiveChat(c);
                    this.oos.writeObject(
                            new Message("server", "LOADCHAT", "Chatverlauf wird geladen", Customtime.get()));
                    this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                    this.oos.writeObject(c.getChat());
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    private void message(Message msg) {
        try {
            if (this.user == null) {
                this.oos.writeObject(new Message("server", "error", "Nicht angemeldet.", Customtime.get()));
            } else if (this.user.getActiveChat() == null) {
                this.oos.writeObject(new Message("server", "error", "Chat nicht gefunden.", Customtime.get()));
            } else {
                String time = Customtime.get();
                msg.setTime(time);
                this.user.write(msg);
                this.oos.writeObject(new Message("server", "SENT", time + " " + msg.getSender() + ": " + msg.getText(),
                        Customtime.get()));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void leave() {
        try {
            if (this.user == null) {
                this.oos.writeObject(new Message("server", "error", "Nicht angemeldet.", Customtime.get()));
            } else if (this.user.getActiveChat() == null) {
                this.oos.writeObject(new Message("server", "error", "Kein aktiver Chat gefunden.", Customtime.get()));
            } else {
                this.user.leaveChat();
                this.oos.writeObject(new Message("server", "LEFT", "Chat verlassen.", Customtime.get()));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void send(Message msg) {
        try {
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(msg);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void server() {
        try {
            this.connections.remove(this);
            this.oos.writeObject(new Message("server", "START", "starting init transfer", "time"));
            this.serverconnection.setConnection(this.connection);
            this.serverconnection.startInit();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

} // class