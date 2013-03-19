/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */

public class ImplementStrStr {
    public static void main(String[] args) {
        ImplementStrStr is = new ImplementStrStr();
        is.strStrKMP("a", "");
    }

    /**
     * Implementation in KMP. See DP solution below.
     */
    public String strStrKMP(String haystack, String needle) {
        if (needle.length() == 0)
            return haystack;

        int[] prefix = computePrefix(needle);

        int m = needle.length();
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && needle.charAt(j) != haystack.charAt(i))
                j = prefix[j - 1];
            if (needle.charAt(j) == haystack.charAt(i))
                j++;

            if (j == needle.length())
                return haystack.substring(i - m + 1);
        }

        return null;

    }

    int[] computePrefix(String p) {
        int[] prefix = new int[p.length()];
        int j = 0;
        for (int i = 1; i < p.length(); i++) {
            while (j > 0 && p.charAt(j) != p.charAt(i))
                j = prefix[j - 1];
            if (p.charAt(j) == p.charAt(i))
                j++;
            prefix[i] = j;
        }
        return prefix;
    }

    /**
     * One dimension DP. It can also be done in KMP.
     */
    public String strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            char ch = needle.charAt(i - 1);
            for (int j = n; j >= i; j--) {
                if (ch == haystack.charAt(j - 1))
                    dp[j] = dp[j - 1] + 1;
            }
        }
        for (int i = 0; i <= n; i++) {
            if (dp[i] == m)
                return haystack.substring(i - m);
        }
        return null;
    }
}
