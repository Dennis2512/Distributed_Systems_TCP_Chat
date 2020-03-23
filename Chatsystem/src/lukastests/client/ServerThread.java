package lukastests.client;

import java.net.*;
import java.io.*;
import java.util.*;

public class ServerThread extends Thread {

    private BufferedReader in;

    public ServerThread(BufferedReader in) {
        this.in = in;
    }

    public void run() {
        try {
            while (true) {
                String input = in.readLine();
                if (input.substring(0, input.indexOf('_')).equals("MSG")) {
                    System.out.println(input.substring(input.indexOf('_') + 1));
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}