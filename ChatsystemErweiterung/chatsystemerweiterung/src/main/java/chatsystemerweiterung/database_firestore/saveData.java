package chatsystemerweiterung.database_firestore;

import java.io.FileInputStream;
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

    public static void main(String[] args) {

        try {

            String path = "src/main/java/chatsystemerweiterung/database_firestore/serviceAccountKey.json";

            FileInputStream serviceAccount = new FileInputStream(path);
        

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://distributedsystemstcpchat.firebaseio.com")
                .build();
        
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Firestore db = FirestoreClient.getFirestore();

        // Collection: chats, Document: chatroom_name
        DocumentReference docRef = db.collection("chats").document("dn_test");
        Map<String, Object> data = new HashMap<>();
        data.put("timestmp", "123456");
        data.put("sender", "Dennis");
        data.put("partner", "Lukas");
        data.put("msg", "Hallo du da");
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        
    }

}
