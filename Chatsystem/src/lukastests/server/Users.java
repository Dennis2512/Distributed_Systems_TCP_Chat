package lukastests.server;

import java.net.*;
import java.io.*;
import java.util.*;

public class Users {
    private User[] users;

    public Users() {
        this.users = new User[3];
        this.users[0] = new User("LB", "LBpass");
        this.users[1] = new User("TB", "TBpass");
        this.users[2] = new User("DN", "DNpass");
    }

    public User[] getUsers() {
        return this.users;
    }

    public User getUser(String kennung) {
        User akt = null;
        int i = 0;
        while (akt == null && i < this.users.length) {
            if (this.users[i].getKennung().equals(kennung)) {
                akt = this.users[i];
            }
            i++;
        }
        return akt;
    }
}