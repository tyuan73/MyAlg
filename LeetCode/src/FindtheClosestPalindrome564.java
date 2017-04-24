/**
 * Created by yuantian on 4/24/17.
 */
public class FindtheClosestPalindrome564 {
    public String nearestPalindromic(String n) {
        int left = (n.length() + 1) / 2, right = n.length() - left;
        long head = Long.parseLong(n.substring(0, left));
        long origin = Long.parseLong(n);
        long diff = Long.MAX_VALUE, num = 0;
        for (int i = -1; i <= 1; i++) {
            long ret = getPalindrom(head + i, right);
            if (ret != origin && Math.abs(ret - origin) < diff) {
                diff = Math.abs(ret - origin);
                num = ret;
            }
        }
        return Long.toString(num);
    }

    private long getPalindrom(long head, int rightLen) {
        String str = Long.toString(head);
        StringBuilder sb = new StringBuilder(str);
        if (head == 0l || rightLen > str.length()) {
            for (int i = 0; i < rightLen; i++)
                sb.append('9');
        } else {
            for (int i = rightLen - 1; i >= 0; i--)
                sb.append(str.charAt(i));
        }
        return Long.parseLong(sb.toString());
    }
}
