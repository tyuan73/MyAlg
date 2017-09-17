/*

*/

import java.util.*;
import java.io.*;

public class ValidPalindromeII680 {
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
        }
        if (l >= r) return true;
        return valid(s, l + 1, r) || valid(s, l, r - 1);
    }

    private boolean valid(String s, int l, int r) {
        for (; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r)) return false;
        }
        return true;
    }
}