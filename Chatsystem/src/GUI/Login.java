package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;


public class Login extends JFrame {

    private static JLabel lbl_kennung;
    private  static JLabel lbl_password;
    private static JLabel lbl_header;
    private static JTextField tf_kennung;
    private static JPasswordField tf_password;
    private static JPanel pnl_mainWindow;
    private static JPanel pnl_header;
    private static JPanel pnl_center;
    private static JPanel pnl_bottom;
    private static JPanel pnl_leftCenter;
    private static JPanel pnl_rightCenter;
    private static JPanel pnl_middleCenter;
    private static JFrame loginFenster;
    protected static JButton btn_login;
    protected static JButton btn_register;
    protected static JButton btn_clear;
    private static JPanel pnl_gridCenterFirstTop;
    private static JPanel pnl_gridCenterSecondTop;
    private static JPanel pnl_gridCenterThirdTop;
    private static JPanel pnl_gridCenterFourthTop;
    private static Border blackborder;
    
    
    public static void main(String[] args) {
        loginFenster = new JFrame();
        loginFenster.setTitle("Login Fenster");
        loginFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFenster.getContentPane().setBackground(new Color(119, 136, 153));
       // pnl_mainWindow = new JPanel(new BorderLayout());
        blackborder = BorderFactory.createLineBorder(new Color(46, 139, 87), 2);
   
        pnl_mainWindow = new JPanel(new GridLayout(3, 1));
        pnl_header = new JPanel(new GridBagLayout());
        pnl_center = new JPanel(new GridLayout(1, 1));
        pnl_leftCenter =  new JPanel(new GridLayout(1, 1));
        pnl_middleCenter = new JPanel(new GridLayout(4, 1));
        pnl_rightCenter = new JPanel(new GridLayout(1, 1));
        
        
        pnl_gridCenterFirstTop = new JPanel(new GridLayout(1, 1));
        pnl_gridCenterSecondTop = new JPanel(new GridLayout(1, 2));
        pnl_gridCenterThirdTop = new JPanel(new GridLayout(1, 1));
        pnl_gridCenterFourthTop = new JPanel(new GridLayout(1, 2));
        
   
        
       // pnl_bottom = new JPanel(new GridLayout(1, 3));
        pnl_bottom = new JPanel();
        pnl_bottom.setLayout(new BoxLayout(pnl_bottom, BoxLayout.LINE_AXIS));
     
        
        
        
      //  pnl_mainWindow.add(pnl_header, BorderLayout.NORTH);
       // pnl_mainWindow.add(pnl_center, BorderLayout.CENTER);
       // pnl_mainWindow.add(pnl_bottom, BorderLayout.SOUTH);
        
        pnl_header.setBackground(new Color(46, 139, 87));
        pnl_mainWindow.add(pnl_header);
        pnl_mainWindow.add(pnl_center);
        pnl_mainWindow.add(pnl_bottom);
        
        pnl_center.add(pnl_leftCenter);
        pnl_center.add(pnl_middleCenter);
        pnl_center.add(pnl_rightCenter);
        
        pnl_middleCenter.add(pnl_gridCenterFirstTop);
        pnl_middleCenter.add(pnl_gridCenterSecondTop);
        pnl_middleCenter.add(pnl_gridCenterThirdTop);
        pnl_middleCenter.add(pnl_gridCenterFourthTop);
        
        lbl_kennung = new JLabel("Kennung: ");
        lbl_kennung.setFont(new Font("Calibri", Font.BOLD, 30));
        lbl_password = new JLabel("Passwort: ");
        lbl_password.setFont(new Font("Calibri", Font.BOLD, 30));
        lbl_header = new JLabel("Willkommen auf der Login-Seite");
        lbl_header.setFont(new Font("Calibri", Font.BOLD, 45));
        lbl_header.setForeground(new Color(255, 255, 255));
        tf_kennung = new JTextField();
        tf_kennung.setFont(new Font("Calibri", Font.BOLD, 30));
        tf_password = new JPasswordField();
        tf_password.setFont(new Font("Calibri", Font.BOLD, 30));
        
        tf_kennung.setBorder(blackborder);
        tf_password.setBorder(blackborder);
        
        btn_clear = new JButton("Clear");
        btn_clear.setBackground(new Color(50, 205,50));
        btn_clear.setForeground(new Color(255, 255, 255));
        btn_clear.setFont(new Font("Calibri", Font.BOLD, 30));
        btn_login = new JButton("Login");
        btn_login.setBackground(new Color(50, 205, 50));
        btn_login.setForeground(new Color(255, 255, 255));
        btn_login.setFont(new Font("Calibri", Font.BOLD, 30));
        btn_register = new JButton("Registrieren");
        btn_register.setBackground(new Color(50, 205, 50));
        btn_register.setForeground(new Color(255, 255, 255));
        btn_register.setFont(new Font("Calibri", Font.BOLD, 30));
        lbl_kennung.setToolTipText("Bitte geben Sie im benachbarten Textfeld Ihre Kennung ein!");
       // lbl_kennung.setIcon(new ImageIcon(Login.class.getResource("/kennung.png")));
        lbl_password.setToolTipText("Bitte geben Sie im benachbarten Textfeld Ihr Passwort ein!");
      //  lbl_password.setIcon(new ImageIcon(Login.class.getResource("/password.png")));
        //tf_kennung.setToolTipText("Bitte geben Sie Ihre Kennung ein: ");
        //tf_password.setToolTipText("Bitte geben Sie Ihr Passwort ein: ");
        

        pnl_header.add(lbl_header);
        

        pnl_gridCenterSecondTop.add(lbl_kennung);
        pnl_gridCenterSecondTop.add(tf_kennung);
        pnl_gridCenterFourthTop.add(lbl_password);
        pnl_gridCenterFourthTop.add(tf_password);

       pnl_bottom.add(Box.createHorizontalGlue());
        pnl_bottom.add(btn_clear);
       //pnl_bottom.add(Box.createRigidArea(new Dimension(500, 0)));
        pnl_bottom.add(Box.createHorizontalGlue());
       pnl_bottom.add(btn_register);
       //pnl_bottom.add(Box.createRigidArea(new Dimension(500, 0)));
       pnl_bottom.add(Box.createHorizontalGlue());
        pnl_bottom.add(btn_login);
        pnl_bottom.add(Box.createHorizontalGlue());
        loginFenster.getContentPane().add(pnl_mainWindow);
        //loginFenster.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//loginFenster.setUndecorated(true);
        loginFenster.pack();
        loginFenster.setVisible(true);
        btn_login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {	
				String kennung = tf_kennung.getText();
				String password = String.valueOf(tf_password.getPassword());
				System.out.println(password);
				System.out.println(kennung);
				ChatFenster chatwindow = new ChatFenster();
        //loginFenster.setVisible(false);
				loginFenster.dispose();
			}
        });
        	
        btn_register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String kennung = tf_kennung.getText().toString();
				String passwordCleartext = String.valueOf(tf_password.getPassword());
				String passwordHashed = HashPassword.hashWithRSA(passwordCleartext);
				System.out.println(kennung);
				System.out.println(passwordCleartext);
				System.out.println(passwordHashed);
				
			}
        	
        });
        
        btn_clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf_kennung.setText("");
				tf_password.setText("");
				tf_kennung.requestFocus();
				JOptionPane.showMessageDialog(loginFenster, "Die Textfelder wurden geleert! ");
				
			}	
        });
        
      
        
        
    }
    
  


    

}


