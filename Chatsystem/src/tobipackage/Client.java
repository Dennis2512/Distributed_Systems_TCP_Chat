package tobipackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Client {
    //private int serverPort; -> getServerport
    private static final int SERVER_PORT = 188;
   // private static final String HOSTNAME = "localhost";

    public static void main(String[] args) {
        String HOSTNAME = "localhost";
        PrintWriter networkOut = null;
        BufferedReader networkIn = null;
        Socket s = null;
        try{
            s = new Socket(HOSTNAME, SERVER_PORT);
            System.out.println("Connected to Server");
            networkIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            networkOut = new PrintWriter(s.getOutputStream());
            while (true){
                String theLine = userIn.readLine();
                if (theLine.equals(".")) break;
                networkOut.println(theLine);
                networkOut.flush();
                System.out.println(networkIn.readLine());
            }
        } catch (IOException e1){
            System.err.println(e1);
        } finally {
            try{
                if(s != null) {
                    s.close();
                }
            }catch (IOException e2){
                System.err.println(e2);
            }
        }
    }
    // ------------------------------------------------------------------------------------------
}


