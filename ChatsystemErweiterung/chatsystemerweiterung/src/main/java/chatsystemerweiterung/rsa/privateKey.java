package chatsystemerweiterung.rsa;

import java.math.BigInteger;

public class privateKey {
    BigInteger n;
    BigInteger d;

    public privateKey(BigInteger n, BigInteger d) {
        this.n = n;
        this.d = d;
    }

    public privateKey() {

    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getD() {
        return d;
    }
}