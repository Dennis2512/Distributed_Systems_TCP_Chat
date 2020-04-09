package GUI;

import basisversion.server.*;
import lukastests.server.User;
import java.awt.GridLayout;

import javax.swing.*;


public class ChatOverview extends JFrame {
    private static JPanel pnl_chatOverview;
    private static JTextField searchbarJTextField = new JTextField();

    public ChatOverview(){
        this.getContentPane().add(pnl_chatOverview);
        this.setSize(200, 800);
        this.setVisible(true);
    }

    public static void main(String[] args){
        
        User[] testUser = new User[2];
        testUser[0] = new User("Kevin", "egal");
        testUser[1] = new User("Tom", "egal");
        JButton[] userButtons = new JButton[testUser.length];
        searchbarJTextField.setText("Suche...");
        pnl_chatOverview = new JPanel(new GridLayout(testUser.length+1, 1));
        pnl_chatOverview.add(searchbarJTextField);
        for(int i = 0;i<userButtons.length;i++){
            userButtons[i] = new JButton();
            userButtons[i].setText(testUser[i].getKennung());
            pnl_chatOverview.add(userButtons[i]);
        }
        ChatOverview newChatO = new ChatOverview();
    }

}