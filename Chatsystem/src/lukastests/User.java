package lukastests;

import java.net.*;
import java.io.*;
import java.util.*;

public class User {
    private String kennung;
    private String password;
    private String partner;
    private boolean online = false;

    public User(String kennung, String password) {
        this.kennung = kennung;
        this.password = password;
    }

    public boolean login(String password) {
        if (password.equals(this.password)) {
            this.online = true;
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        this.online = false;
    }

    // getter

    public String getKennung() {
        return this.kennung;
    }

    public String getPartner() {
        return this.partner;
    }

    public boolean isOnline() {
        return this.online;
    }

}