package chatsystemerweiterung.GUI;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import chatsystemerweiterung.rsa.Security;
import chatsystemerweiterung.server.Message;

import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class ChatOverview extends JFrame implements ActionListener {
    private static final long serialVersionUID = -6596937811705258413L;

    private Socket connection;
    private String user, password;
    private ArrayList<String> chats;
    private JPanel panel;
    private JScrollPane scroll;
    private JButton newchat;
    private Security security;
    private ArrayList<String> users;

    public ChatOverview(Socket connection, String user, String password, ArrayList<String> chats,
            ArrayList<String> users) {
        this.connection = connection;
        this.security = new Security();
        this.user = user;
        this.password = password;
        this.chats = chats;
        this.users = users;
        // initinalisiere btnlist
        this.initnewchat();
        this.initscrollview();
        this.init();
    }

    private void init() {
        this.setLayout(null);
        this.setTitle("Select a chat");
        this.setSize(400, 470);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initscrollview() {
        // create panel
        this.panel = new JPanel(new GridBagLayout());
        GridBagConstraints constrains = new GridBagConstraints();
        constrains.insets = new Insets(0, 0, 2, 0);
        this.panel.setBounds(30, 105, 340, 260);
        for (int i = 0; i < this.chats.size(); i++) {
            JButton tmp = new JButton("Chat with: " + this.chats.get(i));
            tmp.setPreferredSize(new Dimension(340, 50));
            tmp.addActionListener(this);
            constrains.gridx = 0;
            constrains.gridy = i;
            constrains.fill = GridBagConstraints.HORIZONTAL;
            this.panel.add(tmp, constrains);
        }
        // make scrollable
        this.scroll = new JScrollPane(this.panel);
        this.scroll.setBounds(30, 105, 340, 260);
        this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // add to fram
        this.add(this.scroll);
    }

    private void initnewchat() {
        this.newchat = new JButton("New chat");
        this.newchat.setBounds(50, 20, 300, 50);
        this.newchat.setBackground(new Color(50, 205, 50));
        this.newchat.setForeground(new Color(255, 255, 255));
        this.newchat.setFont(new Font("Calibri", Font.BOLD, 20));
        ChatOverview c = this;
        this.newchat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new NewChat(users, c);
            }

        });
        this.add(this.newchat);

        // init label for panel

        JLabel label = new JLabel("Your chats: ");
        label.setBounds(30, 75, 340, 30);
        label.setHorizontalAlignment(JLabel.CENTER);
        this.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String names = e.getActionCommand();
        names = names.substring(11);
        try {
            onChatClicked(names);
        } catch (IOException err) {
            try {
                connection = new Socket("localhost", connection.getPort() == 187 ? 188 : 187);
                relogin();
                onChatClicked(names);
            } catch (Exception errr) {
                System.out.println("You were logged off.");
                System.exit(0);
            }
        } catch (ClassNotFoundException err) {
            err.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void onChatClicked(String names) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
        oos.writeObject(this.security.encryptMessage(new Message(user, "CONNECT", names, new Date())));
        ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
        Message ans = this.security.decryptMessage((Message) ois.readObject());
        if (ans.getType().equals("LOADCHAT")) {
            ois = new ObjectInputStream(this.connection.getInputStream());
            ArrayList<Message> chat = (ArrayList<Message>) ois.readObject();
            new ChatFenster(this.connection, this.user, this.password, chat, names, chats, users);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, ans.getText());
        }

    }

    public void addChat(String chat) {
        try {
            this.onAddChat(chat);
        } catch (IOException e) {
            try {
                // Verbindung zu Server verloren
                // Verbindung zu neuem Server aufbauen und erneut anmelden
                this.connection = new Socket("localhost", connection.getPort() == 187 ? 188 : 187);
                this.relogin();
                this.onAddChat(chat);
            } catch (Exception err) {
                System.out.println("Servers went offline.");
                System.exit(0);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void onAddChat(String chat) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(this.connection.getOutputStream());
        oos.writeObject(this.security.encryptMessage(new Message(this.user, "CONNECT", chat, new Date())));
        ObjectInputStream ois = new ObjectInputStream(this.connection.getInputStream());
        Message msg = this.security.decryptMessage((Message) ois.readObject());
        if (msg.getType().equals("NEWCHAT")) {
            this.chats.add(chat);
            new ChatFenster(this.connection, this.user, this.password, null, chat, chats, users);
            this.dispose();
        } else if (msg.getType().equals("LOADCHAT")) {
            ois = new ObjectInputStream(this.connection.getInputStream());
            ArrayList<Message> chatverlauf = (ArrayList<Message>) ois.readObject();
            new ChatFenster(this.connection, this.user, this.password, chatverlauf, chat, chats, users);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, msg.getText());
        }
    }

    @SuppressWarnings("unchecked")
    private void relogin() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(this.connection.getOutputStream());
        oos.writeObject(this.security.encryptMessage(new Message(this.user, "LOGIN", this.password, new Date())));
        ObjectInputStream ois = new ObjectInputStream(this.connection.getInputStream());
        Message msg = this.security.decryptMessage((Message) ois.readObject());
        if (msg.getType().equals("SUCCESS")) {
            ois = new ObjectInputStream(this.connection.getInputStream());
            this.chats = (ArrayList<String>) ois.readObject();
            ois = new ObjectInputStream(this.connection.getInputStream());
            this.users = (ArrayList<String>) ois.readObject();
        } else {
            System.out.println("You were logged off.");
            System.exit(0);
        }
    }

}