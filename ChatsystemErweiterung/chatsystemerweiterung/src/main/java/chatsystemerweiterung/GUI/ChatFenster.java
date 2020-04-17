package chatsystemerweiterung.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

import chatsystemerweiterung.database_firestore.saveData;
import chatsystemerweiterung.rsa.Security;
import chatsystemerweiterung.server.Customtime;
import chatsystemerweiterung.server.Message;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatFenster extends JFrame {
	private static final long serialVersionUID = 2572190480168115289L;

	private Socket connection;
	protected static JTextField tf_message;
	private static JButton btn_sendMessage;
	private static JMenuBar mnbr_chat;
	private static JMenu mnu_chatInfo;
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
	private JTextArea ta_Messages;
	private static JButton btn_emojis;

	private ArrayList<Message> chat;
	private String user, partner, chatname;
	private SimpleDateFormat sdf;

	private saveData save;
	private Security security;
	private ArrayList<String> chats, users;

	public ChatFenster(Socket con, String user, ArrayList<Message> chat, String chatname, ArrayList<String> chats,
			ArrayList<String> users) {
		this.chats = chats;
		this.users = users;
		this.sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		this.chat = chat;
		ta_Messages = new JTextArea();
		if (this.chat == null) {
			this.chat = new ArrayList<Message>();
		} else {
			this.initChat();
		}
		this.chatname = chatname;
		this.setTitle("Chat with " + this.chatname);
		this.connection = con;
		this.user = user;
		save = new saveData();
		this.security = new Security();
		this.build();
	}

	private void initChat() {
		for (Message msg : this.chat) {
			this.ta_Messages.setText(this.ta_Messages.getText() + this.sdf.format(msg.getTime()) + " " + msg.getSender()
					+ ": " + msg.getText() + '\n');
		}
	}

	public void sent(Message text) {
		ta_Messages.setText(ta_Messages.getText() + text.getText() + '\n');
		tf_message.setText("");
		ta_Messages.setCaretPosition(this.ta_Messages.getDocument().getLength());
		save.saveChat(user, partner, text.getText(), (String) this.sdf.format(text.getTime()));

	}

	// empfangene Nachricht setzen
	public void printMsg(Message msg) {
		ta_Messages.append(this.sdf.format(msg.getTime()) + " " + msg.getSender() + ": " + msg.getText() + '\n');
		ta_Messages.setCaretPosition(ta_Messages.getDocument().getLength());
		save.saveChat(partner, user, msg.getText(), (String) this.sdf.format(msg.getTime()));

	}

	public void build() {

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
		ta_Messages.setBorder(blackborder);
		ta_Messages.setEditable(false);

		this.initMenu();

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

		// clear message
		btn_clearMessage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf_message.setText("");
			}
		});

		// Nachricht an den Chatpartner senden
		btn_sendMessage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tf_message.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Geben Sie bitte erst eine Nachricht ein!");
				} else {
					String nachricht = tf_message.getText();
					nachricht = emojiFinder.shortcutSwitcher(nachricht);
					// nachricht senden
					try {

						ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
						Message msg = security.encryptMessage(new Message(user, "MSG", nachricht, Customtime.get()));
						oos.writeObject(msg);

					} catch (IOException err) {
						err.printStackTrace();
					}
				}
			}

		});

		btn_emojis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EmojiOverview();
				frm_chatwindow.setVisible(false);
			}

		});

		Listen listen = new Listen(connection, this);
		listen.start();
		this.getContentPane().add(pnl_main);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	private void initMenu() {
		mnbr_chat = new JMenuBar();
		mnuitm_logout = new JMenuItem("Logout");
		mnuitm_logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		mnuitm_chatOverview = new JMenuItem("Chat Übersicht");
		mnuitm_chatOverview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backtochat();
			}

		});
		mnu_chatInfo = new JMenu("Menü");
		this.setJMenuBar(mnbr_chat);
		mnu_chatInfo.add(mnuitm_logout);
		mnu_chatInfo.add(mnuitm_chatOverview);
		mnbr_chat.add(mnu_chatInfo);
		mnbr_chat.setFont(new Font("Calibri", Font.BOLD, 20));
		mnbr_chat.setForeground(new Color(255, 255, 255));
		mnu_chatInfo.setFont(new Font("Calibri", Font.BOLD, 20));
		mnu_chatInfo.setForeground(new Color(0, 0, 0));
		mnuitm_logout.setFont(new Font("Calibri", Font.BOLD, 20));
		mnuitm_logout.setForeground(new Color(0, 0, 0));
		mnuitm_chatOverview.setFont(new Font("Calibri", Font.BOLD, 20));
		mnuitm_chatOverview.setForeground(new Color(0, 0, 0));
	}

	private void logout() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(this.connection.getOutputStream());
			oos.writeObject(this.security.encryptMessage(new Message(this.user, "LOGOUT", "", new Date())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onLogout() {
		this.dispose();
	}

	private void backtochat() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(this.connection.getOutputStream());
			oos.writeObject(this.security.encryptMessage(new Message(this.user, "LEAVE", "", new Date())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onLeft() {
		this.dispose();
		new ChatOverview(this.connection, this.user, this.chats, this.users);
	}

}
