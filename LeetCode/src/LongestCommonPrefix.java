/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 11:58 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        Arrays.sort(strs);
        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        int i = 0;
        while(i < s1.length() && i < s2.length()) {
            if(s1.charAt(i) != s2.charAt(i))
                break;
            i++;
        }

        return s1.substring(0, i);
    }

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }
}
