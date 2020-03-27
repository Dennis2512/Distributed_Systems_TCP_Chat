package lukastests.server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Userconnection extends Thread {

    private User user;
    private ArrayList<Userconnection> cons;
    private Userconnection partnerCon;
    private Users users;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Userconnection(Socket socket, ArrayList<Userconnection> cons, Users users) {
        this.socket = socket;
        this.cons = cons;
        this.users = users;
    }

    public void run() {
        try {
            // output und input hier definiert damit der catch block ausgeführt wird wenn
            // eine verbindung beendet wird
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // alle einkommenden nachrichten werden hier ausgewertet
            while (true) {
                String msg = in.readLine();
                switch (msg.substring(0, msg.indexOf('_'))) {
                    case "LOGIN":
                        this.login(msg);
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
                    case "LOGOUT":
                        this.logout();
                        break;
                    default:
                        System.out.println("Unexpected input.");
                }
            }
        } catch (IOException e) {
            // wenn sich der client abmeldet und die verbindung geschlossen hat
            // prüfen ob er sich vorher abgemeldet hatte
            if (this.user.isOnline()) {
                if (this.user.getPartner() != null && this.partnerCon != null) {
                    this.partnerCon.send("PARTNERLEAVE");
                    this.partnerCon.setPartnerCon(null);
                    this.user.getPartner().setPartner(null);
                    this.partnerCon = null;
                    this.user.setPartner(null);
                }
                this.cons.remove(this);
                this.user.logout();
            }
            System.out.println(this.user.getKennung() + " hat sich abgemeldet.");
        }
    }

    public void send(String msg) {
        this.out.println(msg);
    }

    public User getUser() {
        return this.user;
    }

    public void setPartnerCon(Userconnection con) {
        this.partnerCon = con;
    }

    private void getPartnerConnection() {
        for (int i = 0; i < this.cons.size(); i++) {
            if (this.cons.get(i).getUser().getKennung() == this.user.getPartner().getKennung()) {
                this.partnerCon = this.cons.get(i);
            }
        }
    }

    // switch methods

    private void login(String msg) {
        User tmp;
        // abtrennung des identifiers
        String k = msg.substring(msg.indexOf('_') + 1);
        // hole kennung aus der nachricht
        k = k.substring(0, k.indexOf('_'));
        // user mit kennung k suchen
        tmp = this.users.getUser(k);
        if (tmp == null) { // prüfen ob user gefunden wurde
            out.println("Anmelden fehlgeschlagen. Nutzer konnte nicht gefunden werden.");
        } else if (tmp.isOnline()) { // prüfen ob nutzer bereits angemeldet ist
            out.println("Nutzer ist bereits angemeldet.");
        } else {
            // passwort aus der nachricht holen
            String p = msg.substring(msg.lastIndexOf('_') + 1);
            // prüfen ob passwort korrekt war
            if (tmp.login(p)) {
                // aktuellen nutzer dieser verbindung setzen
                this.user = tmp;
                out.println("Angemeldet als " + k);
                System.out.println(k + " hat sich angemeldet.");
            } else { // falsches passwort
                out.println("Password falsch.");
            }
        }
    }

    private void connect(String msg) {
        // kennung aus nachricht holen
        String p = msg.substring(msg.indexOf('_') + 1);
        // nach user mit kennung suchen
        User partner = this.users.getUser(p);
        if (partner == null) { // nutzer nicht gefunden
            out.println("Nutzer konnte nicht gefunden werden.");
        } else if (!partner.isOnline()) { // nutzer ist nicht online
            out.println("Nutzer ist nicht online.");
        } else if (partner == this.user) { // parter ist nutzer selber
            out.println("Verbindung mit sich selber nicht möglich.");
        } else if (partner.getPartner() != null && partner.getPartner() != this.user) { // partner nicht frei
            out.println("Nutzer ist bereits am chatten.");
        } else {
            // in dieser connection wird der partner und die verbindung zu diesem
            // gespeichert
            this.user.setPartner(partner);
            this.getPartnerConnection();
            if (this.partnerCon == null) { // verbindung zu partner konnte nicht gefunden werden
                out.println("Es konnte keine Verbindung gefunden werden.");
            } else {
                if (partner.getPartner() == null) { // warten dass sich partner auch mit mir verbindet
                    out.println("WAITFOR");
                } else { // Verbindung aufbauen, chatroom öffnen
                    this.partnerCon.send("PARTNERCONNECT");
                    out.println("SUCCESS");
                }
            }
        }
    }

    private void message(String msg) {
        if (this.user.getPartner() == null) { // keine aktive verbindung
            out.println("Erst mit Partner verbinden.");
        } else if (this.user.getPartner().getPartner() == null) { // keine verbindung des partners
            out.println("Partner hat sich noch nicht mit ihnen verbunden.");
        } else if (this.partnerCon == null) { // keine verbindung zum partner gefunden
            out.println("Keine Verbindung zum Partner gefunden. Ist er online?");
        } else {
            // nachricht wird gesendet
            this.partnerCon.send(msg);
            // nachricht wird im chatlog hinterlegt
            new ChatLog(msg, this.user.getKennung(), this.user.getPartner().getKennung());
        }
    }

    private void leave() {
        if (this.partnerCon == null || this.user.getPartner() == null) { // wenn es keine verbindung zum schließen gibt
            this.out.println("Keine aktive Verbindung");
        } else {
            // partner mitteilen, dass chat beendet wurde und verbindung beenden
            this.partnerCon.send("PARTNERLEAVE");
            this.partnerCon.setPartnerCon(null);
            this.user.getPartner().setPartner(null);
            this.partnerCon = null;
            this.user.setPartner(null);
            this.out.println("LEAVESUCCESS");
        }
    }

    private void logout() {
        try {
            this.cons.remove(this);
            this.user.logout();
            this.out.println("SUCCESS");
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}