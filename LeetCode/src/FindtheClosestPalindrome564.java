/**
 * Created by yuantian on 4/24/17.
 */
public class FindtheClosestPalindrome564 {
    public String nearestPalindromic(String n) {
        // the length of the left (head) part, and the length of the right (tail) part
        int left = (n.length() + 1) / 2, right = n.length() - left;
        long head = Long.parseLong(n.substring(0, left)); // head in number
        long origin = Long.parseLong(n); // original number
        long diff = Long.MAX_VALUE, num = 0;
        /**
         * in order to get closest palindrome, we just need to check palindrome
         * numbers that can be constructed from: (left - 1), (left + 0) and
         * (left + 1).
         */
        for (int i = -1; i <= 1; i++) {
            long ret = getPalindrome(head + i, right);
            if (ret != origin && Math.abs(ret - origin) < diff) {
                diff = Math.abs(ret - origin);
                num = ret;
            }
        }
        return Long.toString(num);
    }

    /**
     * Construct palindrome from head. Don't need to check if the length of the original
     * number is odd or not. The right part must have the same length as the right part of the
     * original number.
     */
    private long getPalindrome(long head, int rightLen) {
        String str = Long.toString(head);
        StringBuilder sb = new StringBuilder(str);
        /**
         * Corner cases to consider:
         * 1. numbers like "10" has a left of "1" which will be "0" when checking (head - 1);
         * 2. numbers like "1000" has a left of "10" which will be "9" when checking (head - 1),
         *    this is the case that left has a shorter length than right;
         *
         * By checking (head == 0) || rightLen > str.length(), we kill them all.
         */
        if (head == 0l || rightLen > str.length()) {
            for (int i = 0; i < rightLen; i++)
                sb.append('9');
        } else {
            /**
             * Construct the palindrome numbers from head. This is pretty straight forward.
             */
            for (int i = rightLen - 1; i >= 0; i--)
                sb.append(str.charAt(i));
        }
        return Long.parseLong(sb.toString());
    }
}
