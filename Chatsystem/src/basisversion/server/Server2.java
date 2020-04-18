package basisversion.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server2 {
    private static int exchangeport = 187;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(188);
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
            System.out.println("Server 2 läuft");

            // verbindungen empfangen und speichern
            while (true) {
                // connections aufbauen
                Connection con = new Connection(server.accept(), connections, users, false);
                connections.add(con);
                con.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.close();
        }
    }
}