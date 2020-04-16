package chatsystemerweiterung.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.sound.sampled.SourceDataLine;
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

import com.google.api.SystemParameter;

import chatsystemerweiterung.GUI.Listen;
import chatsystemerweiterung.database_firestore.saveData;
import chatsystemerweiterung.server.Customtime;
import chatsystemerweiterung.server.Message;
import chatsystemerweiterung.server.User;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChatFenster extends JFrame {

	private Socket connection;
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

	private boolean end;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	private ArrayList<Message> chat;
	private String user, partner;
	private SimpleDateFormat sdf;

	private saveData save;

	public ChatFenster(Socket con, String user) {
		super("Chat Window");
		this.sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		connection = con;
		this.user = user;
		partner = "LB";
		end = false;
		ta_Messages = new JTextArea();
		save = new saveData();

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
		System.out.println(this.sdf.format(msg.getTime()) + " " + msg.getSender() + ": " + msg.getText());
		save.saveChat(partner, user, msg.getText(), (String) this.sdf.format(msg.getTime()));

	}

	public void build() {

		// Verbindung zum anderen Chatpartner aufbauen
		try {
			this.oos = new ObjectOutputStream(this.connection.getOutputStream());
			// kennung einlesen --> Hardgecoded für anfang
			// System.out.println("Kennung ihres Chatpartners eingeben:");
			// String p = this.console.readLine();
			// chat aufbauen
			this.oos.writeObject(new Message(user, "CONNECT", partner, Customtime.get()));
			// wenn erfolgreich, dann angemeldeten nutzer setzen
			this.ois = new ObjectInputStream(this.connection.getInputStream());
			Message ans = (Message) ois.readObject();

			if (ans.getType().equals("NEWCHAT")) {
				System.out.println(ans.getText());
				System.out.println("Neuer Chat generiert. LosChatten");
			} else if (ans.getType().equals("LOADCHAT")) {
				System.out.println(ans.getText());
				System.out.println("Chat schon vorhanden. Chathistorie laden");
				this.ois = new ObjectInputStream(this.connection.getInputStream());
				this.chat = (ArrayList<Message>) ois.readObject();
			} else {
				System.out.println(ans.getText());
			}

		} catch (IOException e) {
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}

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
						oos.writeObject(new Message(user, "MSG", nachricht, Customtime.get()));

					} catch (IOException err) {
						System.err.println(err);
					}
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

		Listen listen = new Listen(connection, this);
		listen.start();

		this.getContentPane().add(pnl_main);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	// public static void main(String[] args) {
	// ChatFenster f1 =new ChatFenster();

	// }

}
