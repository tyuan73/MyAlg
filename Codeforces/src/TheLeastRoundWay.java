/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/19/13
 * Time: 10:47 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Arrays;
import java.util.Scanner;

public class TheLeastRoundWay {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] m2 = new int[n][n];
        int[][] m5 = new int[n][n];
        boolean hasZero = false;
        int zeroR = 0, zeroC = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = in.nextInt();
                // treat zero as 10
                if (x == 0) {
                    hasZero = true;
                    zeroR = i;
                    zeroC = j;
                    m2[i][j] = 1;
                    m5[i][j] = 1;
                    continue;
                }
                while (x % 2 == 0) {
                    m2[i][j]++;
                    x /= 2;
                }
                while (x % 5 == 0) {
                    m5[i][j]++;
                    x /= 5;
                }
            }
        }

        int[][] backtrack = new int[n][n];
        int zeros = doDP(m2, backtrack);
        char[] path = getPath(backtrack);
        int z5 = doDP(m5, backtrack);
        if (z5 < zeros) {
            zeros = z5;
            path = getPath(backtrack);
        }

        if (hasZero && zeros >= 1) {
            zeros = 1;
            int i = 0;
            for (int j = 0; j < zeroC; j++)
                path[i++] = 'R';
            for (int j = 0; j < zeroR; j++)
                path[i++] = 'D';
            for (int j = zeroC; j < n - 1; j++)
                path[i++] = 'R';
            for (int j = zeroR; j < n - 1; j++)
                path[i++] = 'D';
        }
        System.out.println(zeros);
        System.out.println(path);
    }

    static char[] getPath(int[][] backtrack) {
        int n = backtrack.length;
        char[] path = new char[2 * n - 2];
        int curx = n - 1, cury = n - 1;
        for (int i = path.length - 1; i >= 0; i--) {
            if (backtrack[curx][cury] == 1) {
                path[i] = 'R';
                cury--;
            } else {
                path[i] = 'D';
                curx--;
            }
        }
        return path;
    }

    static int doDP(final int[][] t, int[][] backtrack) {
        int n = t.length;
        int[][] dp = new int[n][n];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = t[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i < n - 1) {
                    dp[i + 1][j] = dp[i][j] + t[i + 1][j];
                    backtrack[i + 1][j] = 0;
                }
                if (j < n - 1) {
                    int x = dp[i][j] + t[i][j + 1];
                    if (x < dp[i][j + 1]) {
                        dp[i][j + 1] = x;
                        backtrack[i][j + 1] = 1;
                    }
                }
            }
        }
        return dp[n - 1][n - 1];
    }
}
