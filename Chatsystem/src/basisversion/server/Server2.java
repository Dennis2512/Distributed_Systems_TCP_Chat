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
        Serverconnection serverconnection;
        try {
            // wenn der andere server bereits am laufen ist verbindung als serververbindung
            // speichern und anderem server mitteilen, dass diese verbindung die
            // serververbindung ist
            Socket tmp = new Socket("localhost", exchangeport);
            serverconnection = new Serverconnection();
            serverconnection.setConnection(tmp);
            serverconnection.init(users);
        } catch (IOException e) {
            serverconnection = new Serverconnection();
        }

        try {

            // server starten
            System.out.println("Server 2 läuft");

            // verbindungen empfangen und speichern
            ArrayList<Connection> connections = new ArrayList<Connection>();
            while (true) {
                // connections aufbauen
                Connection con = new Connection(server.accept(), connections, users, serverconnection);
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