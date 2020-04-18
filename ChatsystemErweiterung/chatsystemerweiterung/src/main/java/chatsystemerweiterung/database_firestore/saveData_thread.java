package chatsystemerweiterung.database_firestore;

import java.util.ArrayList;

import chatsystemerweiterung.server.Message;
import chatsystemerweiterung.server.User;

public class saveData_thread extends Thread {

    saveData sd;

    public void run(Message msg, ArrayList<User> userList) {
        this.sd = new saveData();
        sd.saveChat(msg, userList);
    }
}