/**
 * Created by yuantian on 4/19/17.
 */

import java.util.*;

public class PaintFence276 {
    /**
     * DP, O(n). Can be improved to use O(0) space.
     */
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;
        int[][] dp = new int[n + 1][2];
        dp[1][0] = k;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) * (k - 1);
            dp[i][1] = dp[i - 1][0];
        }

        return dp[n][0] + dp[n][1];
    }

    /**
     * much simplified version of above solution. O(n) runtime with O(0) space
     */
    public int numWays2(int n, int k) {
        if (n == 0) return 0;

        int one = k, two = 0;
        for(int i = 1; i < n; i++) {
            one = (two + (two = one)) * (k-1);
        }

        return one + two;
    }

    /**
     * O(lg(n)) solution using matrix multiplication.
     *
     * dp[n+1] = dp[n-1] * [ k-1, 1]
     *                     [ k-1, 0]
     */
    public int numWays1(int n, int k) {
        if (n == 0 || k == 0) return 0;

        int[][] next = {{k - 1, 1}, {k - 1, 0}};
        int[][] pow = pow(next, n - 1);

        return k * (pow[0][0] + pow[0][1]);
    }

    private int[][] pow(int[][] matrix, int n) {
        int[][] ans = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ans = multiplication(ans, matrix);
            }
            matrix = multiplication(matrix, matrix);
            n >>= 1;
        }
        return ans;
    }

    private int[][] multiplication(int[][] m1, int[][] m2) {
        int[][] ans = new int[2][2];
        ans[0][0] = m1[0][0] * m2[0][0] + m1[0][1] * m2[1][0];
        ans[0][1] = m1[0][0] * m2[0][1] + m1[0][1] * m2[1][1];
        ans[1][0] = m1[1][0] * m2[0][0] + m1[1][1] * m2[1][0];
        ans[1][1] = m1[1][0] * m2[0][1] + m1[1][1] * m2[1][1];
        return ans;
    }
}
