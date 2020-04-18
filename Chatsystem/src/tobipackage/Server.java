package tobipackage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Server {
    private int serverPort;
    private String serverName;
    private PrintWriter serverPrintWriter;
    private ServerSocket serverSocket;
    private Socket connection;
    private String chatverlauf;
    private LinkedList<String> auftragsQueue;// Vermutlich kein String -> NEED TO VERIFY

    public Server(int serverPort, String serverName) {
        this.serverPort = serverPort;
        this.serverName = serverName;
        this.serverPrintWriter = null;
        this.serverSocket = null;
        this.connection = null;
        this.chatverlauf = "";
        this.auftragsQueue = null;
    }

    public void startServer() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
            System.out.println("Following Server is running: " + this.serverName);
        } catch (Exception e) {

        }
    }

    public void connectServer() {
        while (true) {
            try {
                this.connection = this.serverSocket.accept();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void disconnectServer() {
        try {
            this.connection.close();
            System.out.println("Server: " + this.serverName + " is disconnected");
        } catch (Exception e) {

        }
    }

    public void serverWriter() {
        try {
            serverPrintWriter = new PrintWriter(connection.getOutputStream());
            Date now = new Date();
            serverPrintWriter.println(now.toString());
            serverPrintWriter.flush();
        } catch (Exception e) {

        }
    }

    public void createWorker() {

    }

    private static final int SERVER_PORT = 188;

    public static void main(String[] args) {
        int port = SERVER_PORT;
        PrintWriter out = null;
        Socket connection = null;

        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server is running");
            while (true) {
                try {
                    connection = server.accept();
                    out = new PrintWriter(connection.getOutputStream());
                    Date now = new Date();
                    out.println(now.toString());
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null)
                            connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
