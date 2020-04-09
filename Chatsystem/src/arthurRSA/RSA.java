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


    public static String TextToUnicode(String word){
       
        return word;
    }

    public static String UnicodeToText(String word){
        return null;
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
        while (!testPrime(p)){
            p = BigInteger.probablePrime(4096,new Random());
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
        }while (!testE(p,q,e) || !testESecure(e));
        return e;
    }

    public static boolean testE(BigInteger p,BigInteger q,BigInteger e){
        BigInteger phi = (p.subtract(BigInteger.valueOf(0))).multiply(q.subtract(BigInteger.valueOf(0)));
        if(phi.gcd(e).equals(new BigInteger("1"))){
            return true;
        }
        return false;
    }

    public static boolean testESecure(BigInteger e){
        BigInteger q = new BigInteger("547249375320001591218372353449071068751591574780192229675901359924876412558895798936528856303264669024198277785661578560982079964789966886877313062556069690340220275806875891014714760267409868085514348264632543954173882779873829928915189975071658671638765481503945969528187229795518021191531656665470526022856743616393131145163695025180010376251272051475422836143990963463671257378866029676897240889474593958536718590824871473494904597219053699319224754101236639262440746474077969888659565411124076482979329026798698233882774647078793269747468225557911604334813307403812175812103888896374446702429569070319460368320056487076048079420991602225991447696422820700125808044397756828206645400750997657668707758263659130308749097932925830823088699413351994674468564257823930024377513286481674464057295198780039488838159903424556447101890053238735645167980778838885935056911905111730891076386456548743163956793671307147616406218402653808001248079649723144322969177305454075112725810906216114030302222330108745869763778549066565666094359680256510739778122420991708406485186313521357275216428352825130169562440545600178709881456969680400357779700140141428555704706201730165107167117901912637862251202602383875139585250240393355169617381420311");
        BigInteger p = new BigInteger("702840629101728897372658230595151274591809019013213833451753046492665748003158679191323326278006400113587506404173482714998275024504866852629349390839289051003022118556528647119511876059470609996943152713974386205020767115991663517369196587076015499984563013724585395870286151241808965701616689392937795185395908726882409613819692805524189704315533599478967037590460012059852370520387554784315707884039750098394922763978250125694253803949358374859542336268863080446075805810737563646527457615107764635938433693383706118307988474164679813384124497783914414564225073535680136026283616844934675226372685479994189054026486376470280292535727651108845312871469161149892892383151981769853914605700334305035817960693854290372494145933064557322010153295176601110272603113172942128121722692929012546002182074229690076576646807773310087508731424831900152879868133078459721174360980469175920408977970139060518846121138745711977856243358487599792401903507156330208354082531877255330805774353483851381306271364265961671062206339150182188354491650695117906427018664209299481174664089161105197860338097734496105413402490392476928414914912206461998351677359804377747432811237332074144360222539455623196404679379002300659038937942017515132950286589441");
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
