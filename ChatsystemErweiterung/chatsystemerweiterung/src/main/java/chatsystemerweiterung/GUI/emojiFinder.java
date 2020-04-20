package chatsystemerweiterung.GUI;

public class emojiFinder {


    static String emojis[] = { "😄", "😃", "😀", "😊", "😉", "😍", "😘", "😚", "😗", "😙", "😜", "😝", "😛", "😳", "😁",
            "😔", "😌", "😒", "😞", "😣", "‍😢", "😂", "😭", "😪", "😥", "😰", "😅", "😓", "😩", "😫", "😨", "😱", "😠",
            "😡", "😤", "😖", "🤑", "😋", "😷", "😎", "😴", "😵", "😲", "😟", "😦", "😧", "😈" };

    static String[] shortcuts = { ":smile:", ":smiley:", ":grinning:", ":blush:", ":wink:", ":heart_eyes:",
            ":kissing_heart:", ":kissing_closed_eyes:", ":kissing:", ":kissing_smiling_eyes:",
            ":stuck_out_tongue_winking_eye:", ":stuck_out_tongue_closed_eyes:", ":stuck_out_tongue:", ":flushed:",
            ":grin:", ":pensive:", ":relieved:", ":unamused:", ":disappointed:", ":persevere:", ":cry:", ":joy:",
            ":sob:", "‍:sleepy:", ":disappointed_relieved:", ":cold_sweat:", ":sweat_smile:", ":sweat:", ":weary:",
            ":tired_face:", ":fearful:", ":scream:", ":angry:", ":rage:", ":triumph:", ":confounded:", ":money_face:",
            ":yum:", ":mask:", ":sunglasses:", ":sleeping:", ":dizzy_face:", ":astonished:", ":worried:", ":frowning:",
            ":anguished:", ":smiling_imp:" };

    static String[][] es = new String[emojis.length][2];

    private static void emojiFinderInit() {
        for (int i = 0; i < emojis.length; i++) {
            es[i][0] = "" + emojis[i];
            es[i][1] = "" + shortcuts[i];
        }
    }

    public static String shortcutSwitcher(String word) {
        emojiFinderInit();
        for (int i = 0; i < shortcuts.length; i++) {
            word = word.replaceAll(es[i][1], es[i][0]);
        }
        return word;
    }

}