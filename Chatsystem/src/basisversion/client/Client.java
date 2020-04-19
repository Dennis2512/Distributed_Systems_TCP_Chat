package basisversion.client;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        Socket connection = null;
        int port = selectPort();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String user = null;
        String password = null;
        String partner = null;
        while (connection == null) {
            try {
                System.out.println("Verbinde mit Server...");
                connection = new Socket("localhost", port);
                System.out.println("Verbunden mit Server " + (port == 187 ? 1 : 2));
            } catch (Exception e) {
                port = changePort(port);
            }
        }
        while (user == null) {
            try {
                System.out.println("Do you want to login or register? (l/r)");
                String answer = console.readLine();
                if (answer.equals("l")) {
                    Login login = new Login(connection);
                    login.start();
                    login.join();
                    user = login.getKennung();
                    password = login.getPassword();
                    connection = login.getConnection();
                } else if (answer.equals("r")) {
                    Register register = new Register(connection);
                    register.start();
                    register.join();
                    user = register.getKennung();
                    password = register.getPassword();
                    connection = register.getConnection();
                } else {
                    System.out.println("Unknown command: " + answer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        boolean offline = false;
        while (!offline) {
            try {
                System.out.println("Do you want to chat or logout? (c/l)");
                String answer = console.readLine();
                if (answer.equals("c")) {
                    Connect connect = new Connect(connection, user, password);
                    connect.start();
                    connect.join();
                    partner = connect.getPartner();
                    connection = connect.getConnection();
                    if (partner != null) {
                        Chatsession chatsession = new Chatsession(connect.getChat(), connection, user, password,
                                partner);
                        while (chatsession.isDisplayable()) {
                            Thread.sleep(50);
                        }
                        connection = chatsession.getConnection();
                        partner = null;
                    }
                } else if (answer.equals("l")) {
                    Logout logout = new Logout(connection);
                    logout.start();
                    logout.join();
                    offline = logout.offline();
                } else {
                    System.out.println("Unknown command: " + answer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
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