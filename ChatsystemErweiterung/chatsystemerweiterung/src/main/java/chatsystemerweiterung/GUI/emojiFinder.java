package chatsystemerweiterung.GUI;

public class emojiFinder {

    /*static String emojis[] = { "😌", "🙏", "🎻", "😠", "🧐", "👆", "👌", "😇", "😊", "🤫", "🤔", "💰", "😅", "😍", "😔",
            "💪", "💔", "🙄", "🥰", "😟", "🤷🏼‍♀️", "❤️", "👉", "🧞‍♂️", "😬", "😭", "🌚", "👈", "🤙", "👨", "🤣",
            "👍", "😃", "😂", "💛", "🖤", "😄", "🌝", "😁", "☀️", "👎", "😜", "💯", "😉", "😎", "🤓", "🥳" };
    static String[] shortcuts = { ":relieved:", ":pray:", ":violin:", ":angry:", ":monocle:", ":point_up:", ":ok:",
            ":angel:", ":happy:", ":shush:", ":think:", ":money:", ":sweat:", ":love_eyes", ":pensive:", ":fit:",
            ":broken_heart:", ":roling_eyes:", ":in_love:", ":unhappy:", ":shruggin:", ":heart:", ":point_right",
            "‍:djinn:", ":grim:", ":cry:", ":moon:", ":point_left:", ":call_me:", ":mustache:", ":rofl:", ":thumb:",
            ":smile:", ":joy:", ":heart_yellow:", ":heart_black:", "another_smile", "full_moon", ":grin:", ":sun:",
            ":thumb_down:", ":tounge_out:", ":100:", ":wink:", ":sunglasses:", ":glasses:", ":party:" };
            */
            static String emojis[] = { "😄", "😃", "😀", "😊", "😉", "😍", "😘", "😚", "😗", "😙", "😜", "😝", "😛", "😳", "😁",
            "😔", "😌", "😒", "😞", "😣", "‍😢", "😂", "😭", "😪", "😥", "😰", "😅", "😓", "😩", "😫", "😨",
            "😱", "😠", "😡", "😤", "😖", "🤑", "😋", "😷", "😎", "😴", "😵", "😲", "😟", "😦", "😧", "😈" };
            
            static String[] shortcuts = { ":smile:", ":smiley:", ":grinning:", ":blush:", ":wink:", ":heart_eyes:", ":kissing_heart:",
            ":kissing_closed_eyes:", ":kissing:", ":kissing_smiling_eyes:", ":stuck_out_tongue_winking_eye:", ":stuck_out_tongue_closed_eyes:", ":stuck_out_tongue:", ":flushed:", ":grin:", ":pensive:",
            ":relieved:", ":unamused:", ":disappointed:", ":persevere:", ":cry:", ":joy:", ":sob:",
            "‍:sleepy:", ":disappointed_relieved:", ":cold_sweat:", ":sweat_smile:", ":sweat:", ":weary:", ":tired_face:", ":fearful:", ":scream:",
            ":angry:", ":rage:", ":triumph:", ":confounded:", ":money_face:", ":yum:", ":mask:", ":sunglasses:",
            ":sleeping:", ":dizzy_face:", ":astonished:", ":worried:", ":frowning:", ":anguished:", ":smiling_imp:" };

    static String[][] es = new String[emojis.length][2];

    private static void emojiFinderInit() {
        System.out.println(emojis.length + " " + shortcuts.length);
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