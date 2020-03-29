package lukastests.client;

import java.net.*;
import java.io.*;
import java.util.*;

public class LukasClient {
    private static final int SERVER_PORT = 187;
    private static final String HOSTNAME = "localhost";

    public static void main(String[] args) throws InterruptedException {
        try {
            Socket socket = new Socket(HOSTNAME, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            boolean connected = false;
            String kennung = "";
            String partner = "";
            while (!connected) {
                String msg = in.readLine();
                switch (msg) {
                    case "LOGIN":
                        System.out.println("Kennung:");
                        String k = userInput.readLine();
                        kennung = k;
                        System.out.println("Password:");
                        String p = userInput.readLine();
                        out.println("LOGIN_" + k + "_" + p);
                        System.out.println(in.readLine());
                        break;
                    case "CONNECT":
                        System.out.println("Kennung ihres Chatpartners eingeben:");
                        partner = userInput.readLine();
                        out.println("CONNECT_" + partner);
                        String success = in.readLine();
                        if (success.equals("SUCCESS")) {
                            connected = true;
                        } else if (success.equals("WAITFOR")) {
                            System.out.println("Auf Partner warten.");
                            if (in.readLine().equals("PARTNERCONNECT")) {
                                connected = true;
                            } else {
                                System.out.println("Partner could not be connected.");
                            }
                        } else {
                            System.out.println(success);
                        }
                        break;
                    default:
                        System.out.println(msg);
                }
            }
            // clear console
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("Chatroom mit " + partner);
            InputThread inThread = new InputThread(out, kennung);
            inThread.start();
            ServerThread serverThread = new ServerThread(in/* , userInput */);
            serverThread.start();
        } catch (ConnectException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void printMsg(String msg) {
        System.out.println(msg);
    }
}