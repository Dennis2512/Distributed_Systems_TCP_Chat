package chatsystemerweiterung.GUI;

import chatsystemerweiterung.server.User;
import java.awt.GridLayout;

import javax.swing.*;

public class ChatOverview extends JFrame {
    private static final long serialVersionUID = 6833629384295621602L;

    private static JPanel pnl_chatOverview;
    private static JTextField searchbarJTextField;

    public ChatOverview() {
        this.getContentPane().add(pnl_chatOverview);
        this.setSize(200, 800);
        this.setVisible(true);

    }

    public void build() {
        searchbarJTextField = new JTextField();
        User[] testUser = new User[2];
        testUser[0] = new User("Kevin", "egal");
        testUser[1] = new User("Tom", "egal");
        JButton[] userButtons = new JButton[testUser.length];
        searchbarJTextField.setText("Suche...");
        pnl_chatOverview = new JPanel(new GridLayout(testUser.length + 1, 1));
        pnl_chatOverview.add(searchbarJTextField);
        for (int i = 0; i < userButtons.length; i++) {
            userButtons[i] = new JButton();
            userButtons[i].setText(testUser[i].getKennung());
            pnl_chatOverview.add(userButtons[i]);
        }
        ChatOverview newChatO = new ChatOverview();
    }

    // public static void main(String[] args) {

    // User[] testUser = new User[2];
    // testUser[0] = new User("Kevin", "egal");
    // testUser[1] = new User("Tom", "egal");
    // JButton[] userButtons = new JButton[testUser.length];
    // searchbarJTextField.setText("Suche...");
    // pnl_chatOverview = new JPanel(new GridLayout(testUser.length + 1, 1));
    // pnl_chatOverview.add(searchbarJTextField);
    // for (int i = 0; i < userButtons.length; i++) {
    // userButtons[i] = new JButton();
    // userButtons[i].setText(testUser[i].getKennung());
    // pnl_chatOverview.add(userButtons[i]);
    // }
    // ChatOverview newChatO = new ChatOverview();
    // }

}