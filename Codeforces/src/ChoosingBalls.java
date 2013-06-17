/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/9/13
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Arrays;
import java.util.Scanner;

public class ChoosingBalls {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int q = in.nextInt();
        long[] v = new long[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++)
            v[i] = in.nextLong();
        for (int i = 0; i < n; i++)
            c[i] = in.nextInt();

        long INF = (long) 1e15;
        long[] dp = new long[n + 1];
        while (q-- > 0) {
            long a = in.nextInt();
            long b = in.nextInt();
            Arrays.fill(dp, -INF);
            dp[0] = 0;
            int max = 0, max2 = 1;
            for (int i = 0; i < n; i++) {
                int ci = c[i];
                long vi = v[i];
                long newV = Math.max(b * vi, dp[ci] + a * vi);
                int index = ci == max ? max2 : max;
                newV = Math.max(newV, dp[index] + b * vi);

                if (newV > dp[ci]) {
                    dp[ci] = newV;
                    if (ci != max) {
                        if (dp[ci] > dp[max]) {
                            max2 = max;
                            max = ci;
                        } else if (dp[ci] > dp[max2]) {
                            max2 = ci;
                        }
                    }
                }
            }
            System.out.println(dp[max]);
        }
    }
}
