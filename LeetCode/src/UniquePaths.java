/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/25/13
 * Time: 3:44 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    /**
     *  Use one dimension array.
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++) {
                dp[j] += dp[j-1];
            }
        return dp[n-1];
    }
}
