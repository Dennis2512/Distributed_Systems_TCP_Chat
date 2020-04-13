package basisversion.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {

    private Socket connection;
    private boolean open;

    public ServerConnection() {
        this.open = false;
    }

    public void setConnection(Socket connection) {
        this.connection = connection;
        this.open = true;
    }

    public void init(Users users) {
        try {
            Init init = new Init(this.connection, users);
            init.start();
            init.join();
            Synchronizer sync = new Synchronizer(this.connection, users);
            sync.start();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public void startInit(Users users) {
        try {
            Transfer transfer = new Transfer(this.connection, users);
            transfer.start();
            transfer.join();
            Synchronizer sync = new Synchronizer(this.connection, users);
            sync.start();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public boolean isOpen() {
        return this.open;
    }

    public void sendMsg(Message msg) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(this.connection.getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}