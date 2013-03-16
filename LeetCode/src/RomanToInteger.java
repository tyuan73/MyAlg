/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/16/13
 * Time: 12:15 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class RomanToInteger {
    static HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public int romanToInt(String s) {
        int pre = map.get(s.charAt(0));
        int ret = pre;
        for(int i = 1; i < s.length(); i++) {
            int x = map.get(s.charAt(i));
            ret += x;
            if(x > pre) {
                ret -= 2*pre;
                pre = 100000;
            }
            else
                pre = x;
        }
        return ret;
    }
}
