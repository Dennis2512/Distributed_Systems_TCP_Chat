package lukastests.server;

import java.sql.*;
import java.util.*;
import java.io.*;

public class ChatLog {
        
    BufferedWriter logWriter;
    
    
    // 1. Schauen ob es einen Logdatei für die 2 Chatmitglieder gibt; falls name des Files sender und empfänger in beliebiger reihenfolge enthält
    // WENN JA
    //    Dann ChatItems hinzufügen
    // WENN NEIN
    //    Dann neue Datei erstellen und dann Chatitems hinzufügen
    
    
    // Create Chat Log by writing Chat History to JSON File
    public ChatLog(String msg, String sender_name, String partner_name) {

        // Remove Timestamps in basic version
        CreateTimestamp cTs = new CreateTimestamp();
        Timestamp timeStmp = cTs.getTimestamp();
        File senderFile = new File(".\\" + sender_name + "_" + partner_name + "_ChatLog.json");
        File partnerFile = new File(".\\" + partner_name+ "_" + sender_name + "_ChatLog.json");
        
        this.writeLog(senderFile, msg, timeStmp, sender_name, partner_name);
        this.writeLog(partnerFile, msg, timeStmp, sender_name, partner_name);

    }
    
    // Methode 1 Partner & Sender vergleichen mit Textdateinamen; ob es die Datei schon gibt. boolean --> Datei existiert ja / nein
    // Methode 2 ruft Methode 1 auf und 
    //   ENTWEDER neue Datei erstellen mit Header 
    //   ODER aus der alten Datei die letzten 2 Zeilen löschen und msg anfügen.

    /*private boolean doesFileExist (String filename, String sender_name, String partner_name){
        
        if(filename.contains(sender_name) && filename.contains(partner_name)){
            return true; 
        } else {
            return false;
        }
    }*/

    public void writeLog(File file, String msg, Timestamp timeStmp, String sender_name, String partner_name){
        // Fügt neue LogDatei hinzu falls benötigte noch nicht vorhanden
        if(!file.exists()){
            try {
                file.createNewFile();
                logWriter = new BufferedWriter(new FileWriter(file, true));
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
            //count lines --> von der Anzahl von lines kann man  Abziehen.

            // ArrayList[10]        10 = Anz lines
            // wenn Nachricht geschrieben wird: Arraylist[ (10-2) + 8 ]
            // neue Nachricht: Arraylist[ (16-2) + 8 ]
            // usw
            
            String line;
            try (FileReader myFileReader = new FileReader(file); BufferedReader myLineReader = new BufferedReader(myFileReader)){
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
            // Fügt das chatItem in die JSON Datei ein
            logWriter = new BufferedWriter(new FileWriter(file, true));
            
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
}


/*
try (
    BufferedReader br = new BufferedReader(new FileReader(resourcesFilePath));
    BufferedWriter writer = Files.newBufferedWriter(outputFilePath, charset);
) {
    String line;

    while ((line = br.readLine()) != null) {
        String repl = line.replaceAll("Orange:(.*)","OrangeAndApples:$1");
        writer.writeln(repl);
    }
}
*/