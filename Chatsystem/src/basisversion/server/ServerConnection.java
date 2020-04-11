package basisversion.server;

import java.io.IOException;
import java.net.*;

public class ServerConnection extends Thread {

    Socket serverconnection;
    int port;

    public ServerConnection(int port) {
        this.port = port;
    }

    public void run() {
        while (true) {

        }
    } // run

} // class