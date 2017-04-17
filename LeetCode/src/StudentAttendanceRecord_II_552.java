/**
 * Created by yuantian on 4/17/17.
 */

import java.util.*;

public class StudentAttendanceRecord_II_552 {
    final static int P = 1000000007;

    public int checkRecord(int n) {
        int[][] dp = new int[n + 1][3];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = (int) ((dp[i - 1][0] + (long) dp[i - 1][1] + dp[i - 1][2]) % P);
            dp[i][1] = dp[i - 1][0];
            dp[i][2] = dp[i - 1][1];
        }

        long count = dp[n][0] + (long) dp[n][1] + dp[n][2];
        for (int i = 1; i <= n; i++) {
            long left = (dp[i - 1][0] + (long) dp[i - 1][1] + dp[i - 1][2]) % P;
            long right = (dp[n - i][0] + (long) dp[n - i][1] + dp[n - i][2]) % P;
            count = (left * right + count) % P;
        }
        return (int) count;
    }
}
