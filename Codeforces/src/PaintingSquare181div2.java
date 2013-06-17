/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/25/13
 * Time: 11:42 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class PaintingSquare181div2 {

    static final int MOD = 7340033;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        // calculate dp
        long[][] sp = new long[31][1001];
        long[][] dp = new long[31][1001];
        dp[0][0] = 1;
        for (int i = 1; i <= 30; i++) {
            for (int j = 0; j <= 1000; j++) {
                for (int k = 0; k <= j; k++)
                    sp[i][j] += dp[i - 1][k] * dp[i - 1][j - k];
                sp[i][j] %= MOD;
            }

            dp[i][0] = 1;
            for (int j = 1; j <= 1000; j++) {
                for (int k = 0; k <= j - 1; k++)
                    dp[i][j] += sp[i][k] * sp[i][j - 1 - k];
                dp[i][j] %= MOD;
            }
        }

        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int c = 0;
            while ((n & 1) == 1 && n > 1) {
                c++;
                n >>= 1;
            }
            System.out.println(dp[c][k]);
        }
    }
}
