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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
            if (msg.getSender().length() > 0)
                threads.add(0, new EncryThread(msg.getSender(), this.n, this.e));
            else
                threads.add(0, null);
            if (msg.getType().length() > 0)
                threads.add(1, new EncryThread(msg.getType(), this.n, this.e));
            else
                threads.add(1, null);
            if (msg.getText().length() > 0)
                threads.add(2, new EncryThread(msg.getText(), this.n, this.e));
            else
                threads.add(2, null);
            for (EncryThread e : threads) {
                if (e != null)
                    e.start();
            }
            for (EncryThread e : threads) {
                if (e != null)
                    e.join();
            }
            String sender = threads.get(0) != null ? threads.get(0).encrypted : "";
            String type = threads.get(1) != null ? threads.get(1).encrypted : "";
            String text = threads.get(2) != null ? threads.get(2).encrypted : "";
            Message res = new Message(sender, type, text, new Date());
            return res;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return msg;
        }

    }

    public Message decryptMessage(Message msg) {
        try {
            ArrayList<DecryThread> threads = new ArrayList<>();
            if (msg.getSender().length() > 0)
                threads.add(0, new DecryThread(msg.getSender(), this.n, this.d));
            else
                threads.add(0, null);
            if (msg.getType().length() > 0)
                threads.add(1, new DecryThread(msg.getType(), this.n, this.d));
            else
                threads.add(1, null);
            if (msg.getText().length() > 0)
                threads.add(2, new DecryThread(msg.getText(), this.n, this.d));
            else
                threads.add(2, null);
            for (DecryThread d : threads) {
                if (d != null)
                    d.start();
            }
            for (DecryThread d : threads) {
                if (d != null)
                    d.join();
            }
            String sender = threads.get(0) != null ? threads.get(0).decrypted : "";
            String type = threads.get(1) != null ? threads.get(1).decrypted : "";
            String text = threads.get(2) != null ? threads.get(2).decrypted : "";
            Message res = new Message(sender, type, text, new Date());
            return res;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return msg;
        }
    }
}