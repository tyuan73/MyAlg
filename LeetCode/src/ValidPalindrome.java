/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/25/13
 * Time: 4:15 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            char ch1 = s.charAt(l);
            if (!Character.isLetterOrDigit(ch1)) {
                l++;
                continue;
            }

            char ch2 = s.charAt(r);
            if (!Character.isLetterOrDigit(ch2)) {
                r--;
                continue;
            }

            if (Character.toLowerCase(ch1) != Character.toLowerCase(ch2))
                return false;
            l++;
            r--;
        }

        return true;
    }
}
