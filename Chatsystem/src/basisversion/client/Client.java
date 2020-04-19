package basisversion.client;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        Socket connection = null;
        int port = selectPort();
        BufferedReader console = null;
        String user = null;
        Login login = null;
        while (connection == null) {
            // connection null setzen
            //
            System.out.println("Verbinde mit Server...");
            try {
                connection = new Socket("localhost", port);
                System.out.println("Verbunden mit Server " + (port == 187 ? 1 : 2));

                console = new BufferedReader(new InputStreamReader(System.in));
                //login after reconnection
                if (user != null){
                    login = new Login(connection);
                    login.start();
                    login.join();
                    user = login.getKennung();
                    if (user == null) {
                        connection = null;
                        continue;
                    }
                }
                // login or register
                if (user == null) {
                    System.out.println("Do you want to login or register? (l/r)");
                    String answer = console.readLine();
                    if (answer.equals("l")) {
                        // start login
                        login = new Login(connection);
                        login.start();
                        login.join();
                        user = login.getKennung();
                        if (user == null) {
                            connection = null;
                            continue;
                        }

                    } else if (answer.equals("r")) {
                        // start register
                        Register register = new Register(connection);
                        register.start();
                        register.join();
                        user = register.getKennung();
                        if (user == null) {
                            connection = null;
                            break;
                        }
                    } else {
                        System.out.println("Unknown command: " + answer);
                    }
                }
                
                boolean offline = false;
                if (!offline == true) {
                    System.out.println("Do you want to start a Chat or logout? (c/l)");
                    String action = console.readLine();
                    if (action.equals("c")) {
                        // start connect
                        Connect connect = new Connect(connection, user);
                        connect.start();
                        connect.join();
                        if (connect.getPartner() == null){
                            connection = null;
                            continue;
                        } else {
                            // chat starten, und erst fortfahren sobald der chat geschlossen wurde
                            Chatsession c = new Chatsession(connect.getChat(), connection, user, connect.getPartner());
                            while (c.isDisplayable()) {
                                Thread.sleep(200);
                            }
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
            } catch (IOException ex) {
                port = changePort(port);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    private static int selectPort() {
        Random ran = new Random();
        return (187 + ran.nextInt(2));

    }

    public static int changePort(int port) {
        // wechselt zwischen port 187 und 188
        return port == 187 ? 188 : 187;
    }
}