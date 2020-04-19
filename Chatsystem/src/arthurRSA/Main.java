package arthurRSA;

public class Main {
    public static void main(String[] args) {
        testRSA();
    }

    public static void testRSA(){
        int n = 1;
        int m = 0;
        while(true){
            RSAKeyGen generator = new RSAKeyGen();
        publicKey keyA = generator.getPublicKey();
        System.out.println("n: "+keyA.getN());
        System.out.println("E: "+keyA.getE());
        privateKey keyB = generator.getPrivateKey();
        String testWord = "Hier ist ein Testtext Oki😌🖤♡♥❤❧";
        System.out.println(testWord+"<---------------------------------------------------------------");
        String encryWord = RSA.encry(testWord, keyA.getN(), keyA.getE());
        System.out.println(encryWord+"<---------------------------------------------------------------");
        String decryWord = RSA.decry(encryWord, keyB.getN(), keyB.getD());
        System.out.println(decryWord+"<---------------------------------------------------------------");
        if(!testWord.equals(decryWord)){
            m++;
        }
        System.out.println("Round: "+n+" | Fehlschläge: "+m);
        n++;
        }
    }
}
