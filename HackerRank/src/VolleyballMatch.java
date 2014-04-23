/**
 * Created by yuantian on 4/23/14.
 */

import java.util.*;

public class VolleyballMatch {
    static long P = 1000000007;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long a = in.nextLong();
        long b = in.nextLong();

        if (a < b) {
            long x = a;
            a = b;
            b = x;
        }

        long[][] dp = new long[25][25];

        if (a - b <= 1 || a < 25 || (a > 25 && a-b != 2)) {
            System.out.println(0);
        } else {
            Arrays.fill(dp[0], 1);
            for (int i = 0; i < 25; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i < 25; i++) {
                for (int j = 1; j < 25; j++)
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % P;
            }

            if (a == 25) {
                System.out.println(dp[24][(int)b]);
            } else {
                b -= 24;
                long out = dp[24][24];
                //System.out.println(out);
                long base = 2;
                while (b > 0) {
                    if ((b & 1) > 0)
                        out = (out * base) % P;
                    b >>= 1;
                    base = (base * base) % P;
                }
                System.out.println(out);
            }
        }
    }
}
