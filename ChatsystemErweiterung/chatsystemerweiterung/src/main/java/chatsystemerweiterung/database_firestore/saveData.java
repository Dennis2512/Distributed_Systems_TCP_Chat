package chatsystemerweiterung.database_firestore;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import chatsystemerweiterung.GUI.NewChat;
import chatsystemerweiterung.server.Chat;
import chatsystemerweiterung.server.Message;
import chatsystemerweiterung.server.User;
import chatsystemerweiterung.server.Users;

public class saveData {

    String chatname;
    String sender, text, filepath, type;
    Date time;
    Message msg;
    ArrayList<User> partner;
    ArrayList<String> teilnehmer;
    ArrayList<Message> messageList;

    public saveData() {
        
    }

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

        String tmp = "";
        Collections.sort(teilnehmer);
        
        for(int i = 0; i < teilnehmer.size(); i++) {
            tmp = tmp + teilnehmer.get(i) + "_";
        }

        chatname = tmp;
        if (chatname != null && chatname.length() > 0 && chatname.charAt(chatname.length() - 1) == '_') {
            chatname = chatname.substring(0, chatname.length() - 1);
        }

        System.out.println("Chatname: " + chatname);

        // DB-Verbindung
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

        // Schreibe Daten in die DB
        Map<String, Object> data = new HashMap<>();
        data.put("text", text);
        data.put("sender", sender);
        data.put("timestmp", time);
        data.put("type", "MSG");

        db.collection("chats").document(chatname).collection("nachrichten").document().set(data);

    }

    public void initChat(Users users) {

        messageList = new ArrayList<Message>();

        // DB-Verbindung
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
        Map<String, Map<String, Object>> map = new HashMap<>();
        ArrayList<User> userList = users.getUsers();

        // Prüfe, ob Chat bereits vorhanden ist
        try {
            ApiFuture<QuerySnapshot> past = db.collection("chats").get();
            List<QueryDocumentSnapshot> documents = past.get().getDocuments();
            for(User u : userList) {
                for (QueryDocumentSnapshot qds : documents) {
                    if(qds.getId().contains(u.getKennung())) {

                        String documentName = qds.getId();

                        // Hole Daten aus der DB
                        try {
                            ApiFuture<QuerySnapshot> future =
                            db.collection("chats").document(documentName).collection("nachrichten").get();
                            List<QueryDocumentSnapshot> nachrichten = future.get().getDocuments();
                            for(DocumentSnapshot document : nachrichten) {
                                System.out.println("Gefundenes Dokument: " + document.getId());
                                map.put(document.getId(),document.getData());
                            }
                            for(Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
                                System.out.println("Schlüssel in Map: " + entry.getKey());
                                for(Map.Entry<String, Object> innerEntry : entry.getValue().entrySet()) {
                                    System.out.println(innerEntry.getKey() + " : " + innerEntry.getValue());
                                    // Parsen der Message
                                    switch(innerEntry.getKey()) {
                                        case "text":
                                            this.text = (String)innerEntry.getValue();
                                            break;
                                        case "sender":
                                            this.sender = (String)innerEntry.getValue();
                                            break;
                                        case "type":
                                            this.type = (String)innerEntry.getValue();
                                            break;
                                        case "timestmp":
                                            this.time = (Date)innerEntry.getValue();
                                            break;
                                    }
                                }
                                // Fügt die Message zu einer Liste mit allen Messages hinzu
                                Message tmpMsg = parseToMessage(text, sender, type, time);
                                messageList.add(tmpMsg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message parseToMessage(String text, String sender, String type, Date time) {
        Message msg = new Message(sender, type, text, time);
        return msg;
    }

    public void createChat(ArrayList<Message> messageList, ArrayList<User> users) {
        Chat chat = new Chat(users);
        chat.setChat(messageList);
        // ArrayList<Message> sortedChats = messageList;
    }
    
}
