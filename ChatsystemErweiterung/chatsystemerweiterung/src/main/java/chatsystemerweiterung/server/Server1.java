package chatsystemerweiterung.server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

import chatsystemerweiterung.database_firestore.saveData;

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
            saveData sd = new saveData();
            sd.initChat(users);
            // Hier DB abrufen
            // initChat(users) -> auf Obj Operationen ausführen
            // Aufruf auch in Server 2
            // Users nicht neu erzeugen
            // ArrayList vom Typ Messages -> wenn Chat erzeugt wurde setChat(ArrayList setzen in einem Stück)
            // das NICHT als Thread machen
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
            e.printStackTrace();
        } finally {
            server.close();
        }
    }
}