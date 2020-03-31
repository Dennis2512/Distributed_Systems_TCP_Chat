package basisversion.client;

import java.io.*;
import java.net.Socket;

import basisversion.server.Message;

public class Register extends Thread {

    private BufferedReader console;
    private Socket connection;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String kennung;

    public Register(Socket connection) {
        this.connection = connection;
        this.console = new BufferedReader(new InputStreamReader(System.in));
    }

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
                this.oos.writeObject(new Message(k, "REGISTER", p, "time"));
                // wenn erfolgreich, dann angemeldeten nutzer setzen
                this.ois = new ObjectInputStream(connection.getInputStream());
                Message ans = (Message) ois.readObject();
                if (ans.getType().equals("SUCCESS")) {
                    this.kennung = k;
                    System.out.println("Registriert. Angemeldet als " + this.kennung);
                } else {
                    System.out.println(ans.getText());
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    public String getKennung() {
        return this.kennung;
    }
}