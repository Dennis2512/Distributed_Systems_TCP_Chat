package lukastests.client;

import java.net.*;
import java.io.*;

public class LukasClient {
    public static void main(String[] args) {
        try {
            // verbindung zum server
            Socket socket = new Socket("localhost", 187);
            // input und output des servers + consolen eingabe
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

            // Anmeldung starten
            Login login = new Login(in, out);
            login.start();
            login.join();
            while (login.getKennung() != null) {
                // auswahl ob man sich abmelden oder chatten will
                System.out.println("Was möchten Sie tun? ( 'C' = chatten, 'L' = logout )");
                String aktion = consoleIn.readLine();
                if (aktion.equals("C")) {
                    // mit Chatpartner verbinden
                    Connect connect = new Connect(in, out);
                    connect.start();
                    connect.join();
                    if (connect.getPartner() != null) {
                        // clear screen
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        // 'Überschrift' des chatrooms
                        System.out.println("Chatroom mit " + connect.getPartner()
                                + ".  ( Schreibe '.leave' um den Chat zu verlassen.)");
                        // starten der chat threads
                        ConsoleThread consoleThread = new ConsoleThread(out, login.getKennung(), consoleIn);
                        IncomingThread incomingThread = new IncomingThread(in);
                        consoleThread.start();
                        incomingThread.start();
                        // diese laufen solange, bis einer den chat verlässt, dann endet zunächst der
                        // incomint thread, darauf wird hier gewartet
                        incomingThread.join();
                        if (consoleThread.isAlive()) { // wenn die console noch auf eingaben wartet
                            System.out.println("Write '.exit' to leave the chat.");
                            consoleThread.join();
                        }
                    } else {
                        System.out.println("Connect fehlgeschlagen.");
                    }
                } else if (aktion.equals("L")) {
                    out.println("LOGOUT_");
                    String logout = in.readLine();
                    if (logout.equals("SUCCESS")) {
                        login.logout();
                        in.close();
                        out.close();
                        consoleIn.close();
                        socket.close();
                    }

                }

            }
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}