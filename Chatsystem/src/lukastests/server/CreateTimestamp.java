package lukastests.server;

import java.sql.*;

class CreateTimestamp {
    
    /*public static void main(final String[] args) {
        Timestamp tstamp = new Timestamp(System.currentTimeMillis());
        System.out.println(tstamp);
        Timestamp tstamp2 = new Timestamp(System.currentTimeMillis());
        System.out.println(tstamp2);

        System.out.println(tstamp.before(tstamp2));
    }*/

    public Timestamp getTimestamp(){
        Timestamp tstamp = new Timestamp(System.currentTimeMillis());
        return tstamp;
    }
}