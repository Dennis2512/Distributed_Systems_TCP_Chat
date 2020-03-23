package lukastests.client;

import java.net.*;
import java.io.*;
import java.util.*;

public class InputThread extends Thread {

    private BufferedReader in;
    private PrintWriter out;
    private String kennung;

    public InputThread(PrintWriter out, String kennung) {
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.out = out;
        this.kennung = kennung;
    }

    public void run() {
        try {
            while (true) {
                this.out.println("MSG_" + kennung + ": " + this.in.readLine());
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}