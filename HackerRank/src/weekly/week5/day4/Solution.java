package weekly.week5.day4;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 6/19/14
 * Time: 10:57 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();

        long P = 1000000007;

        long[][] dp = new long[n+1][k+2];
        long[][] dp2 = new long[n+1][k+2];

        dp[1][0] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i][0] = 1;
            for(int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % P;
                if (j >= i) {
                    dp[i][j] -= dp[i-1][j-i];
                    if (dp[i][j] < 0)
                        dp[i][j] += P;
                }
                if (dp[i][j] == 0)
                    break;
            }
        }

        dp2[1][0] = 1;
        for(int i = 2; i <= n; i++) {
            dp2[i][0] = 1;
            for(int j = 1; j <= k; j++) {
                dp2[i][j] = (dp2[i-1][j] + dp2[i-1][j-1] * (i-1)) % P;
            }
        }

        int start = 0;
        if((k & 1) == 1) {
            start = 1;
        }
        long out = 0;
        for(int i = start; i <= k; i += 2) {
            out += dp[n][i];
            if(out > P)
                out -= P;
        }
        System.out.print(out + " ");

        out = 0;
        for(int i = 0; i <= k; i++) {
            out += dp2[n][i];
            if(out > P)
                out -= P;
        }
        System.out.println(out);

        /*
        for(long[] l : dp) {
            for(long x : l)
                System.out.print(" " + x);
            System.out.println();
        }
        for(long[] l : dp2) {
            for(long x : l)
                System.out.print(" " + x);
            System.out.println();
        }
        */

    }
}
