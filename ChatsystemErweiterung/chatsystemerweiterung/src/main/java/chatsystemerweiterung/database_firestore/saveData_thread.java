package chatsystemerweiterung.database_firestore;

import java.util.ArrayList;

import chatsystemerweiterung.rsa.Security;
import chatsystemerweiterung.server.Message;
import chatsystemerweiterung.server.User;

// Thread zum Speichern / Laden der Daten in die / aus der DB
public class saveData_thread extends Thread {

    saveData sd;
    Security security;

    // Nutze RSA zum Ver- und Entschlüsseln der Daten
    public saveData_thread() {
        this.security = new Security();
    }

    // Startet saveData in einem seperaten Thread
    public void run(Message msg, ArrayList<User> userList) {
        this.sd = new saveData();
        sd.saveChat(this.security.encryptMessage(msg), userList);
    }
}