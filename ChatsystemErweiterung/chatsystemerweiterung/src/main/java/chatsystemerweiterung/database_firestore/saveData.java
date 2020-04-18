package chatsystemerweiterung.database_firestore;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import chatsystemerweiterung.server.Message;
import chatsystemerweiterung.server.User;

public class saveData {

    String chatname;
    String sender, text, filepath;
    Date time;
    Message msg;
    ArrayList<User> partner;
    ArrayList<String> teilnehmer;

    public void saveChat(Message message, ArrayList<User> userList) {

        teilnehmer = new ArrayList<String>();
        System.out.println("Methode saveChat wurde aufgerufen");
        
        this.msg = message;
        time = msg.getTime();
        sender = msg.getSender();
        partner = userList;
        text = this.msg.getText();

        for(User u: partner) {
            teilnehmer.add(u.getKennung());
            System.out.println(u.getKennung());
        }

        // teilnehmer.add(sender);
        String tmp = "";
        Collections.sort(teilnehmer);
        
        for(int i = 0; i < teilnehmer.size(); i++) {
            tmp = tmp + teilnehmer.get(i) + "_";
            /* if(i == teilnehmer.size()-1) {
                tmp = tmp + teilnehmer.get(i);
            } */
        }

        chatname = tmp;
        if (chatname != null && chatname.length() > 0 && chatname.charAt(chatname.length() - 1) == '_') {
            chatname = chatname.substring(0, chatname.length() - 1);
        }

        System.out.println("Chatname: " + chatname);

        try {
            filepath = "";
            filepath = Paths.get("").toAbsolutePath().normalize().toString();
            if (!filepath.contains("ChatsystemErweiterung")) {
                filepath += "\\ChatsystemErweiterung";
            }
            filepath += "\\chatsystemerweiterung\\src\\main\\java\\chatsystemerweiterung\\database_firestore\\serviceAccountKey.json";
            // String path =
            // "src/main/java/chatsystemerweiterung/database_firestore/serviceAccountKey.json";

            FileInputStream serviceAccount = new FileInputStream(filepath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://distributedsystemstcpchat.firebaseio.com").build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Firestore db = FirestoreClient.getFirestore();

        // DocumentReference docRef1 = db.collection("chats").document(chatname).collection("nachrichten").document();

        Map<String, Object> data = new HashMap<>();
        data.put("timestmp", time);
        data.put("sender", sender);
        data.put("msg", text);

        db.collection("chats").document(chatname).collection("nachrichten").document().set(data);

        // asynchronously write data
        // ApiFuture<WriteResult> result = docRef1.set(data);
        // System.out.println(result.toString());

    }
    
}
