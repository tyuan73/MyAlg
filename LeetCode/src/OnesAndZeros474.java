/**
 * Created by yuantian on 2/6/17.
 */
public class OnesAndZeros474 {
    /**
     * DP - backsack 2d
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] count = new int[len][2];
        for (int i = 0; i < len; i++) {
            String s = strs[i];
            for (int j = 0; j < s.length(); j++)
                count[i][s.charAt(j) - '0']++;
        }

        int[][] dp = new int[m + 1][n + 1];
        for (int[] c : count) {
            for (int j = m; j >= c[0]; j--) {
                for (int k = n; k >= c[1]; k--) {
                    dp[j][k] = Math.max(dp[j - c[0]][k - c[1]] + 1, dp[j][k]);
                }
            }
        }
        return dp[m][n];
    }
}
