package GUI;

    import javax.swing.*;
    import java.awt.GridLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

public class EmojiOverview extends JFrame{


    private static JPanel pnl_emojisOverview;
    private static JButton btn_relieved;
    private static JButton btn_pray;
    private static JButton btn_violin;
    private static JButton btn_angry;
    private static JButton btn_monocle;
    private static JButton btn_point_up;
    private static JButton btn_ok;
    private static JButton btn_angel;
    private static JButton btn_happy;
    private static JButton btn_shush;
    private static JButton btn_think;
    private static JButton btn_money;
    private static JButton btn_sweat;
    private static JButton btn_loveeyes;
    private static JButton btn_pensive;
    private static JButton btn_fit;
    private static JButton btn_brokenHeart;
    private static JButton btn_rollingEyes;
    private static JButton btn_inLove;
    private static JButton btn_unhappy;
    private static JButton btn_shruggin;
    private static JButton btn_heart;
    private static JButton btn_pointRight;
    private static JButton btn_djinn;
    private static JButton btn_grim;
    private static JButton btn_cry;
    private static JButton btn_moon;
    private static JButton btn_pointLeft;
    private static JButton btn_callMe;
    private static JButton btn_mustache;
    private static JButton btn_rofl;
    private static JButton btn_thumb;
    private static JButton btn_smile;
    private static JButton btn_joy;
    private static JButton btn_heartYellow;
    private static JButton btn_heartBlack;
    private static JButton btn_anotherSmile;
    private static JButton btn_fullMoon;
    private static JButton btn_grin;
    private static JButton btn_sun;
    private static JButton btn_thumbDown;
    private static JButton btn_tongueOut;
    private static JButton btn_100;
    private static JButton btn_wink;
    private static JButton btn_sunglasses;
    private static JButton btn_glasses;
    private static JButton btn_party;





