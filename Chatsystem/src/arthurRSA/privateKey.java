package arthurRSA;

import java.math.BigInteger;

public class privateKey{
    BigInteger n;
    BigInteger d;

    privateKey(BigInteger n, BigInteger d){
        this.n = n;
        this.d = d;
    }

    privateKey(){
        
    }

    public BigInteger getN(){
        return n;
    }

    public BigInteger getD(){
        return d;
    }
}