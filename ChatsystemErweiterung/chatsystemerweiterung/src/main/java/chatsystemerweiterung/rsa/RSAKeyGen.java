package chatsystemerweiterung.rsa;

import java.math.BigInteger;

public class RSAKeyGen {
    BigInteger p = RSA.getPrime();
    BigInteger q = RSA.getPrime();
    BigInteger e = RSA.getE(p, q);
    BigInteger n = RSA.getN(p, q);
    BigInteger d = RSA.getD(p, q, e);
    boolean generated = false;

    private void generateAndTestKeys() {
        String testWord = "Das ist ein Test. Daher: Hallo!";
        String result = "";
        do {
            result = RSA.encry(testWord, n, e);
            result = RSA.decry(result, n, d);
            if (!testWord.equals(result)) {
                p = RSA.getPrime();
                q = RSA.getPrime();
                e = RSA.getE(p, q);
                n = RSA.getN(p, q);
                d = RSA.getD(p, q, e);
            }
        } while (!testWord.equals(result));
        generated = true;
    }

    public privateKey getPrivateKey() {
        if (generated) {
            return new privateKey(n, d);
        }
        generateAndTestKeys();
        return getPrivateKey();

    }

    public publicKey getPublicKey() {
        if (generated) {
            return new publicKey(n, e);
        }
        generateAndTestKeys();
        return getPublicKey();
    }
}