package chatsystemerweiterung.database_firestore;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class saveData {

    public void saveChat(String sender, String partner, String msg, String time) {
        try {
<<<<<<< HEAD
            String path = "./src/main/java/chatsystemerweiterung/database_firestore/serviceAccountKey.json";
=======
            String filepath = Paths.get("").toAbsolutePath().normalize().toString();
            if (!filepath.contains("ChatsystemErweiterung")) {
                filepath += "\\ChatsystemErweiterung";
            }
            filepath += "\\chatsystemerweiterung\\src\\main\\java\\chatsystemerweiterung\\database_firestore\\serviceAccountKey.json";
            // String path =
            // "src/main/java/chatsystemerweiterung/database_firestore/serviceAccountKey.json";
>>>>>>> ae028b660d9d3ce5a667d2cb1093b022ffa8818a

            FileInputStream serviceAccount = new FileInputStream(filepath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://distributedsystemstcpchat.firebaseio.com").build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Firestore db = FirestoreClient.getFirestore();

        // Collection: chats, Document: chatroom_name
        DocumentReference docRef1 = db.collection("chats").document(sender + "_" + partner);
        Map<String, Object> data = new HashMap<>();
        data.put("timestmp", time);
        data.put("sender", sender);
        data.put("partner", partner);
        data.put("msg", msg);

        // zweiter Chatpartner daten
        DocumentReference docRef2 = db.collection("chats").document(sender + "_" + partner);
        Map<String, Object> data2 = new HashMap<>();
        data2.put("timestmp", time);
        data2.put("sender", sender);
        data2.put("partner", partner);
        data2.put("msg", msg);

        // asynchronously write data
        ApiFuture<WriteResult> result = docRef1.set(data);
        System.out.println(result.toString());

        ApiFuture<WriteResult> result2 = docRef2.set(data);
        System.out.println(result2.toString());
    }

    // public static void main(String[] args) {

    // try {

    // String path = "/Users/julie/Documents/DHBW/Verteilte
    // Systeme/Distributed_Systems_TCP_Chat/ChatsystemErweiterung/chatsystemerweiterung/src/main/java/chatsystemerweiterung/database_firestore/serviceAccountKey.json";

    // FileInputStream serviceAccount = new FileInputStream(path);

    // FirebaseOptions options = new FirebaseOptions.Builder()
    // .setCredentials(GoogleCredentials.fromStream(serviceAccount))
    // .setDatabaseUrl("https://distributedsystemstcpchat.firebaseio.com").build();

    // FirebaseApp.initializeApp(options);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // Firestore db = FirestoreClient.getFirestore();

    // // Collection: chats, Document: chatroom_name
    // DocumentReference docRef = db.collection("chats").document("dn_test");
    // Map<String, Object> data = new HashMap<>();
    // data.put("timestmp", "123456");
    // data.put("sender", "Dennis");
    // data.put("partner", "Lukas");
    // data.put("msg", "Hallo du da");
    // // asynchronously write data
    // ApiFuture<WriteResult> result = docRef.set(data);
    // System.out.println(result.toString());
    // }

}
