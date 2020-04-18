package chatsystemerweiterung.GUI;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewChat extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private ArrayList<String> users;
    private ArrayList<String> selected;
    private JPanel panel;
    private JScrollPane scroll;
    private JLabel names;
    private ChatOverview chatoverview;

    public NewChat(ArrayList<String> users, ChatOverview chatoverview) {
        this.users = users;
        this.chatoverview = chatoverview;
        this.selected = new ArrayList<>();
        // add label
        JLabel label = new JLabel("Click on a user to add / remove him from the new Chat.");
        label.setBounds(30, 10, 340, 30);
        this.add(label);
        this.names = new JLabel("New chat with: ");
        this.names.setBounds(30, 50, 340, 30);
        this.add(this.names);
        this.initscrollview();
        this.init();
    }

    private void init() {
        this.setLayout(null);
        this.setTitle("New chat");
        this.setSize(400, 450);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initscrollview() {
        // create panel
        this.panel = new JPanel(new GridBagLayout());
        GridBagConstraints constrains = new GridBagConstraints();
        constrains.insets = new Insets(0, 0, 2, 0);
        this.panel.setBounds(30, 90, 340, 260);
        for (int i = 0; i < this.users.size(); i++) {
            JButton tmp = new JButton(this.users.get(i));
            tmp.setPreferredSize(new Dimension(340, 50));
            tmp.addActionListener(this);
            constrains.gridx = 0;
            constrains.gridy = i;
            constrains.fill = GridBagConstraints.HORIZONTAL;
            this.panel.add(tmp, constrains);
        }
        // make scrollable
        this.scroll = new JScrollPane(this.panel);
        this.scroll.setBounds(30, 90, 340, 260);
        this.scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // add to fram
        this.add(this.scroll);
        // add confirm button
        JButton confirm = new JButton("Confirm");
        confirm.setBounds(150, 360, 100, 40);
        confirm.setBackground(new Color(50, 205, 50));
        confirm.setForeground(new Color(255, 255, 255));
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addChat();
            }
        });
        this.add(confirm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (selected.contains(e.getActionCommand())) {
            selected.remove(e.getActionCommand());
        } else {
            selected.add(e.getActionCommand());
        }
        updateLabel();
    }

    private void updateLabel() {
        String namesstring = "";
        for (String n : this.selected) {
            namesstring += n + ",";
        }
        if (namesstring.length() > 0) {
            namesstring = namesstring.substring(0, namesstring.lastIndexOf(','));
        }
        this.names.setText("New chat with: " + namesstring);
    }

    private void addChat() {
        String namesstring = "";
        for (String n : this.selected) {
            namesstring += n + ",";
        }
        if (namesstring.length() > 0) {
            namesstring = namesstring.substring(0, namesstring.lastIndexOf(','));
            this.chatoverview.addChat(namesstring);
            this.dispose();
        }
    }

}