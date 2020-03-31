package basisversion.client;

import java.io.*;
import java.net.Socket;

import basisversion.server.Message;

public class Login extends Thread {

    private BufferedReader console;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String kennung;
    private Socket connection;

    public Login(Socket connection) {
        this.console = new BufferedReader(new InputStreamReader(System.in));
        this.connection = connection;
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
                this.oos.writeObject(new Message(k, "LOGIN", p, ""));
                // wenn erfolgreich, dann angemeldeten nutzer setzen
                this.ois = new ObjectInputStream(this.connection.getInputStream());

                Message ans = (Message) this.ois.readObject();
                if (ans.getType().equals("SUCCESS")) {
                    this.kennung = k;
                    System.out.println("Angemeldet als " + this.kennung);
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