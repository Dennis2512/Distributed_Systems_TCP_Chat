package basisversion.client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import basisversion.server.Customtime;
import basisversion.server.Message;

public class Login extends Thread {

    private BufferedReader console;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String kennung;
    private Socket connection;
    private boolean end;

    public Login(Socket connection) {
        this.console = new BufferedReader(new InputStreamReader(System.in));
        this.connection = connection;
        this.end = false;
    }

    @SuppressWarnings("unchecked")
    public void run() {
        try {
            while (!this.end) {
                this.oos = new ObjectOutputStream(this.connection.getOutputStream());
                // kennung einlesen
                System.out.println("Kennung:");
                String k = this.console.readLine();
                // password einlesen
                System.out.println("Password:");
                String p = this.console.readLine();
                // versuchen mit kennung und password anzumelden
                this.oos.writeObject(new Message(k, "LOGIN", p, Customtime.get()));
                // wenn erfolgreich, dann angemeldeten nutzer setzen
                this.ois = new ObjectInputStream(this.connection.getInputStream());

                Message ans = (Message) this.ois.readObject();
                if (ans.getType().equals("SUCCESS")) {
                    this.kennung = k;
                    this.end = true;
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
            this.end = true;
            this.connection = null;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this.end = true;
            this.connection = null;
        }
    }

    public String getKennung() {
        return this.kennung;
    }
}