/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/6/13
 * Time: 12:39 AM
 * To change this template use File | Settings | File Templates.
 */

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring lp = new LongestPalindromicSubstring();
        String ret = lp.longestPalindrome("321012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210123210012321001232100123210123");
        System.out.println(ret);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return s;

        String str = preprocess(s);
        int[] rad = new int[str.length()];
        int center = 0;
        int r = 0;
        for (int i = 1; i < str.length() - 1; i++) {
            int len = 0;
            if (i < center + r) {
                len = rad[2 * center - i];
                if (i + len > center + r)
                    len = center + r - i;
            }

            //System.out.println("i = " + i + " len =" + len);
            while (str.charAt(i - len - 1) == str.charAt(i + len + 1))
                len++;
            rad[i] = len;

            if (i + len > center + r) {
                center = i;
                r = len;
            }
        }

        /*
        for(int i : rad) {
            System.out.printf("%5d", i);
        }
        System.out.println();
        */

        int max = 0, index = 0;
        for (int i = 1; i < rad.length; i++) {
            if (rad[i] > max) {
                max = rad[i];
                index = i;
            }
        }

        int start = (index - max - 1) / 2;
        return s.substring(start, start + max);
    }

    String preprocess(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("^*");
        for (int i = 0; i < s.length(); i++)
            sb.append(s.charAt(i)).append('*');
        sb.append('$');
        //System.out.println(sb.toString());
        return sb.toString();
    }
}
