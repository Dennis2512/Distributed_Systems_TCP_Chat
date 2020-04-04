package GUI;

    import javax.swing.*;
    import java.awt.GridLayout;


public class EmojiOverview extends JFrame{

    
    private static JPanel pnl_emojisOverview;
    private static JButton btn_emojiSweat;
    private static JButton btn_emojiSmile;
    private static JButton btn_emojiLaughTears;
    private static JButton btn_emojiLaughTearsCrazy;
    private static JButton btn_emojiRedHeart;
    private static JButton btn_emojiThumbsUp;
    private static JButton btn_emojiYellowHeart;
    private static JButton btn_emojiTongueOut;
    private static JButton btn_emojiSunglasses;
    private static JButton btn_emojiMuscle;
    private static JButton btn_emojiMoneyBag;
    private static JButton btn_emojiHeartEyes;
    private static JButton btn_emojiAngry;
    private static JButton btn_emojiHundredPercent;
    private static JButton btn_emojiZwinkerFace;
    private static JButton btn_emojiBlush;
    private static JButton btn_emojiShushingFace;
    private static JButton btn_emojithinking;
    private static JButton btn_emojiSad;
    private static JButton btn_emojiBrokenHeart;
    private static JButton btn_emojiPointLeftTone;
    private static JButton btn_emojiSmiley;
    private static JButton btn_emojiCallMe;
    private static JButton btn_emojiGreyMoon;
    private static JButton btn_emojiSunny;



    public EmojiOverview(){

        pnl_emojisOverview = new JPanel(new GridLayout(5, 10, 1, 1));
        pnl_emojisOverview.add(btn_emojiRelieved);
        pnl_emojisOverview.add(btn_emojiPrayTone);
        pnl_emojisOverview.add(btn_emojiViolin);
        pnl_emojisOverview.add(btn_emojiAngry);
        pnl_emojisOverview.add(btn_emojiFaceWithMonocle);
        pnl_emojisOverview.add(btn_emojiPointUp);
        pnl_emojisOverview.add(btn_emojiOkHand);
        pnl_emojisOverview.add(btn_emojiInnocent);
        pnl_emojisOverview.add(btn_emojiBlush);
        pnl_emojisOverview.add(btn_emojiShushingFace);
        pnl_emojisOverview.add(btn_emojiThinking);
        pnl_emojisOverview.add(btn_emojiMoneyBag);
        pnl_emojisOverview.add(btn_emojiSweat);
        pnl_emojisOverview.add(btn_emojiHeartEyes);
        pnl_emojisOverview.add(btn_emojiSad);
        pnl_emojisOverview.add(btn_emojiMuscle);
        pnl_emojisOverview.add(btn_emojiBrokenHeart);
        pnl_emojisOverview.add(btn_emojiRollingEyes);
        pnl_emojisOverview.add(btn_emojiSmilingFaceWithThreeHearts);
        pnl_emojisOverview.add(btnemojiWorried);
        pnl_emojisOverview.add(btn_emojiWomanShrugging);
        pnl_emojisOverview.add(btn_emojiRedHeart);
        pnl_emojisOverview.add(btn_emojiPointRight);
        pnl_emojisOverview.add(btn_emojiManGenie);
        pnl_emojisOverview.add(btn_emojiGrimacing);
        pnl_emojisOverview.add(btn_emojiSob);
        pnl_emojisOverview.add(btn_emojiMoonWithFace);
        pnl_emojisOverview.add(btn_emojiPointLeftTone);
        pnl_emojisOverview.add(btn_emojiCallMe);
        pnl_emojisOverview.add(btn_emojiMan);
        pnl_emojisOverview.add(btn_emojiRofl);
        pnl_emojisOverview.add(btn_emojiThumbsUp);
        pnl_emojisOverview.add(btn_emojiBlush);
        pnl_emojisOverview.add(btn_emojiRollingEyes);
        pnl_emojisOverview.add(btn_emojiSmiley);
        pnl_emojisOverview.add(btn_emojiJoy);
        pnl_emojisOverview.add(btn_emojiYellowHeart);
        pnl_emojisOverview.add(btn_emojiSmile);
        


        this.getContentPane().add(pnl_emojisOverview);
		this.setSize(500, 500);
		this.setVisible(true);
    }

    public static void main(String args[]){

    }

}