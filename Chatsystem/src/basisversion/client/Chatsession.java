package basisversion.client;

import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import javax.swing.*;

import basisversion.server.Message;

public class Chatsession extends JFrame implements ActionListener {

    private static final long serialVersionUID = -358835487197247689L;
    private ArrayList<Message> chat;
    private Socket connection;
    private JTextArea textarea;
    private JTextField textfield;
    private JLabel label;
    private JButton button;
    private String user, partner;
    private JScrollPane scroll;

    public Chatsession(ArrayList<Message> initchat, Socket connection, String user, String partner) {
        this.connection = connection;
        this.user = user;
        this.partner = partner;
        if (initchat == null) {
            this.chat = new ArrayList<Message>();
        } else {
            this.chat = initchat;
        }
        // textarea init
        this.textarea = new JTextArea();
        this.scroll = new JScrollPane(this.textarea);
        this.scroll.setBounds(20, 10, 350, 300);
        this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // this.scroll.getVerticalScrollBar().setValue(this.scroll.getVerticalScrollBar().getMinimum());
        this.add(this.scroll);
        // chat laden
        if (this.chat.size() > 0) {
            this.textarea.setText(this.chatToString(this.chat));
        }
        this.textarea.setBounds(20, 10, 350, 300);
        this.textarea.setEditable(false);
        this.textarea.setWrapStyleWord(true);
        this.textarea.setLineWrap(true);
        this.textarea.setCaretPosition(this.textarea.getDocument().getLength());
        // make it scrollable

        // label init
        this.label = new JLabel("Type your message here:");
        this.label.setBounds(20, 310, 350, 35);
        this.add(this.label);

        // message field init
        this.textfield = new JTextField();
        this.textfield.setBounds(20, 340, 350, 35);
        this.add(this.textfield);

        // send button init
        this.button = new JButton("Send");
        this.button.setBounds(20, 380, 350, 35);
        this.button.addActionListener(this);
        this.add(this.button);
        // window init
        this.setTitle("Chatroom mit " + this.partner);
        this.setSize(400, 465);
        this.setLayout(null);
        this.setLocation(400, 100);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // show window
        this.setVisible(true);

        // listening to messages
        Listen listen = new Listen(this.connection, this);
        listen.start();

        // telling server that i left the chat
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
                    oos.writeObject(new Message(user, "LEAVE", "", "time"));
                } catch (IOException err) {
                    System.err.println(err);
                }
            }
        });

    }

    // wenn der Button gedrücket wurde
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String text = this.textfield.getText();
            if (!text.equals("")) {
                ObjectOutputStream oos = new ObjectOutputStream(this.connection.getOutputStream());
                oos.writeObject(new Message(this.user, "MSG", this.textfield.getText(), "time"));
            }
        } catch (IOException err) {
            System.err.println(err);
        }
    }

    public String chatToString(ArrayList<Message> chat) {
        String value = "";
        for (int i = 0; i < chat.size(); i++) {
            Message tmp = chat.get(i);
            value = value + tmp.getTime() + " " + tmp.getSender() + ": " + tmp.getText() + '\n';
        }
        return value;
    }

    public void printMsg(Message msg) {
        this.textarea.append(msg.getTime() + " " + msg.getSender() + ": " + msg.getText() + '\n');
        this.textarea.setCaretPosition(this.textarea.getDocument().getLength());
    }

    public void sent(String text) {
        this.textarea.setText(this.textarea.getText() + text + '\n');
        this.textfield.setText("");
        this.textarea.setCaretPosition(this.textarea.getDocument().getLength());
    }
} // class