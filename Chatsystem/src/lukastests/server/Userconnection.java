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
            String msg;
            User tmp;
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("LOGIN");
            while (true) {
                msg = in.readLine();
                switch (msg.substring(0, msg.indexOf('_'))) {
                    case "LOGIN":
                        String k = msg.substring(msg.indexOf('_') + 1);
                        k = k.substring(0, k.indexOf('_'));
                        tmp = this.users.getUser(k);
                        if (tmp == null) {
                            out.println("Anmelden fehlgeschlagen. Nutzer konnte nicht gefunden werden.");
                            out.println("LOGIN");
                        } else if (tmp.isOnline()) {
                            out.println("Nutzer ist bereits angemeldet.");
                            out.println("LOGIN");
                        } else {
                            String p = msg.substring(msg.lastIndexOf('_') + 1);
                            if (tmp.login(p)) {
                                this.user = tmp;
                                out.println("Angemeldet als " + k);
                                System.out.println(k + " hat sich angemeldet.");
                                out.println("CONNECT");
                            } else {
                                out.println("Password falsch.");
                                out.println("LOGIN");
                            }
                        }
                        break;
                    case "CONNECT":
                        String p = msg.substring(msg.indexOf('_') + 1);
                        tmp = this.users.getUser(p);
                        if (tmp == null) {
                            out.println("Nutzer konnte nicht gefunden werden.");
                            out.println("CONNECT");
                        } else if (!tmp.isOnline()) {
                            out.println("Nutzer ist nicht online.");
                            out.println("CONNECT");
                        } else if (tmp == this.user) {
                            out.println("Verbindung mit sich selber nicht möglich.");
                            out.println("CONNECT");
                        } else if (tmp.getPartner() != null && tmp.getPartner() != this.user) {
                            out.println("Nutzer ist bereits am chatten.");
                            out.println("CONNECT");
                        } else {
                            this.user.setPartner(tmp);
                            this.getPartnerConnection();
                            if (this.partnerCon == null) {
                                out.println("Es konnte keine Verbindung gefunden werden.");
                            } else {
                                if (tmp.getPartner() == null) {
                                    out.println("WAITFOR");
                                } else {
                                    this.partnerCon.send("PARTNERCONNECT");
                                    out.println("SUCCESS");
                                }
                            }
                        }
                        break;
                    case "MSG":
                        if (this.user.getPartner() == null) {
                            out.println("Erst mit Partner verbinden.");
                        } else if (this.user.getPartner().getPartner() == null) {
                            out.println("Partner hat sich noch nicht mit ihnen verbunden.");
                        } else if (this.partnerCon == null) {
                            out.println("Keine Verbindung zum Partner gefunden. Ist er online?");
                        } else {
                            this.partnerCon.send(msg);
                        }
                        break;
                    default:
                        System.out.println("Unexpected input.");
                }
            }
        } catch (

        IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (this.socket != null) {
                    this.cons.remove(this);
                    this.socket.close();
                    this.user.logout();
                    System.out.println("Closed.");
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public void send(String msg) {
        this.out.println(msg);
    }

    public User getUser() {
        return this.user;
    }

    private void getPartnerConnection() {
        for (int i = 0; i < this.cons.size(); i++) {
            if (this.cons.get(i).getUser().getKennung() == this.user.getPartner().getKennung()) {
                this.partnerCon = this.cons.get(i);
            }
        }
    }
}