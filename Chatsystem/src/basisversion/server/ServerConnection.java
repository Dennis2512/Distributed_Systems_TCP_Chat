package basisversion.server;

import java.net.Socket;

public class Serverconnection {

    private Socket connection;

    public void setConnection(Socket connection) {
        this.connection = connection;
    }

    public void init() {
        try {
            Init init = new Init(this.connection);
            init.start();
            init.join();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public void startInit() {
        try {
            Transfer transfer = new Transfer(this.connection);
            transfer.start();
            transfer.join();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}