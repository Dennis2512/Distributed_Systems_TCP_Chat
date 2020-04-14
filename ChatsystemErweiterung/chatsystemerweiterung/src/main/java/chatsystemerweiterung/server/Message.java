package chatsystemerweiterung.server;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = -8432220740461476745L;

    private String text;
    private String sender;
    private String time;
    private String type;

    public Message(String sender, String type, String text, String time) {
        this.text = text;
        this.sender = sender;
        this.time = time;
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // getter

    public String getText() {
        return this.text;
    }

    public String getSender() {
        return this.sender;
    }

    public String getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

}