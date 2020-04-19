package chatsystemerweiterung.GUI;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.vdurmont.emoji.EmojiParser;

public class EmojiOverview extends JFrame {
    private static final long serialVersionUID = -1935516020694444631L;

    private static JPanel pnl_emojisOverview;
    private static JButton btn_smiley;
    private static JButton btn_smile;
    private static JButton btn_grinning;
    private static JButton btn_blush;
    private static JButton btn_wink;
    private static JButton btn_heart_eyes;
    private static JButton btn_kissing_heart;
    private static JButton btn_kissing_closed_eyes;
    private static JButton btn_kissing;
    private static JButton btn_kissing_smiling_eyes;
    private static JButton btn_stuck_out_tongue_winking_eye;
    private static JButton btn_stuck_out_tongue_closed_eyes;
    private static JButton btn_stuck_out_tongue;
    private static JButton btn_flushed;
    private static JButton btn_grin;
    private static JButton btn_pensive;
    private static JButton btn_relieved;
    private static JButton btn_unamused;
    private static JButton btn_disappointed;
    private static JButton btn_persevere;
    private static JButton btn_cry;
    private static JButton btn_joy;
    private static JButton btn_sob;
    private static JButton btn_sleepy;
    private static JButton btn_disappointed_relieved;
    private static JButton btn_cold_sweat;
    private static JButton btn_sweat_smile;
    private static JButton btn_sweat;
    private static JButton btn_weary;
    private static JButton btn_tired_face;
    private static JButton btn_fearful;
    private static JButton btn_scream;
    private static JButton btn_angry;
    private static JButton btn_rage;
    private static JButton btn_triumph;
    private static JButton btn_confounded;
    private static JButton btn_moneyFace;
    private static JButton btn_yum;
    private static JButton btn_mask;
    private static JButton btn_sunglasses;
    private static JButton btn_sleeping;
    private static JButton btn_dizzyFace;
    private static JButton btn_astonished;
    private static JButton btn_worried;
    private static JButton btn_frowning;
    private static JButton btn_anguished;
    private static JButton btn_smiling_imp;

