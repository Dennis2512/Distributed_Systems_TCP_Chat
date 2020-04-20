package basisversion.client;

import java.io.*;
import java.net.Socket;

import basisversion.server.Customtime;
import basisversion.server.Message;

public class Register extends Thread {

    private BufferedReader console;
    private Socket connection;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String kennung, password;

    public Register(Socket connection) {
        this.connection = connection;
        this.console = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        try {
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            // kennung einlesen
            System.out.println("Kennung:");
            String k = this.console.readLine();
            // password einlesen
            System.out.println("Password:");
            String p = this.console.readLine();
            // versuchen mit kennung und password anzumelden
            this.oos.writeObject(new Message(k, "REGISTER", p, Customtime.get()));
            // wenn erfolgreich, dann angemeldeten nutzer setzen
            this.ois = new ObjectInputStream(connection.getInputStream());
            Message ans = (Message) ois.readObject();
            if (ans.getType().equals("SUCCESS")) {
                this.kennung = k;
                this.password = p;
                System.out.println("Registriert. Angemeldet als " + this.kennung);
            } else {
                System.out.println(ans.getText());
            }
        } catch (IOException e) {
            try {
                System.out.println("Server connection was lost, trying to reconnect...");
                this.connection = new Socket("localhost", this.connection.getPort() == 187 ? 188 : 187);
                System.out.println("Verbunden mit Server " + (this.connection.getPort() == 187 ? 1 : 2));
            } catch (Exception err) {
                System.out.println("Servers went offline.");
                System.exit(0);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getKennung() {
        return this.kennung;
    }

    public String getPassword() {
        return this.password;
    }

    public Socket getConnection() {
        return this.connection;
    }
}