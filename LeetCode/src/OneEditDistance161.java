/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/2/17
 * Time: 10:54 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class OneEditDistance161 {
    public boolean isOneEditDistance(String s, String t) {
        int n = s.length(), m = t.length();
        if (n > m)
            return isOneEditDistance(t, s);    // make sure s.length <= t.length
        if (m - n > 1)
            return false;

        int l = 0, r = n - 1;
        while (l < n && s.charAt(l) == t.charAt(l))
            l++;
        if (n == m) {
            while (r >= 0 && s.charAt(r) == t.charAt(r))
                r--;
            return r == l;
        } else {
            while (r >= 0 && s.charAt(r) == t.charAt(r + 1))
                r--;
            return l > r;
        }
    }
}
