package basisversion.client;

import java.io.*;
import java.net.Socket;

import basisversion.server.Customtime;
import basisversion.server.Message;

public class Login extends Thread {

    private BufferedReader console;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String kennung, password;
    private Socket connection;
    private boolean end;

    public Login(Socket connection) {
        this.console = new BufferedReader(new InputStreamReader(System.in));
        this.connection = connection;
        this.end = false;
    }

    public void run() {
        while (!this.end) {
            try {
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
                    this.password = p;
                    this.end = true;
                    System.out.println("Angemeldet als " + this.kennung);
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