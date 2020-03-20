package lukastests;

import java.net.*;
import java.io.*;
import java.util.*;

public class LukasServer {
    private static final int SERVER_PORT = 187;

    public static void main(String[] args) {
        try {
            // server
            ServerSocket server = new ServerSocket(SERVER_PORT);
            System.out.println("Server läuft");
            // verbindungen
            int id = 0;
            ArrayList<Userconnection> connections = new ArrayList<Userconnection>();
            while (true) {
                id++;
                Userconnection ucon = new Userconnection(id, server.accept(), connections);
                connections.add(ucon);
                ucon.start();
                System.out.println(id + " started.");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}