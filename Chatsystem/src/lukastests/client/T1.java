package lukastests.client;

import java.io.*;

public class T1 extends Thread {
    private BufferedReader in;

    public T1(BufferedReader in) {
        this.in = in;
    }

    public void run() {
        while (true) {
            try {
                System.out.println(System.in.available());
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}