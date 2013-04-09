/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/25/13
 * Time: 5:28 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class WildcardMatching {
    /**
     * Two dimension array DP. It won't pass big test because "Memory Limit Exceeded".
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int n = p.length();
        int m = s.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*')
                dp[i][0] = true;
            else
                break;
        }

        for (int i = 1; i <= n; i++) {
            char ch = p.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                char ch1 = s.charAt(j - 1);
                if (ch == '?' || ch == ch1)
                    dp[i][j] = dp[i - 1][j - 1];
                else if (ch == '*')
                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
            }
        }

        return dp[n][m];
    }

    /**
     * One dimension.
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        int n = p.length();
        int m = s.length();
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            char ch = p.charAt(i - 1);
            if (ch == '*') {
                boolean f = dp[0];
                for (int j = 1; j <= m; j++) {
                    boolean cur = f || dp[j - 1] || dp[j];
                    dp[j - 1] = f;
                    f = cur;
                }
                dp[m] = f;
            } else {
                for (int j = m; j > 0; j--) {
                    char ch1 = s.charAt(j - 1);
                    if (ch == '?' || ch == ch1)
                        dp[j] = dp[j - 1];
                    else
                        dp[j] = false;
                }
                dp[0] = false;
            }
        }

        return dp[m];
    }
}
