package chatsystemerweiterung.rsa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import chatsystemerweiterung.server.Message;

public class Security {

    private BigInteger n, e, d;

    public Security() {
        String filepath = Paths.get("").toAbsolutePath().normalize().toString().contains("ChatsystemErweiterung")
                ? Paths.get("").toAbsolutePath().normalize().toString() + "\\keys.txt"
                : Paths.get("").toAbsolutePath().normalize().toString() + "\\ChatsystemErweiterung\\keys.txt";
        File file = new File(filepath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            n = new BigInteger(line);
            line = reader.readLine();
            e = new BigInteger(line);
            line = reader.readLine();
            d = new BigInteger(line);
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public BigInteger getN() {
        return this.n;
    }

    public BigInteger getE() {
        return this.e;
    }

    public BigInteger getD() {
        return this.d;
    }

    public Message encryptMessage(Message msg) {
        try {
            ArrayList<EncryThread> threads = new ArrayList<>();
            threads.add(new EncryThread(msg.getSender(), this.n, this.e));
            threads.add(new EncryThread(msg.getType(), this.n, this.e));
            threads.add(new EncryThread(msg.getText(), this.n, this.e));
            for (EncryThread e : threads) {
                e.start();
            }
            for (EncryThread e : threads) {
                e.join();
            }
            Message res = new Message(threads.get(0).encrypted, threads.get(1).encrypted, threads.get(2).encrypted,
                    new Date());
            return res;
        } catch (InterruptedException e) {
            System.err.println(e);
            return msg;
        }

    }

    public Message decryptMessage(Message msg) {
        try {
            ArrayList<DecryThread> threads = new ArrayList<>();
            threads.add(new DecryThread(msg.getSender(), this.n, this.d));
            threads.add(new DecryThread(msg.getType(), this.n, this.d));
            threads.add(new DecryThread(msg.getText(), this.n, this.d));
            for (DecryThread d : threads) {
                d.start();
            }
            for (DecryThread d : threads) {
                d.join();
            }
            Message res = new Message(threads.get(0).decrypted, threads.get(1).decrypted, threads.get(2).decrypted,
                    new Date());
            return res;
        } catch (InterruptedException e) {
            System.err.println(e);
            return msg;
        }
    }
}