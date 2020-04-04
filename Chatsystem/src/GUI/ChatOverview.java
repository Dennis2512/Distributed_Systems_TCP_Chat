package GUI;

import basisversion.server.*;
import lukastests.server.User;

import javax.swing.*;


public class ChatOverview extends JFrame {
    private static JPanel pnl_chatOverview;
    JTextField searchbarJTextField = new JTextField();

    public ChatOverview(){
        User[] testUser = new User[2];
        testUser[0] = new User("Kevin", "egal");
        testUser[1] = new User("Tom", "egal");
        JButton[] userButtons = new JButton[testUser.length];
        searchbarJTextField.setText("Suche...");
        for(int i = 0;i<userButtons.length;i++){
            userButtons[i].setText(testUser[i].getKennung());
        }
    }

}