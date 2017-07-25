/**
 * Created by yuantian on 7/25/17.
 */
public class PalindromicSubstrings647 {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += count(s, i, i); // odd
            count += count(s, i, i + 1); // even;
        }
        return count;
    }

    private int count(String s, int l, int r) {
        int c = 0;
        for (; l >= 0 && r < s.length(); l--, r++) {
            if (s.charAt(l) != s.charAt(r)) break;
            c++;
        }
        return c;
    }
}
