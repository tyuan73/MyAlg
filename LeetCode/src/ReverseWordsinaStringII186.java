/**
 * Created by yuantian on 2/28/17.
 */
public class ReverseWordsinaStringII186 {
    public void reverseWords(char[] s) {
        int start = 0;
        for (int i = 0; i < s.length; i++) {
            if (i == s.length - 1 || s[i + 1] == ' ') {
                flip(s, start, i);
                start = i + 2;
            }
        }
        //flip(s, start, s.length-1);
        flip(s, 0, s.length - 1);
    }

    private void flip(char[] s, int l, int r) {
        for (; l < r; l++, r--) {
            char ch = s[l];
            s[l] = s[r];
            s[r] = ch;
        }
    }
}
