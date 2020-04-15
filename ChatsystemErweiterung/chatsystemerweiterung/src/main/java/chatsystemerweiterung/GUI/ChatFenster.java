package chatsystemerweiterung.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ChatFenster extends JFrame {

	protected static JTextField tf_message;
	private static JButton btn_sendMessage;
	private static JMenuBar mnbr_chat;
	private static JMenu mnu_chatInfo;
	private static JMenuItem mnuitm_account;
	private static JMenuItem mnuitm_logout;
	private static JMenuItem mnuitm_chatOverview;
	private static JPanel pnl_main;
	private static JPanel pnl_top;
	private static JPanel pnl_center;
	private static JPanel pnl_bottom;
	private static JButton btn_clearMessage;
	private static JLabel lbl_title;
	private static Border greenborder;
	private static Border blackborder;
	private static JPanel pnl_messages;
	private static JTextArea ta_Messages;
	private static Border blackline;
	private static JButton btn_emojis;
	private static JFrame frm_chatwindow;

	public ChatFenster() {
		super("Chat Window");
		JFrame frm_chatwindow = new JFrame();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		greenborder = BorderFactory.createLineBorder(new Color(46, 139, 87), 2);
		blackborder = BorderFactory.createTitledBorder("Nachrichten");
		pnl_main = new JPanel(new BorderLayout());
		btn_emojis = new JButton("Emojis");
		pnl_top = new JPanel(new GridBagLayout());
		pnl_center = new JPanel(new GridLayout(1, 1));
		pnl_messages = new JPanel(new GridLayout(1, 1));
		pnl_bottom = new JPanel();
		pnl_bottom.setLayout(new BoxLayout(pnl_bottom, BoxLayout.LINE_AXIS));
		mnbr_chat = new JMenuBar();
		mnuitm_logout = new JMenuItem("Logout");
		mnuitm_account = new JMenuItem("Account");
		mnuitm_chatOverview = new JMenuItem("Chat Übersicht");
		mnu_chatInfo = new JMenu("Menü");
		this.setJMenuBar(mnbr_chat);
		mnu_chatInfo.add(mnuitm_account);
		mnu_chatInfo.add(mnuitm_logout);
		mnu_chatInfo.add(mnuitm_chatOverview);
		mnbr_chat.add(mnu_chatInfo);

		ta_Messages = new JTextArea();
		ta_Messages.setBorder(blackborder);
		ta_Messages.setEditable(false);
		mnbr_chat.setFont(new Font("Calibri", Font.BOLD, 20));
		mnbr_chat.setForeground(new Color(255, 255, 255));
		mnu_chatInfo.setFont(new Font("Calibri", Font.BOLD, 20));
		mnu_chatInfo.setForeground(new Color(0, 0, 0));
		mnuitm_logout.setFont(new Font("Calibri", Font.BOLD, 20));
		mnuitm_logout.setForeground(new Color(0, 0, 0));
		mnuitm_account.setFont(new Font("Calibri", Font.BOLD, 20));
		mnuitm_account.setForeground(new Color(0, 0, 0));
		mnuitm_chatOverview.setFont(new Font("Calibri", Font.BOLD, 20));
		mnuitm_chatOverview.setForeground(new Color(0, 0, 0));

		lbl_title = new JLabel("Chat Program");
		lbl_title.setForeground(new Color(255, 255, 255));
		lbl_title.setFont(new Font("Calibri", Font.BOLD, 30));
		pnl_top.setBackground(new Color(46, 139, 87));
		btn_clearMessage = new JButton("Clear");
		btn_clearMessage.setBackground(new Color(50, 205, 50));
		btn_clearMessage.setForeground(new Color(255, 255, 255));
		btn_clearMessage.setFont(new Font("Calibri", Font.BOLD, 30));
		btn_sendMessage = new JButton("Send");
		btn_sendMessage.setBackground(new Color(50, 205, 50));
		btn_sendMessage.setForeground(new Color(255, 255, 255));
		btn_sendMessage.setFont(new Font("Calibri", Font.BOLD, 30));
		btn_emojis.setBackground(new Color(50, 205, 50));
		btn_emojis.setForeground(new Color(255, 255, 255));
		btn_emojis.setFont(new Font("Calibri", Font.BOLD, 30));
		tf_message = new JTextField();
		tf_message.setFont(new Font("Calibri", Font.BOLD, 30));
		tf_message.setBorder(greenborder);
		pnl_top.add(lbl_title);
		pnl_messages.add(ta_Messages);

		pnl_main.add(pnl_top, BorderLayout.NORTH);
		pnl_main.add(pnl_center, BorderLayout.CENTER);
		pnl_main.add(pnl_bottom, BorderLayout.SOUTH);

		pnl_center.add(pnl_messages);

		pnl_bottom.add(btn_clearMessage);
		pnl_bottom.add(Box.createHorizontalGlue());
		pnl_bottom.add(tf_message);
		pnl_bottom.add(Box.createHorizontalGlue());
		pnl_bottom.add(btn_emojis);
		pnl_bottom.add(Box.createHorizontalGlue());
		pnl_bottom.add(btn_sendMessage);

		btn_clearMessage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf_message.setText("");
			}
		});

		btn_sendMessage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tf_message.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Geben Sie bitte erst eine Nachricht ein!");
				} else {
					String nachricht = tf_message.getText();
					nachricht = emojiFinder.shortcutSwitcher(nachricht);
					ta_Messages.setText(ta_Messages.getText() + "\n" + nachricht);
				}
			}

		});

		btn_emojis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EmojiOverview emojiWindow = new EmojiOverview();
				frm_chatwindow.setVisible(false);
			}

		});

		this.getContentPane().add(pnl_main);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	// public static void main(String[] args) {
	// ChatFenster f1 =new ChatFenster();

	// }

}
