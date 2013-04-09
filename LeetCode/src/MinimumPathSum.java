/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/7/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Arrays;

public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = grid[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i != n - 1)
                    dp[i + 1][j] = dp[i][j] + grid[i + 1][j];
                if (j != m - 1)
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + grid[i][j + 1]);
            }
        }
        return dp[n - 1][m - 1];
    }
}
