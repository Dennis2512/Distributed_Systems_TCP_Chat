package lukastests.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Login extends Thread {

    private BufferedReader in, consoleIn;
    private PrintWriter out;
    private String kennung;

    public Login(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.consoleIn = new BufferedReader(new InputStreamReader(System.in));
        this.out = out;
    }

    public void run() {
        try {
            while (this.kennung == null) {
                // kennung einlesen
                System.out.println("Kennung:");
                String k = this.consoleIn.readLine();
                // password einlesen
                System.out.println("Password:");
                String p = this.consoleIn.readLine();
                // versuchen mit kennung und password anzumelden
                this.out.println("LOGIN_" + k + "_" + p);
                // wenn erfolgreich, dann angemeldeten nutzer setzen
                String ans = in.readLine();
                if (ans.equals("Angemeldet als " + k)) {
                    this.kennung = k;
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
                System.out.println(ans);
            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public String getKennung() {
        return this.kennung;
    }

    public void logout() {
        this.kennung = null;
    }
}