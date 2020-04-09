
package arthurRSA;

import java.math.BigInteger;

public class publicKey{
    BigInteger n;
    BigInteger e;

    public publicKey(BigInteger n,BigInteger e){
        this.n = n;
        this.e = e;
    }

    public publicKey(){

    }

    public BigInteger getN(){
        return n;
    }

    public BigInteger getE(){
        return e;
    }
}