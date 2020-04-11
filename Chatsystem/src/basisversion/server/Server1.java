package basisversion.server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server1 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(187);
        Socket serverconnection = null;
        ServerSocket server2server = null;
        try {
            serverconnection = new Socket("localhost", 190);
        } catch (IOException e) {
            while (serverconnection == null) {
                server2server = new ServerSocket(190);
                serverconnection = server2server.accept();
            }
        }

        try {
            // server starten
            System.out.println("Server 1 läuft");

            // Nutzer initialisieren
            Users users = new Users();

            // verbindungen empfangen und speichern
            ArrayList<Connection> connections = new ArrayList<Connection>();
            while (true) {
                // connections aufbauen
                Connection con = new Connection(server.accept(), connections, users);
                connections.add(con);
                con.start();
            }

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            server.close();
            serverconnection.close();
            server2server.close();
        }
    }
}