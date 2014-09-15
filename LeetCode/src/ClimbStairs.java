public class ClimbStairs {
    public int climbStairs(int n) {
        if (n < 2)
            return 1;
        int a = 1, b = 1;
        while (n-- >= 2) {
            int x = a + b;
            a = b;
            b = x;
        }

        return b;
    }

    // why above solution is right?
    // look at the DP solution below
    // and you will find that it's just the sum of dp[i-1] + dp[i-2]
    // which is fibonacci sequence.
    public int climbStairsDP(int n) {
        if (n < 2)
            return 1;
        int[] steps = new int[n + 1];
        steps[0] = 1;
        steps[1] = 1;

        for (int i = 2; i <= n; i++)
            steps[i] = steps[i - 1] + steps[i - 2];

        return steps[n];
    }

    /**
     * a solution using matrix multiplication
     * also in O(log(n))
     * more generic
     */
    public int climbStairs1(int n) {
        int[][] a = {{0, 1}, {1, 1}};
        int[][] m = pow(a, n - 1);
        return m[0][1] + m[1][1];
    }

    private int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    private int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
}