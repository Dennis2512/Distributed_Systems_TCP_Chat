package basisversion.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(187);
        try {

            // server starten
            System.out.println("Server läuft");

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
        }
    }
}