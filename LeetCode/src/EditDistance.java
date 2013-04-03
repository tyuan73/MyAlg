/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */

public class EditDistance {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }

    /**
     * DP
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = i == 0 ? j : i;
                    continue;
                }
                int min = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = Math.min(min, dp[i - 1][j - 1]);
                else
                    dp[i][j] = Math.min(min, dp[i - 1][j - 1] + 1);
            }
        }
        return dp[n][m];
    }
}
