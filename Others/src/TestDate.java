/**
 * Created by yuantian on 8/6/15.
 */

import java.util.Date;
import java.util.TimeZone;
import java.text.*;

public class TestDate {
    static public void main(String[] args) {
        Date expdate = new Date();
        expdate.setTime(expdate.getTime() + (3600 * 1000));
        DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String cookieExpire = "expires=" + df.format(expdate);
        System.out.println(cookieExpire);
    }
}
