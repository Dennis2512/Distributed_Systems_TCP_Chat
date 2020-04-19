package chatsystemerweiterung.GUI;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Main {

    Socket connection;
    Login login;

    public static void main(String[] args) {

        // stellt eine Connection her
        Socket connection = null;
        Login login = new Login(connection);

        int port = selectPort();
        while (connection == null) {
            System.out.println("Verbinde mit Server...");
            try {
                connection = new Socket("localhost", port);
                System.out.println("Verbunden mit Server " + (port == 187 ? 1 : 2));
                // baut den LoginScreen auf nachdem eine Connection mit dem Server
                // sichergestellt wird
                login.build(connection);
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
        return port == 187 ? 188 : 187;
    }
}