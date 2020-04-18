package chatsystemerweiterung.rsa;

import java.math.BigInteger;

public class DecryThread extends Thread {

    String encrypted;
    String decrypted;
    BigInteger n, d;

    public DecryThread(String text, BigInteger n, BigInteger d) {
        this.encrypted = text;
        this.n = n;
        this.d = d;
    }

    public void run() {
        this.decrypted = RSA.decry(this.encrypted, this.n, this.d);
    }
}