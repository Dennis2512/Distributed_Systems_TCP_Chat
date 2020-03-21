package lukastests;

import java.net.*;
import java.io.*;
import java.util.*;

public class Users {
    private User[] users;

    public Users() {
        users[0] = new User("LB", "LB_pass");
        users[1] = new User("TB", "TB_pass");
    }

    public User[] getUsers() {
        return this.users;
    }
}