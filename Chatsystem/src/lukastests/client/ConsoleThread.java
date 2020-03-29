package lukastests.client;

import java.io.*;

public class ConsoleThread extends Thread {

    private BufferedReader in;
    private PrintWriter out;
    private String kennung;
    private boolean stopped;

    public ConsoleThread(PrintWriter out, String kennung, BufferedReader in) {
        this.in = in;
        this.out = out;
        this.kennung = kennung;
        this.stopped = false;
    }

    public void run() {
        try {
            while (!stopped) {
                String input = this.in.readLine();
                if (input.equals(".leave")) {
                    this.stopped = true;
                    this.out.println("LEAVE_");
                } else if (input.equals(".exit")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    this.stopped = true;
                } else {
                    this.out.println("MSG_" + kennung + ": " + input);
                }

            }
        } catch (IOException e) {
            System.err.println(e);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public void kill() {
        this.stopped = true;
    }

}