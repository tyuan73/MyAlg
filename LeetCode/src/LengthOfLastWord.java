/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class LengthOfLastWord {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }

    public int lengthOfLastWord(String s) {
        int r = s.length() - 1;
        while(r >= 0 && s.charAt(r) == ' ')
            r--;
        int l = r;
        while(l >= 0 && s.charAt(l) != ' ')
            l--;

        return r-l;
    }
}
