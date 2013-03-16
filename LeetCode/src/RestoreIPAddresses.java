/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class RestoreIPAddresses {
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> ret = new ArrayList<String>();

        restore(s, 0, new ArrayList<Integer>(), ret);
        return ret;
    }

    void restore(String s, int index, ArrayList<Integer> fields, ArrayList<String> ret) {
        if (fields.size() == 4 && index == s.length()) {
            String one = fields.get(0) + "." + fields.get(1) + "." + fields.get(2) + "." + fields.get(3);
            ret.add(one);
            return;
        }
        if (fields.size() > 4 || index >= s.length())
            return;

        if (s.charAt(index) == '0') {
            fields.add(0);
            restore(s, index + 1, fields, ret);
            fields.remove(fields.size() - 1);
            return;
        }

        int a = 0;
        while (index < s.length()) {
            a = a * 10 + (s.charAt(index) - '0');
            if (a >= 0 && a < 256) {
                fields.add(a);
                restore(s, index + 1, fields, ret);
                fields.remove(fields.size() - 1);
            } else {
                break;
            }
            index++;
        }
    }
}
