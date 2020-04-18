package basisversion.client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import basisversion.server.Customtime;
import basisversion.server.Message;

public class Connect extends Thread {

    private BufferedReader console;

    private String partner, user;
    private Socket connection;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ArrayList<Message> chat;

    public Connect(Socket connection, String user) {
        this.console = new BufferedReader(new InputStreamReader(System.in));
        this.connection = connection;
        this.user = user;
    }

    @SuppressWarnings("unchecked")
    public void run() {
        try {
            while (partner == null) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                // kennung einlesen
                System.out.println("Kennung ihres Chatpartners eingeben:");
                String p = this.console.readLine();
                // chat aufbauen
                this.oos.writeObject(new Message(user, "CONNECT", p, Customtime.get()));
                // wenn erfolgreich, dann angemeldeten nutzer setzen
                this.ois = new ObjectInputStream(this.connection.getInputStream());
                Message ans = (Message) ois.readObject();
                if (ans.getType().equals("NEWCHAT")) {
                    this.partner = p;
                    System.out.println(ans.getText());
                } else if (ans.getType().equals("LOADCHAT")) {
                    System.out.println(ans.getText());
                    this.ois = new ObjectInputStream(this.connection.getInputStream());
                    this.chat = (ArrayList<Message>) ois.readObject();
                    this.partner = p;
                } else {
                    System.out.println(ans.getText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Message> getChat() {
        return this.chat;
    }

    public String getPartner() {
        return this.partner;
    }
}