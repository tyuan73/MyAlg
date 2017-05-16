package practice.dp;

/**
 * Created by yuantian on 5/4/17.
 */

import java.io.*;
import java.util.*;

public class Equal {
    static final int[] C = {1, 2, 5};

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int[] dp = cal();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] c = new int[n];
            for (int i = 0; i < n; i++) {
                c[i] = in.nextInt();
            }

            int min = 1000000;
            for (int x : c) {
                min = Math.min(min, x);
                //max = Math.max(max, x);
            }
            int ans = Integer.MAX_VALUE;
            for (int m = min - 4; m <= min; m++) {
                int steps = 0;
                for (int x : c) {
                    steps += dp[x - m];
                }
                ans = Math.min(ans, steps);
            }
            System.out.println(ans);
        }
    }

    private static int[] cal() {
        int[] dp = new int[1010];
        for (int x : C) {
            for (int i = x; i <= 1009; i++) {
                if (dp[i] == 0 || dp[i] > dp[i - x] + 1)
                    dp[i] = dp[i - x] + 1;
            }
        }
        return dp;
    }
}