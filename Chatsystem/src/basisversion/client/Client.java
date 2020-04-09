package basisversion.client;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket connection = new Socket("localhost", 187);
        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            // login or register
            String user = null;
            while (user == null) {
                System.out.println("Do you want to login or register? (l/r)");
                String answer = console.readLine();
                if (answer.equals("l")) {
                    // start login
                    Login login = new Login(connection);
                    login.start();
                    login.join();
                    user = login.getKennung();
                } else if (answer.equals("r")) {
                    // start register
                    Register register = new Register(connection);
                    register.start();
                    register.join();
                    user = register.getKennung();
                } else {
                    System.out.println("Unknown command: " + answer);
                }
            }
            boolean offline = false;
            while (!offline) {
                System.out.println("Do you want to start a Chat or logout? (c/l)");
                String action = console.readLine();
                if (action.equals("c")) {
                    // start connect
                    Connect connect = new Connect(connection);
                    connect.start();
                    connect.join();
                    // chat starten, und erst fortfahren sobald der chat geschlossen wurde
                    Chatsession c = new Chatsession(connect.getChat(), connection, user, connect.getPartner());
                    while (c.isDisplayable()) {
                        Thread.sleep(200);
                    }

                } else if (action.equals("l")) {
                    // logout
                    Logout logout = new Logout(connection);
                    logout.start();
                    logout.join();
                    if (logout.offline()) {
                        System.out.println("Abgemeldet.");
                        offline = true;
                    } else {
                        System.out.println("Logout failed.");
                    }
                } else {
                    System.out.println("Unknown command: " + action);
                }
            }

        } catch (InterruptedException e) {
            System.err.println(e);
        } finally {
            connection.close();
        }

    }
}