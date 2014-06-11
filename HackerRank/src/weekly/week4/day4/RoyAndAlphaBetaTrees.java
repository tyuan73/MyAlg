package weekly.week4.day4;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 6/5/14
 * Time: 11:16 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class RoyAndAlphaBetaTrees {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        final long P = 1000000009;
        long[] cat = new long[151];
        cat[0] = 1L;
        for(int i = 1; i < 151; i++) {
            long temp = 0;
            for(int l = 0, r = i-1; r >= 0; l++, r--) {
                temp =  (cat[l] * cat[r] + temp) % P;
            }
            cat[i] = temp;
        }

        /*
        for (int i = 0; i < 151; i++) {
            System.out.println(i + " : " + cat[i]);
        }
        */

        long[][][] dp = new long[151][151][2];
        dp[1][1][0] = 1;
        for(int i = 2; i < 151; i++) {
            for(int j = 1; j <= i; j++) {
                int l = j - 1, r = i - j;
                for(int lIdx = 1; lIdx <= l; lIdx++) {
                    dp[i][lIdx][0] += (dp[l][lIdx][1] * cat[r]);
                    dp[i][lIdx][0] %= P;
                    dp[i][lIdx][1] += (dp[l][lIdx][0] * cat[r]);
                    dp[i][lIdx][1] %= P;
                }
                for(int rIdx = j + 1; rIdx <= i; rIdx++) {
                    dp[i][rIdx][0] += dp[r][rIdx - j][1] * cat[l];
                    dp[i][rIdx][0] %= P;
                    dp[i][rIdx][1] += dp[r][rIdx - j][0] * cat[l];
                    dp[i][rIdx][1] %= P;
                }
                dp[i][j][0] += cat[l]*cat[r];
                dp[i][j][0] %= P;
            }
        }

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            long a = in.nextLong();
            long b = in.nextLong();

            long[] A = new long[n+1];
            for(int i = 1; i <= n; i++)
                A[i] = in.nextLong();

            Arrays.sort(A);

            long res = 0;
            for(int i = 1; i <= n; i++) {
                res += (((A[i] * dp[n][i][0]) % P) * a) % P - (((A[i] * dp[n][i][1]) % P) * b) % P;
                //long temp = dp[n][i][0] * a - dp[n][i][1] * b;

                //res += A[i] * (dp[n][i][0] * a - dp[n][i][1] * b)
                res %= P;
            }

            if (res < 0)
                res += P;
            System.out.println(res);
        }
    }
}
