/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 4/17/13
 * Time: 11:24 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Arrays;
import java.util.Scanner;

public class AlienLanguages {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long MOD = 100000007;
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k2 = (int) ((n + 1) / 2);
            int k1 = (int) n - k2;

            long[] dp1 = new long[n + 1];
            long[] dp2 = new long[n + 1];
            Arrays.fill(dp1, 1L);
            dp1[0] = k2;
            long total = k2;
            for (int i = 2; i <= m; i++) {
                dp2[0] = total;
                //dp2[1] = dp1[0];
                total = 0;
                for (int j = 1; j <= n; j++) {
                    if ((j & 1) == 0) {
                        dp2[j] = (dp1[j / 2] + dp2[j - 1]) % MOD;
                    } else
                        dp2[j] = dp2[j - 1];
                    if (j > k1)
                        total = (total + dp2[j]) % MOD;
                }
                long[] temp = dp1;
                dp1 = dp2;
                dp2 = temp;
            }
            System.out.println(total);
        }
    }
}

/**
 4
 1 3
 2 3
 3 2
 4 3

 1
 3
 6
 21
 **/