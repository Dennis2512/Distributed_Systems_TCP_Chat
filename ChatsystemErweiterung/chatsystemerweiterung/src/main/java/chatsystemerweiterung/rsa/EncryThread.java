package chatsystemerweiterung.rsa;

import java.math.BigInteger;

public class EncryThread extends Thread {

    String encrypted;
    String decrypted;
    BigInteger n, e;

    public EncryThread(String text, BigInteger n, BigInteger e) {
        this.decrypted = text;
        this.n = n;
        this.e = e;
    }

    public void run() {
        this.encrypted = RSA.encry(this.decrypted, this.n, this.e);
    }

}