    public EmojiOverview() {
        super("Emojis Overview");
        pnl_emojisOverview = new JPanel(new GridLayout(5, 10, 1, 1));

        // Initialize Buttons in Grid
        btn_smiley = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[0]));
        btn_smile = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[1]));
        btn_grinning = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[2]));
        btn_blush = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[3]));
        btn_wink = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[4]));
        btn_heart_eyes = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[5]));
        btn_kissing_heart = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[6]));
        btn_kissing_closed_eyes = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[7]));
        btn_kissing = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[8]));
        btn_kissing_smiling_eyes = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[9]));
        btn_stuck_out_tongue_winking_eye = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[10]));
        btn_stuck_out_tongue_closed_eyes = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[11]));
        btn_stuck_out_tongue = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[12]));
        btn_flushed = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[13]));
        btn_grin = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[14]));
        btn_pensive = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[15]));
        btn_relieved = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[16]));
        btn_unamused = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[17]));
        btn_disappointed = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[18]));
        btn_persevere = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[19]));
        btn_cry = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[20]));
        btn_joy = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[21]));
        btn_sob = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[22]));
        btn_sleepy = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[23]));
        btn_disappointed_relieved = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[24]));
        btn_cold_sweat = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[25]));
        btn_sweat_smile = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[26]));
        btn_sweat = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[27]));
        btn_weary = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[28]));
        btn_tired_face = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[29]));
        btn_fearful = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[30]));
        btn_scream = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[31]));
        btn_angry = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[32]));
        btn_rage = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[33]));
        btn_triumph = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[34]));
        btn_confounded = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[35]));
        btn_moneyFace = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[36]));
        btn_yum = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[37]));
        btn_mask = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[38]));
        btn_sunglasses = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[39]));
        btn_sleeping = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[40]));
        btn_dizzyFace = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[41]));
        btn_astonished = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[42]));
        btn_worried = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[43]));
        btn_frowning = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[44]));
        btn_anguished = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[45]));
        btn_smiling_imp = new JButton(EmojiParser.parseToUnicode(emojiFinder.shortcuts[46]));

        // Add Buttons to Panel
        pnl_emojisOverview.add(btn_smiley);
        pnl_emojisOverview.add(btn_smile);
        pnl_emojisOverview.add(btn_grinning);
        pnl_emojisOverview.add(btn_blush);
        pnl_emojisOverview.add(btn_wink);
        pnl_emojisOverview.add(btn_heart_eyes);
        pnl_emojisOverview.add(btn_kissing_heart);
        pnl_emojisOverview.add(btn_kissing_closed_eyes);
        pnl_emojisOverview.add(btn_kissing);
        pnl_emojisOverview.add(btn_kissing_smiling_eyes);
        pnl_emojisOverview.add(btn_stuck_out_tongue_winking_eye);
        pnl_emojisOverview.add(btn_stuck_out_tongue_closed_eyes);
        pnl_emojisOverview.add(btn_stuck_out_tongue);
        pnl_emojisOverview.add(btn_flushed);
        pnl_emojisOverview.add(btn_grin);
        pnl_emojisOverview.add(btn_pensive);
        pnl_emojisOverview.add(btn_relieved);
        pnl_emojisOverview.add(btn_unamused);
        pnl_emojisOverview.add(btn_disappointed);
        pnl_emojisOverview.add(btn_persevere);
        pnl_emojisOverview.add(btn_cry);
        pnl_emojisOverview.add(btn_sob);
        pnl_emojisOverview.add(btn_joy);
        pnl_emojisOverview.add(btn_sleepy);
        pnl_emojisOverview.add(btn_disappointed_relieved);
        pnl_emojisOverview.add(btn_cold_sweat);
        pnl_emojisOverview.add(btn_sweat_smile);
        pnl_emojisOverview.add(btn_sweat);
        pnl_emojisOverview.add(btn_weary);
        pnl_emojisOverview.add(btn_tired_face);
        pnl_emojisOverview.add(btn_fearful);
        pnl_emojisOverview.add(btn_scream);
        pnl_emojisOverview.add(btn_angry);
        pnl_emojisOverview.add(btn_rage);
        pnl_emojisOverview.add(btn_triumph);
        pnl_emojisOverview.add(btn_confounded);
        pnl_emojisOverview.add(btn_moneyFace);
        pnl_emojisOverview.add(btn_yum);
        pnl_emojisOverview.add(btn_mask);
        pnl_emojisOverview.add(btn_sunglasses);
        pnl_emojisOverview.add(btn_sleeping);
        pnl_emojisOverview.add(btn_dizzyFace);
        pnl_emojisOverview.add(btn_astonished);
        pnl_emojisOverview.add(btn_worried);
        pnl_emojisOverview.add(btn_frowning);
        pnl_emojisOverview.add(btn_anguished);
        pnl_emojisOverview.add(btn_smiling_imp);
        btn_smiley.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_smiley.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_smile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_smile.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_grinning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_grinning.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_blush.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_blush.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_wink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_wink.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_heart_eyes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_heart_eyes.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_kissing_heart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_kissing_heart.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_kissing_closed_eyes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_kissing_closed_eyes.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_kissing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_kissing.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_kissing_smiling_eyes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_kissing_smiling_eyes.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_stuck_out_tongue_winking_eye.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_stuck_out_tongue_winking_eye.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_stuck_out_tongue_closed_eyes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_stuck_out_tongue_closed_eyes.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_stuck_out_tongue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_stuck_out_tongue.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_flushed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_flushed.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_grin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_grin.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_pensive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_pensive.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_relieved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_relieved.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_unamused.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_unamused.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_disappointed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_disappointed.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_persevere.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_persevere.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_cry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_cry.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_sob.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_sob.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_joy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_joy.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_sleepy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_sleepy.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_disappointed_relieved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_disappointed_relieved.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_cry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_cry.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_cold_sweat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_cold_sweat.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_sweat_smile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_sweat_smile.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_sweat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_sweat.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_weary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_weary.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_tired_face.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_tired_face.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_fearful.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_fearful.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_smile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_smile.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_joy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_joy.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_scream.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_scream.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_angry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_angry.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_rage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_rage.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_triumph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_triumph.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_grin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_grin.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_confounded.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_confounded.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_moneyFace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_moneyFace.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_yum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_yum.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_mask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_mask.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_sunglasses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_sunglasses.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_sleeping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_sleeping.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_dizzyFace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_dizzyFace.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_astonished.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_astonished.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_worried.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_worried.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_frowning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_frowning.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_anguished.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_anguished.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        btn_smiling_imp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emoji = btn_smiling_imp.getText();
                ChatFenster.tf_message.setText(ChatFenster.tf_message.getText() + EmojiParser.parseToUnicode(emoji));
                setVisible(false);
            }
        });

        this.getContentPane().add(pnl_emojisOverview);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    // public static void main(String args[]){

    // Add ActionListener for each emojibutton
    // EmojiOverview eo = new EmojiOverview();

    // }

}