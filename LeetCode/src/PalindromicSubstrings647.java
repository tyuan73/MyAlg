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

    /**
     * DP version
     */
    public int countSubstrings_DP(String s) {
        int n = s.length();
        int count = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) { // must go down, otherwise, "dp[i][j] = dp[i + 1][j - 1];" below won't work
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3)
                        dp[i][j] = true;
                    else
                        dp[i][j] = dp[i + 1][j - 1];
                }
                if (dp[i][j]) count++;
            }
        }

        return count;
    }
}