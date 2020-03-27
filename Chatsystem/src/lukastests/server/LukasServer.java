package lukastests.server;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class LukasServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(187);
        try {
            // server

            // user definieren
            Users users = new Users();

            // server starten
            System.out.println("Server läuft");

            // verbindungen empfangen und speichern
            ArrayList<Userconnection> connections = new ArrayList<Userconnection>();
            while (true) {
                Userconnection ucon = new Userconnection(server.accept(), connections, users);
                connections.add(ucon);
                // connection starten
                ucon.start();
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            server.close();
        }
    }
}