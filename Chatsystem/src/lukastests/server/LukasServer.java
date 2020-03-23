package lukastests.server;

import java.net.*;
import java.io.*;
import java.util.*;

public class LukasServer {
    private static final int SERVER_PORT = 187;

    public static void main(String[] args) {
        try {
            // server
            Users users = new Users();
            ServerSocket server = new ServerSocket(SERVER_PORT);
            System.out.println("Server läuft");
            // verbindungen
            ArrayList<Userconnection> connections = new ArrayList<Userconnection>();
            while (true) {
                Userconnection ucon = new Userconnection(server.accept(), connections, users);
                connections.add(ucon);
                ucon.start();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}