package lukastests.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Connect extends Thread {

    private BufferedReader in, consoleIn;
    private PrintWriter out;
    private String partner;

    public Connect(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
        this.consoleIn = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        try {
            while (this.partner == null) {
                // partnerkennung einlesen
                System.out.println("Kennung ihres Chatpartners eingeben:");
                String input = this.consoleIn.readLine();
                this.out.println("CONNECT_" + input);
                String success = this.in.readLine();
                if (success.equals("SUCCESS")) { // wenn verbindung erfolgreich
                    this.partner = input;
                } else if (success.equals("WAITFOR")) { // warten auf Partnerverbindung
                    System.out.println("Auf Partner warten.");
                    if (in.readLine().equals("PARTNERCONNECT")) { // der partner hat sich verbunden
                        this.partner = input;
                    } else { // der partner konnte sich nicht verbinden
                        System.out.println("Partner could not be connected.");
                    }
                } else { // andere rückmeldung des servers
                    System.out.println(success);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getPartner() {
        return this.partner;
    }
}