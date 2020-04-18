package chatsystemerweiterung.client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import chatsystemerweiterung.rsa.Security;
import chatsystemerweiterung.server.Customtime;
import chatsystemerweiterung.server.Message;

public class Login extends Thread {

    private BufferedReader console;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String kennung;
    private Socket connection;
    private Security security;

    public Login(Socket connection) {
        this.console = new BufferedReader(new InputStreamReader(System.in));
        this.connection = connection;
        this.security = new Security();
    }

    @SuppressWarnings("unchecked")
    public void run() {
        try {
            while (this.kennung == null) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                // kennung einlesen
                System.out.println("Kennung:");
                String k = this.console.readLine();
                // password einlesen
                System.out.println("Password:");
                String p = this.console.readLine();
                // versuchen mit kennung und password anzumelden
                this.oos.writeObject(this.security.encryptMessage(new Message(k, "LOGIN", p, Customtime.get())));
                // wenn erfolgreich, dann angemeldeten nutzer setzen
                this.ois = new ObjectInputStream(this.connection.getInputStream());

                Message ans = this.security.decryptMessage((Message) this.ois.readObject());
                if (ans.getType().equals("SUCCESS")) {
                    this.kennung = k;
                    System.out.println("Angemeldet als " + this.kennung);
                    this.ois = new ObjectInputStream(this.connection.getInputStream());
                    ArrayList<ArrayList<String>> chatoverview = (ArrayList<ArrayList<String>>) this.ois.readObject();
                    System.out.println(chatoverview.size());
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

    public String getKennung() {
        return this.kennung;
    }
}