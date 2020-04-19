package chatsystemerweiterung.server;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Users {
    String filepath;
    File userfile;
    BufferedReader reader;
    BufferedWriter writer;

    private ArrayList<User> users;

    public Users() throws IOException {
        this.filepath = Paths.get("").toAbsolutePath().normalize().toString();
        if (!this.filepath.contains("ChatsystemErweiterung")) {
            this.filepath += "\\ChatsystemErweiterung";
        }
        this.filepath += "\\users.txt";
        this.userfile = new File(this.filepath);

        this.userfile = new File(this.filepath);
        this.users = new ArrayList<User>();
        this.writer = new BufferedWriter(new FileWriter(userfile, true));
        this.init();
    }

    private void init() throws IOException {
        String line;
        this.reader = new BufferedReader(new FileReader(userfile));
        while ((line = this.reader.readLine()) != null) {
            String kennung = line.substring(line.indexOf('_') + 1);
            kennung = kennung.substring(0, kennung.indexOf('_'));
            String password = line.substring(line.lastIndexOf('_') + 1);
            this.users.add(new User(kennung, password));
        }
        this.reader.close();
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public User getUser(String kennung) {
        int i = 0;
        User result = null;
        while (result == null && i < this.users.size()) {
            if (this.users.get(i).getKennung().equals(kennung)) {
                result = this.users.get(i);
            }
            i++;
        }
        return result;
    }

    public boolean register(String kennung, String password) {
        try {
            this.writer.append("\nkennung_" + kennung + "_password_" + password);
            this.writer.flush();
            this.users.add(new User(kennung, password));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while register");
            return false;
        }
    }

    public void adduser(User user) {
        if (user != null)
            this.users.add(user);
    }

    public ArrayList<User> toUserArray(String string) {
        ArrayList<User> list = new ArrayList<>();
        if (string.contains(",")) {
            String akt;
            while (string.length() > 0) {
                if (string.contains(",")) {
                    akt = string.substring(0, string.indexOf(','));
                } else {
                    akt = string;
                }
                User u = this.getUser(akt);
                if (u != null) {
                    list.add(u);
                } else {
                    return null;
                }
                if (string.contains(",")) {
                    string = string.substring(string.indexOf(',') + 1);
                } else {
                    string = "";
                }
            }
        } else {
            User u = this.getUser(string);
            if (u != null) {
                list.add(u);
            }
        }

        return list;
    }

    public ArrayList<String> toUserlist(User user) {
        ArrayList<String> list = new ArrayList<String>();
        for (User u : this.users) {
            if (user != u)
                list.add(u.getKennung());
        }
        return list;
    }

    public void onServerShutdown() {
        for (User u : this.users) {
            if (u.isOnline() && u.getConnection() == null) {
                u.logout();
            }
        }
    }
}