/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 12:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int n = p.length();
        int m = s.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        // this is important to address matching like "" => ".*c*b*"
        for (int i = 2; i <= n; i += 2) {
            if (p.charAt(i - 1) == '*')
                dp[i][0] = dp[i - 2][0];
        }

        for (int i = 1; i <= n; i++) {
            char p1 = p.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                char s1 = s.charAt(j - 1);
                if (p1 == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i - 2][j];
                    if (s1 == p.charAt(i - 2) || p.charAt(i - 2) == '.')
                        dp[i][j] = dp[i][j] || dp[i - 1][j - 1] || dp[i][j - 1];
                } else if (p1 == '.' || p1 == s1) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[n][m];
    }
}
