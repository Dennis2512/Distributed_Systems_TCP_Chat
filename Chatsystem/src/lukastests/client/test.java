package lukastests.client;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        T1 t1 = new T1(console);
        t1.start();
        while (true) {
            String in = console.readLine();
            Thread.sleep(1000);
        }
    }

}
