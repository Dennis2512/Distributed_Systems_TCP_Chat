package lukastests;

import java.net.*;
import java.io.*;
import java.util.*;

public class Userconnection extends Thread {

    private int id;
    private ArrayList<Userconnection> cons;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Userconnection(int id, Socket socket, ArrayList<Userconnection> cons) {
        this.socket = socket;
        this.id = id;
        this.cons = cons;
    }

    public void run() {
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                out.println(in.readLine());
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (this.socket != null) {
                    this.cons.remove(this);
                    this.socket.close();
                    System.out.println(id + " closed.");
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}