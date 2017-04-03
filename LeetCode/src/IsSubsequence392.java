/**
 * Created by yuantian on 4/3/17.
 */
public class IsSubsequence392 {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0)
            return true;
        for (int i = 0, idxS = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(idxS) && ++idxS == s.length())
                return true;
        }
        return false;
    }
}