    public EmojiOverview(){

        pnl_emojisOverview = new JPanel(new GridLayout(5, 10, 1, 1));

        //Initialize Buttons in Grid
        btn_relieved = new JButton(emojiFinder.emojis[0]);
        btn_pray = new JButton(emojiFinder.emojis[1]);
        btn_violin= new JButton(emojiFinder.emojis[2]);
        btn_angry= new JButton(emojiFinder.emojis[3]);
        btn_monocle= new JButton(emojiFinder.emojis[4]);
        btn_point_up = new JButton(emojiFinder.emojis[5]);
        btn_ok = new JButton(emojiFinder.emojis[6]);
        btn_angel = new JButton(emojiFinder.emojis[7]);
        btn_happy = new JButton(emojiFinder.emojis[8]);
        btn_shush = new JButton(emojiFinder.emojis[9]);
        btn_think = new JButton(emojiFinder.emojis[10]);
        btn_money = new JButton(emojiFinder.emojis[11]);
        btn_sweat = new JButton(emojiFinder.emojis[12]);
        btn_loveeyes = new JButton(emojiFinder.emojis[13]);
        btn_pensive = new JButton(emojiFinder.emojis[14]);
        btn_fit = new JButton(emojiFinder.emojis[15]);
        btn_brokenHeart = new JButton(emojiFinder.emojis[16]);
        btn_rollingEyes = new JButton(emojiFinder.emojis[17]);
        btn_inLove = new JButton(emojiFinder.emojis[18]);
        btn_unhappy = new JButton(emojiFinder.emojis[19]);
        btn_shruggin = new JButton(emojiFinder.emojis[20]);
        btn_heart = new JButton(emojiFinder.emojis[21]);
        btn_pointRight = new JButton(emojiFinder.emojis[22]);
        btn_djinn = new JButton(emojiFinder.emojis[23]);
        btn_grim = new JButton(emojiFinder.emojis[24]);
        btn_cry = new JButton(emojiFinder.emojis[25]);
        btn_moon = new JButton(emojiFinder.emojis[26]);
        btn_pointLeft = new JButton(emojiFinder.emojis[27]);
        btn_callMe = new JButton(emojiFinder.emojis[28]);
        btn_mustache = new JButton(emojiFinder.emojis[29]);
        btn_rofl = new JButton(emojiFinder.emojis[30]);
        btn_thumb = new JButton(emojiFinder.emojis[31]);
        btn_smile = new JButton(emojiFinder.emojis[32]);
        btn_joy = new JButton(emojiFinder.emojis[33]);
        btn_heartYellow = new JButton(emojiFinder.emojis[34]);
        btn_heartBlack= new JButton(emojiFinder.emojis[35]);
        btn_anotherSmile = new JButton(emojiFinder.emojis[36]);
        btn_fullMoon = new JButton(emojiFinder.emojis[37]);
        btn_grin = new JButton(emojiFinder.emojis[38]);
        btn_sun = new JButton(emojiFinder.emojis[39]);
        btn_thumbDown = new JButton(emojiFinder.emojis[40]);
        btn_tongueOut = new JButton(emojiFinder.emojis[41]);
        btn_100 = new JButton(emojiFinder.emojis[42]);
        btn_wink = new JButton(emojiFinder.emojis[43]);
        btn_sunglasses = new JButton(emojiFinder.emojis[44]);
        btn_glasses = new JButton(emojiFinder.emojis[45]);
        btn_party = new JButton(emojiFinder.emojis[46]);

        //Add Buttons to Panel
        pnl_emojisOverview.add(btn_relieved);
        pnl_emojisOverview.add(btn_pray);
        pnl_emojisOverview.add(btn_violin);
        pnl_emojisOverview.add(btn_angry);
        pnl_emojisOverview.add(btn_monocle);
        pnl_emojisOverview.add(btn_point_up);
        pnl_emojisOverview.add(btn_ok);
        pnl_emojisOverview.add(btn_angel);
        pnl_emojisOverview.add(btn_happy);
        pnl_emojisOverview.add(btn_shush);
        pnl_emojisOverview.add(btn_think);
        pnl_emojisOverview.add(btn_money);
        pnl_emojisOverview.add(btn_sweat);
        pnl_emojisOverview.add(btn_loveeyes);
        pnl_emojisOverview.add(btn_pensive);
        pnl_emojisOverview.add(btn_fit);
        pnl_emojisOverview.add(btn_brokenHeart);
        pnl_emojisOverview.add(btn_rollingEyes);
        pnl_emojisOverview.add(btn_inLove);
        pnl_emojisOverview.add(btn_unhappy);
        pnl_emojisOverview.add(btn_shruggin);
        pnl_emojisOverview.add(btn_heart);
        pnl_emojisOverview.add(btn_pointRight);
        pnl_emojisOverview.add(btn_djinn);
        pnl_emojisOverview.add(btn_grim);
        pnl_emojisOverview.add(btn_cry);
        pnl_emojisOverview.add(btn_moon);
        pnl_emojisOverview.add(btn_pointLeft);
        pnl_emojisOverview.add(btn_callMe);
        pnl_emojisOverview.add(btn_mustache);
        pnl_emojisOverview.add(btn_rofl);
        pnl_emojisOverview.add(btn_thumb);
        pnl_emojisOverview.add(btn_smile);
        pnl_emojisOverview.add(btn_joy);
        pnl_emojisOverview.add(btn_heartYellow);
        pnl_emojisOverview.add(btn_heartBlack);
        pnl_emojisOverview.add(btn_anotherSmile);
        pnl_emojisOverview.add(btn_fullMoon);
        pnl_emojisOverview.add(btn_grin);
        pnl_emojisOverview.add(btn_sun);
        pnl_emojisOverview.add(btn_thumbDown);
        pnl_emojisOverview.add(btn_tongueOut);
        pnl_emojisOverview.add(btn_100);
        pnl_emojisOverview.add(btn_wink);
        pnl_emojisOverview.add(btn_sunglasses);
        pnl_emojisOverview.add(btn_glasses);
        pnl_emojisOverview.add(btn_party);
        


        this.getContentPane().add(pnl_emojisOverview);
		this.setSize(500, 500);
		this.setVisible(true);
    }

    public static void main(String args[]){

       //Add ActionListener for each emojibutton
        btn_relieved.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_relieved.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_pray.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_pray.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_violin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_violin.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_angry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_angry.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_monocle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_monocle.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_point_up.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_point_up.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_ok.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_angel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_angel.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_happy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_happy.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_shush.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_shush.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_think.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_think.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_money.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_money.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_sweat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_sweat.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_loveeyes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_loveeyes.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_pensive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_pensive.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_fit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_fit.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_brokenHeart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_brokenHeart.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_rollingEyes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_rollingEyes.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_inLove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_inLove.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_unhappy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_unhappy.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_shruggin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_shruggin.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_heart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_heart.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_pointRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
        });

        btn_djinn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_djinn.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_grim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_grim.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_cry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_cry.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_moon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_moon.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_pointLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_pointLeft.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_callMe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_callMe.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_mustache.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_mustache.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_rofl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_rofl.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_thumb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_thumb.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_smile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_smile.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_joy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_joy.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_heartYellow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_heartYellow.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_heartBlack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_heartBlack.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_anotherSmile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_anotherSmile.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_fullMoon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_fullMoon.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_grin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_grin.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_sun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_sun.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_thumbDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_thumbDown.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_tongueOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_tongueOut.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_100.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_100.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_wink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_wink.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_sunglasses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_sunglasses.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_glasses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = btn_glasses.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
			}
        });

        btn_party.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                String emoji = btn_party.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + emoji);
                
			}
        });

    }

}