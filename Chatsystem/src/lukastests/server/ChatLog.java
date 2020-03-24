package lukastests.server;

import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class ChatLog {

    /*
    1. Schauen ob es einen Logdatei für die 2 Chatmitglieder gibt; falls name des Files sender und empfänger in beliebiger reihenfolge enthält
    WENN JA
        Dann ChatItems hinzufügen
    WENN NEIN
        Dann neue Datei erstellen und dann Chatitems hinzufügen
    */
    // Create Chat Log by writing Chat History to JSON File
    public ChatLog(String msg, String sender_name, String partner_name) {

        // Remove Timestamps in basic version
        CreateTimestamp cTs = new CreateTimestamp();
        Timestamp timeStmp = cTs.getTimestamp();
        File senderFile = new File(".\\" + sender_name + "_" + partner_name + "_ChatLog.json");
        File partnerFile = new File(".\\" + partner_name+ "_" + sender_name + "_ChatLog.json");
        BufferedWriter logWriter;
        
        
       // if(myTextFile.name == sender_name + "_" + partner_name + "_ChatLog.json"|| myTextFile.name == partner_name + "_" + sender_name + "_ChatLog.json");
       // DANN: append
       // Ansonsten ist es ein neuer Chat zwischen anderen Clienten
       // ALSO: create New JSON File


       if(senderFile.getName().contains(partner_name) && senderFile.getName().contains(sender_name)){
               
            // Löscht das ende der Dateil 
            try (FileReader myFileReader = new FileReader(senderFile); BufferedReader myLineReader = new BufferedReader(myFileReader)){

                String line;
                while((line = myLineReader.readLine()) != null){
                    if(line == "]"){
                        line.replace("]", " ");
                        line = myLineReader.readLine();
                        line.replace("}", " ");
                    }
                    System.out.println(line);
                }
    
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
            // Fügt das chatItem in die JSON Datei ein
            logWriter = new BufferedWriter(new FileWriter(senderFile, true));
            logWriter.append("{" + '\n');
            logWriter.append("\"chatItems\": [" + '\n');
            logWriter.append("{" + '\n');
            logWriter.append("\"time\": " + "\"" + timeStmp + "\"," + '\n');
            logWriter.append("\"sender_name\": " + "\"" + sender_name + "\"," + '\n');
            logWriter.append("\"partner_name\": " + "\"" + partner_name + "\"," + '\n');
            // logWriter.append("\"group_name\": " + "\"" + group_name + "\"," + '\n');
            logWriter.append("\"msg\": " + "\"" + msg + "\"" + '\n');
            logWriter.append("}" + '\n');
            logWriter.append("]" + '\n');
            logWriter.append("}" + '\n');
            logWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try{
            logWriter = new BufferedWriter(new FileWriter(partnerFile, true));
            logWriter.append("{" + '\n');
            logWriter.append("\"chatItems\": [" + '\n');
            logWriter.append("{" + '\n');
            logWriter.append("\"time\": " + "\"" + timeStmp + "\"," + '\n');
            logWriter.append("\"sender_name\": " + "\"" + sender_name + "\"," + '\n');
            logWriter.append("\"partner_name\": " + "\"" + partner_name + "\"," + '\n');
            // logWriter.append("\"group_name\": " + "\"" + group_name + "\"," + '\n');
            logWriter.append("\"msg\": " + "\"" + msg + "\"" + '\n');
            logWriter.append("}" + '\n');
            logWriter.append("]" + '\n');
            logWriter.append("}" + '\n');
            logWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                senderFile.createNewFile();
                logWriter = new BufferedWriter(new FileWriter(senderFile, true));
                logWriter.append(",{" + '\n');
                logWriter.append("\"time\": " + "\"" + timeStmp + "\"," + '\n');
                logWriter.append("\"sender_name\": " + "\"" + sender_name + "\"," + '\n');
                logWriter.append("\"partner_name\": " + "\"" + partner_name + "\"," + '\n');
                // logWriter.append("\"group_name\": " + "\"" + group_name + "\"," + '\n');
                logWriter.append("\"msg\": " + "\"" + msg + "\"" + '\n');
                logWriter.append("}" + '\n');
                logWriter.append("]" + '\n');
                logWriter.append("}" + '\n');
                logWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }    

            try {
                partnerFile.createNewFile();
                logWriter = new BufferedWriter(new FileWriter(partner_name, true));
                logWriter.append(",{" + '\n');
                logWriter.append("\"time\": " + "\"" + timeStmp + "\"," + '\n');
                logWriter.append("\"sender_name\": " + "\"" + sender_name + "\"," + '\n');
                logWriter.append("\"partner_name\": " + "\"" + partner_name + "\"," + '\n');
                // logWriter.append("\"group_name\": " + "\"" + group_name + "\"," + '\n');
                logWriter.append("\"msg\": " + "\"" + msg + "\"" + '\n');
                logWriter.append("}" + '\n');
                logWriter.append("]" + '\n');
                logWriter.append("}" + '\n');
                logWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }    

           }

    }
    //methode 1 Partner & Sender vergleichen mit Textdateinamen; ob es die Datei schon gibt. boolean --> Datei existiert ja nein
    //methode 2 ruft methode 1 auf und 
    //   ENTWEDER neue Datei erstellen mit header 
    //   ODER aus der alten Datei die letzten 2 zeilen löschen und message anfügen.
}
