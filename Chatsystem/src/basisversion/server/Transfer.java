package basisversion.server;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Transfer extends Thread {

    private ObjectOutputStream oos;
    private Socket connection;
    private Users users;

    public Transfer(Socket connection, Users users) {
        this.connection = connection;
        this.users = users;
    }

    public void run() {
        try {
            for (int iu = 0; iu < this.users.getUsers().size(); iu++) {
                User user = this.users.getUsers().get(iu);
                for (int ic = 0; ic < user.getChats().size(); ic++) {
                    Chat chat = user.getChats().get(ic);
                    // user senden
                    // chatverlauf senden
                }
            }
            // mitteilen dass init zuende ist
            this.oos = new ObjectOutputStream(this.connection.getOutputStream());
            this.oos.writeObject(new Message("server", "DONE", "Done.", "time"));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private String chatuserstring(Chat chat) {
        String erg = "";
        for (int i = 0; i < chat.getUsers().size(); i++) {
            erg += chat.getUsers().get(i).getKennung() + "_";
        }
        return erg;
    }
}