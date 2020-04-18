package lukastests.client;

import java.io.*;

public class IncomingThread extends Thread {

    private BufferedReader in;
    private boolean leave;

    public IncomingThread(BufferedReader in) {
        this.in = in;
        this.leave = false;
    }

    public void run() {
        try {
            while (!leave) {
                String input = in.readLine();
                // wenn die bekommene nachricht der ende des chats durch abmeldung ist, dann
                // wird dieser thread beendet
                if (input.equals("LEAVESUCCESS")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    System.out.println("Sie haben den Chat verlassen.");
                    this.leave = true;
                } else if (input.equals("PARTNERLEAVE")) {
                    System.out.println("Ihr Partner hat den Chat verlassen.");
                    this.leave = true;
                } else {
                    // ansonsten handelt es sich um eine nachricht, diese wird ausgegeben
                    System.out.println(input.substring(input.indexOf('_') + 1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}