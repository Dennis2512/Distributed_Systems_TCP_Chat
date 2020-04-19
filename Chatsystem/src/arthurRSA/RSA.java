package arthurRSA;

import java.math.BigInteger;
import java.util.Random;
import java.lang.Exception;

public class RSA {

    public static String encry(String word, BigInteger n, BigInteger e){
        String wordPrep = "";
        //System.out.println(word);
        for(int i = 0;i<word.length();i++){
            int cacheInt = (int)word.charAt(i)+10000;
            wordPrep = wordPrep+(cacheInt);
            //System.out.println("Wordprep: "+wordPrep);
        }
        BigInteger wordPasInt = new BigInteger(wordPrep);
        //System.out.println("wordPasInt: "+wordPasInt);
        //System.out.println("Verschlüsselt: "+wordPasInt.modPow(e,n));
        //System.out.println("STOP");
        return ""+wordPasInt.modPow(e,n);
    }

    public static String decry(String word, BigInteger n, BigInteger d){
        BigInteger wordAsInt = new BigInteger(word);
        word = ""+wordAsInt.modPow(d,n);
        //System.out.println(word);
        String cacheWord = "";
        for(int i = 0;i<word.length();i++){
            try{
            char char1 = word.charAt(i);
            char char2 = word.charAt(i+1);
            char char3 = word.charAt(i+2);
            char char4 = word.charAt(i+3);
            char char5 = word.charAt(i+4);
            //System.out.println("Chars: "+char1+char2+char3);
            String chars = ""+char1+char2+char3+char4+char5;
            //System.out.println("Chars: "+chars);
            int letter = Integer.parseInt(chars)-10000;
            //System.out.println("Letters: "+letter);
            char cacheChar = (char)letter;
            //System.out.println("CacheCahr: "+cacheChar);
            cacheWord = cacheWord+cacheChar;
            i = i+4;
            }
            catch(Exception e){

            }
            
        }
        return cacheWord;
    }

    public static BigInteger getPrime(){
        BigInteger p = BigInteger.probablePrime(4096,new Random());
        //BigInteger p = BigInteger.probablePrime(2048,new Random());
        while (!testPrime(p)){
            p = BigInteger.probablePrime(4096,new Random());
            //p = BigInteger.probablePrime(2048,new Random());
        }
        return p;
    }

    public static boolean testPrime(BigInteger p){
        try{
            String testWord = "INSERT A LONG TEXT 69";
            BigInteger q = new BigInteger("702840629101728897372658230595151274591809019013213833451753046492665748003158679191323326278006400113587506404173482714998275024504866852629349390839289051003022118556528647119511876059470609996943152713974386205020767115991663517369196587076015499984563013724585395870286151241808965701616689392937795185395908726882409613819692805524189704315533599478967037590460012059852370520387554784315707884039750098394922763978250125694253803949358374859542336268863080446075805810737563646527457615107764635938433693383706118307988474164679813384124497783914414564225073535680136026283616844934675226372685479994189054026486376470280292535727651108845312871469161149892892383151981769853914605700334305035817960693854290372494145933064557322010153295176601110272603113172942128121722692929012546002182074229690076576646807773310087508731424831900152879868133078459721174360980469175920408977970139060518846121138745711977856243358487599792401903507156330208354082531877255330805774353483851381306271364265961671062206339150182188354491650695117906427018664209299481174664089161105197860338097734496105413402490392476928414914912206461998351677359804377747432811237332074144360222539455623196404679379002300659038937942017515132950286589441");
            BigInteger n = getN(p, q);
            BigInteger e = getE(p, q);
            BigInteger d = getD(p, q, e);
            if(testWord.equals(decry(encry(testWord, n, e), n, d))){
                return true;
            }
            return false;
        }
        catch(Exception e){
            return false;
        }
    }

    public static BigInteger getE(BigInteger p, BigInteger q){
        BigInteger e = (BigInteger.probablePrime(1024,new Random())).add(new BigInteger("1"));
        do{
            e = e.add(new BigInteger("1"));
        }while (!testE(p,q,e) || !testESecure(p,q,e));
        return e;
    }

    public static boolean testE(BigInteger p,BigInteger q,BigInteger e){
        BigInteger phi = (p.subtract(BigInteger.valueOf(0))).multiply(q.subtract(BigInteger.valueOf(0)));
        if(phi.gcd(e).equals(new BigInteger("1"))){
            return true;
        }
        return false;
    }

    public static boolean testESecure(BigInteger p, BigInteger q,BigInteger e){
        BigInteger n = getN(p, q);
        BigInteger d = getD(p, q, e);
        String testWord = "INSERT ANOTHER TEXT.";
        if(testWord.equals(decry(encry(testWord, n, e), n, d))){
            return true;
        }
        return false;
    }

    public static BigInteger getD(BigInteger p, BigInteger q,BigInteger e){
        BigInteger phi = (p.subtract(BigInteger.valueOf(1))).multiply(q.subtract(BigInteger.valueOf(1)));
        return phi.add(inverses.fkt(phi,e));
    }

    public static BigInteger getN(BigInteger p, BigInteger q){
        BigInteger n = p.multiply(q);
        return n;
    }

    static Euklid inverses = (BigInteger qe, BigInteger qe2) -> {
        BigInteger u = new BigInteger("0");
        BigInteger u2 = new BigInteger("1");
        BigInteger r = new BigInteger("1");
        BigInteger r2;
        BigInteger cache;
        final BigInteger seEEA = qe2;
        while (!qe2.equals(new BigInteger("0"))){
            r2 = r;
            r = qe.subtract(qe.mod(qe2)).divide(qe2);
            cache = qe2;
            qe2 = qe.mod(qe2);
            qe = cache;
            if(!qe.equals(seEEA)){
                //erweiterung des EA
                cache = u2;
                u2 = u.subtract(r2.multiply(u2));
                u = cache;
            }
        }
        return u2;
    };
    interface Euklid {
        BigInteger fkt(BigInteger qe, BigInteger qe2);
    }
}
