package GUI;

import basisversion.server.*;
import lukastests.server.User;
import java.awt.GridLayout;

import javax.swing.*;


public class ChatOverview extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 5214320821190048612L;
    private static JPanel pnl_chatOverview;
    private static JTextField searchbarJTextField = new JTextField();
    private static JButton addUser = new JButton();
    private static JButton createGroup = new JButton();
    public ChatOverview(int anzUser){
        this.getContentPane().add(pnl_chatOverview);
        this.setSize(200, (3*40)+80);
        this.setVisible(true);
    }

    public static void main(String[] args){
        
        User[] testUser = new User[2];
        testUser[0] = new User("Kevin", "egal");
        testUser[1] = new User("Tom", "egal");
        JButton[] userButtons = new JButton[testUser.length];
        searchbarJTextField.setText("Nuter hinzufügen.");
        addUser.setText("+");
        createGroup.setText("Gruppe erstellen");
        pnl_chatOverview = new JPanel(new GridLayout(testUser.length+3, 1));
        pnl_chatOverview.add(searchbarJTextField);
        pnl_chatOverview.add(addUser);
        pnl_chatOverview.add(createGroup);
        for(int i = 0;i<userButtons.length;i++){
            userButtons[i] = new JButton();
            userButtons[i].setText(testUser[i].getKennung());
            pnl_chatOverview.add(userButtons[i]);
        }
        ChatOverview newChatO = new ChatOverview(testUser.length);
    }

}