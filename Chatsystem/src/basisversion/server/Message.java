package basisversion.server;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable, Comparable<Message> {
    private static final long serialVersionUID = -8432220740461476745L;

    private String text;
    private String sender;
    private Date time;
    private String type;

    public Message(String sender, String type, String text, Date time) {
        this.text = text;
        this.sender = sender;
        this.time = time;
        this.type = type;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    // getter

    public String getText() {
        return this.text;
    }

    public String getSender() {
        return this.sender;
    }

    public Date getTime() {
        return this.time;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public int compareTo(Message msg) {
        return this.time.compareTo(msg.getTime());
    }

}