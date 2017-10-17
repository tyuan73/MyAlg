package MoodysAnalyticsFallUniversityCodeSprint;

import java.util.*;

public class TileStackingProblem {
    static int P = 1000000007;

    /**
     * DP solution
     */
    static int tileStackingProblem(int n, int m, int k) {
        int[][] dp = new int[n + 2][m + 1];
        for (int i = 1; i <= n + 1; i++)
            dp[i][0] = 1;
        for (int i = 1; i <= m; i++) {
            dp[1][i] = 1;
            for (int j = 2; j <= n + 1; j++) {
                int l = Math.max(0, j - k - 1);
                dp[j][i] = dp[j][i - 1] - dp[l][i - 1];
                if (dp[j][i] < 0)
                    dp[j][i] += P;
                dp[j][i] += dp[j - 1][i];
                if (dp[j][i] > P)
                    dp[j][i] -= P;
            }
        }
        int ans = dp[n + 1][m] - dp[n][m];
        if (ans < 0) ans += P;
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int result = tileStackingProblem(n, m, k);
        System.out.println(result);
        in.close();
    }
}
