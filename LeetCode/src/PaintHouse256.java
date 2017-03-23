/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/22/17
 * Time: 10:41 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class PaintHouse256 {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int[][] dp = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < n; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
    }
}
