/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */

public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int r = s.length() - 1;
        while (r >= 0 && s.charAt(r) == ' ')
            r--;
        int l = r;
        while (l >= 0 && s.charAt(l) != ' ')
            l--;

        return r - l;
    }

    // another solution
    public int lengthOfLastWord1(String s) {
        int end = s.length() - 1;
        int i = end;
        for (; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (end != i) {
                    return end - i;
                }
                end = i - 1;
            }
        }
        return end - i;
    }
}
