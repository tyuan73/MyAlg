/*

*/

import java.util.*;
import java.io.*;

public class KnightProbabilityinChessboard688 {
    static final int[][] steps = {{2, 1}, {-2, 1}, {2, -1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    /**
     * One Dimension array.
     */
    public double knightProbability(int N, int K, int r, int c) {
        double ans = 0.0;
        double[] count = new double[N * N];
        double[] next = new double[N * N];
        count[r * N + c] = 1;
        while (K-- > 0) {
            Arrays.fill(next, 0.0);
            for (int i = 0; i < N * N; i++) {
                double d = count[i];
                int x = i / N, y = i % N;
                for (int[] s : steps) {
                    int x1 = x + s[0], y1 = y + s[1];
                    if (x1 >= 0 && x1 < N && y1 >= 0 && y1 < N)
                        next[x1 * N + y1] += d / 8;
                }
            }
            double[] t = count;
            count = next;
            next = t;
        }
        for (double d : count)
            ans += d;
        return ans;
    }

    /**
     * 2D array solution. much easier
     */
    public double knightProbability2D(int N, int K, int r, int c) {
        double ans = 0.0;
        double[][] count = new double[N][N];
        count[r][c] = 1;
        while (K-- > 0) {
            double[][] next = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int[] s : steps) {
                        int x = i + s[0], y = j + s[1];
                        if (x >= 0 && x < N && y >= 0 && y < N)
                            next[x][y] += count[i][j] / 8;
                    }
                }
            }
            count = next;
        }
        for (double[] d : count)
            for (double d1 : d)
                ans += d1;
        return ans;
    }
}