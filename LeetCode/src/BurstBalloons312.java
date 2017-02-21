/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 2/20/17
 * Time: 10:59 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class BurstBalloons312 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] N = new int[n + 2];
        for (int i = 1; i <= n; i++)
            N[i] = nums[i - 1];
        N[0] = N[n + 1] = 1;

        int[][] dp = new int[n + 2][n + 2];
        for (int len = 1; len <= n; len++) {
            for (int left = 1, right = left + len - 1; right <= n; left++, right++) {
                for (int k = left; k <= right; k++)
                    dp[left][right] = Math.max(dp[left][right], N[left - 1] * N[k] * N[right + 1] + dp[left][k - 1] + dp[k + 1][right]);
            }
        }
        return dp[1][n];
    }
}
