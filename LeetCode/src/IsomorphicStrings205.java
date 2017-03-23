/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/22/17
 * Time: 9:55 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

public class IsomorphicStrings205 {
    /**
     * Assume s.length() == t.length()
     */
    public boolean isIsomorphic(String s, String t) {
        int[] preS = new int[256];
        int[] preT = new int[256];
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            if (preS[s.charAt(i)] != preT[t.charAt(i)]) return false;
            preS[s.charAt(i)] = preT[t.charAt(i)] = count++;
        }
        return true;
    }
}
