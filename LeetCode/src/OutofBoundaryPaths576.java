/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/7/17
 * Time: 11:47 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class OutofBoundaryPaths576 {
    static final int[][] steps = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static final int P = 1000000007;

    public int findPaths(int m, int n, int N, int row, int col) {
        int[][][] dp = new int[N + 1][m][n];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    for (int[] step : steps) {
                        int x = j + step[0], y = k + step[1];
                        if (x < 0 || x >= m || y < 0 || y >= n)
                            dp[i][j][k]++;
                        else
                            dp[i][j][k] += dp[i - 1][x][y];
                        if (dp[i][j][k] >= P)
                            dp[i][j][k] -= P;
                    }
                }
            }
        }
        return dp[N][row][col];
    }
}
