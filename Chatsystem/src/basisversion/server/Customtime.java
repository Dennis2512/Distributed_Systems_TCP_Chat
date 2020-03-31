package basisversion.server;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Customtime {

    static String get() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return sdf.format(new Date());
    }
}