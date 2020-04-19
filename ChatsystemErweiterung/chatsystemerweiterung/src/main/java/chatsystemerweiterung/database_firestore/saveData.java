package chatsystemerweiterung.database_firestore;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import chatsystemerweiterung.rsa.Security;
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
    ArrayList<String> teilnehmer, userKennung;
    ArrayList<Message> messageList;
    Security security;

    // Nutze RSA zum Ver- und Entschlüsseln der Daten
    public saveData() {
        security = new Security();
    }

    // Methode zum Speichern der Daten
    public void saveChat(Message message, ArrayList<User> userList) {

        teilnehmer = new ArrayList<String>();

        this.msg = message;
        time = msg.getTime();
        sender = msg.getSender();
        partner = userList;
        text = this.msg.getText();
        type = this.msg.getType();

        for (User u : partner) {
            teilnehmer.add(u.getKennung());
        }

        String tmp = "";
        Collections.sort(teilnehmer);

        for (int i = 0; i < teilnehmer.size(); i++) {
            tmp = tmp + teilnehmer.get(i) + "_";
        }

        chatname = tmp;
        if (chatname != null && chatname.length() > 0 && chatname.charAt(chatname.length() - 1) == '_') {
            chatname = chatname.substring(0, chatname.length() - 1);
        }

        // DB-Verbindung
        try {
            // Pfad zur serviceAccount.json
            filepath = "";
            filepath = Paths.get("").toAbsolutePath().normalize().toString();
            if (!filepath.contains("ChatsystemErweiterung")) {
                filepath += "\\ChatsystemErweiterung";
            }
            filepath += "\\chatsystemerweiterung\\src\\main\\java\\chatsystemerweiterung\\database_firestore\\serviceAccountKey.json";

            FileInputStream serviceAccount = new FileInputStream(filepath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://distributedsystemstcpchat.firebaseio.com").build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Firestore db = FirestoreClient.getFirestore();

        Map<String, Boolean> exists = new HashMap<>();
        exists.put("exists", true);
        db.collection("chats").document(chatname).set(exists);

        // Schreibe Daten in die DB
        Map<String, Object> data = new HashMap<>();
        String datum = "" + time;
        data.put("text", text);
        data.put("sender", sender);
        data.put("time", datum);
        data.put("type", type);

        db.collection("chats").document(chatname).collection("nachrichten").document().set(data);

    }

    // Methode zum Laden und Zuweisen der Chats
    public void initChat(Users users) {

        partner = new ArrayList<User>();
        messageList = new ArrayList<>();

        // DB-Verbindung
        try {
            // Pfad zur serviceAccount.json
            filepath = "";
            filepath = Paths.get("").toAbsolutePath().normalize().toString();
            if (!filepath.contains("ChatsystemErweiterung")) {
                filepath += "\\ChatsystemErweiterung";
            }
            filepath += "\\chatsystemerweiterung\\src\\main\\java\\chatsystemerweiterung\\database_firestore\\serviceAccountKey.json";

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

        // Prüfe, ob Chat bereits vorhanden ist
        try {
            ApiFuture<QuerySnapshot> past = db.collection("chats").get();
            List<QueryDocumentSnapshot> documents = past.get().getDocuments();

            for (QueryDocumentSnapshot qds : documents) {

                String documentName = qds.getId();

                // Hole Daten aus der DB
                ApiFuture<QuerySnapshot> future = db.collection("chats").document(documentName)
                        .collection("nachrichten").get();
                List<QueryDocumentSnapshot> nachrichten = future.get().getDocuments();
                for (DocumentSnapshot document : nachrichten) {
                    map.put(document.getId(), document.getData());
                }
                for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
                    for (Map.Entry<String, Object> innerEntry : entry.getValue().entrySet()) {
                        // Parsen der Message
                        switch (innerEntry.getKey()) {
                            case "text":
                                this.text = (String) innerEntry.getValue();
                                break;
                            case "sender":
                                this.sender = (String) innerEntry.getValue();
                                break;
                            case "type":
                                this.type = (String) innerEntry.getValue();
                                break;
                            case "time":
                                // Bringe den Zeitstempel in das richtige Format
                                DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",
                                        Locale.ENGLISH);
                                this.time = dateFormat.parse((String) innerEntry.getValue());
                                break;
                        }
                    }
                    // Fügt die Message zu einer Liste mit allen Messages hinzu
                    messageList.add(parseToMessage(text, sender, type, time));
                }

                String chatteilnehmer = qds.getId();
                String[] einTeilnehmer = chatteilnehmer.split("_");
                if (einTeilnehmer.length != 0) {
                    for (String teilnehmer : einTeilnehmer) {
                        partner.add(users.getUser(teilnehmer));
                    }
                }
                Collections.sort(messageList);
                createChat(messageList, partner);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Parse die Attribute der DB zu einer Message
    public Message parseToMessage(String text, String sender, String type, Date time) {
        Message msg = new Message(sender, type, text, time);
        return this.security.decryptMessage(msg);
    }

    // Kreiert die Benutzerchats und weist diesen ihre Chatverläufe zu
    public void createChat(ArrayList<Message> messageList, ArrayList<User> users) {
        Chat chat = new Chat(users);
        chat.setChat(messageList);
        for (User tmp : users) {
            tmp.addChat(chat);
        }
    }

}
