package GUI;

import basisversion.server.Serverconnection;
import java.io.*;
import java.net.*;
import java.util.Random;

public class Main {
<<<<<<< HEAD

    Socket connection;
    Serverconnection serverconnection;
    Login login;

    public static void main(String[] args) {

        // stellt eine Connection her
        Socket connection = null;
        Login login = new Login(connection);

        int port = selectPort();
        while (connection == null) {
            System.out.println("Verbinde mit Server...");
            try {
                connection = new Socket("localhost", 187);
                System.out.println("connected!");
                // baut den LoginScreen auf nachdem eine Connection mit dem Server
                // sichergestellt wird
                login.build(connection);
            } catch (IOException ex) {
                port = changePort(port);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }

=======
    public static void main(String[] args) {
>>>>>>> 62d0795dc800888a0ce9aed32e5bf88b824bfac0
        // emojiFinder finder = new emojiFinder();
        System.out.println(emojiFinder.shortcutSwitcher(":fit: ist ein emoji, sowie :pray: und :heart_black:"));
    }

    private static int selectPort() {
        Random ran = new Random();
        return (187 + ran.nextInt(2));

    }

    public static int changePort(int port) {
        return port == 187 ? 188 : 187;
    }
}