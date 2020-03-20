package lukastests;

import java.net.*;
import java.io.*;
import java.util.*;

public class LukasClient {
    private static final int SERVER_PORT = 187;
    private static final String HOSTNAME = "localhost";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOSTNAME, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String userIn = userInput.readLine();
                out.println(userIn);
                System.out.println(in.readLine());
            }
        } catch (ConnectException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}