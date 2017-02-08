/**
 * Created by yuantian on 2/7/17.
 */
public class MinimumWindowSubstring76 {
    /**
     * O(n) - counting + two pointers
     * https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int[] need = new int[128];       // the target count for each needed char
        int count = t.length();
        for (int i = 0; i < count; i++)
            need[t.charAt(i)]++;

        int span = s.length() + 1, start = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            if (need[s.charAt(r)]-- > 0)
                count--;

            while (count == 0) {         // count == 0 means all chars in t are covered
                if (r - l + 1 < span) {
                    span = r - (start = l) + 1;
                }
                if (need[s.charAt(l++)]++ == 0)
                    count++;
            }
        }
        return span == s.length() + 1 ? "" : s.substring(start, start + span);
    }
}
