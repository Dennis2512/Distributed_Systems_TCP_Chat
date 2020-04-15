package basisversion.server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

public class Server1 {
    private static int exchangeport = 188;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(187);
        // Nutzer initialisieren
        Users users = new Users();
        ArrayList<Connection> connections = new ArrayList<Connection>();
        try {
            // wenn der andere server online ist, dann verbindung aufbauen und als
            // serververbindung kennzeichnen
            Socket tmp = new Socket("localhost", exchangeport);
            Connection con = new Connection(tmp, connections, users, true);
            connections.add(con);
            con.start();
            // anderem server bescheid geben
            con.send(new Message("server", "SERVERINIT", "This is the serverconnection", new Date()));
        } catch (IOException e) {
            // nichts zu tun
        }

        try {
            // server starten
            System.out.println("Server 1 läuft");

            // verbindungen erwarten und speichern
            while (true) {
                // connections aufbauen
                Connection con = new Connection(server.accept(), connections, users, false);
                connections.add(con);
                con.start();
            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            server.close();
        }
    }
}