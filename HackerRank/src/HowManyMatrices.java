/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class HowManyMatrices {
    final static int MOD = 10007;
    static long[] factorial = new long[MOD + 1];
    static long[] inverse = new long[MOD + 1];

    // initialize factorial & inverse
    static {
        factorial[0] = inverse[0] = 1;
        long f = 1, v = 1;
        for (int i = 1; i <= MOD; i++) {
            f = f * i % MOD;
            factorial[i] = f;
            v = v * calModInverse(i) % MOD;
            inverse[i] = v;
        }
    }

    static long calModInverse(long i) {
        int pow = MOD - 2;
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) > 0)
                res = res * i % MOD;
            pow >>= 1;
            i = i * i % MOD;
        }
        return res;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n >= MOD) {
            System.out.println(2);
            return;
        }
        if(n == 1) {
            System.out.println(1);
            return;
        }

        long ans = 2;
        int sqrt = (int) Math.sqrt(n);
        for (int m = 2; m <= sqrt; m++) {
            if (n % m == 0) {
                int m1 = n / m;

                long res = factorial[n];
                for (int k = 2; k < m; k++)
                    res = res * factorial[k] % MOD;
                for (int k = m1; k < m + m1; k++)
                    res = res * inverse[k] % MOD;

                if (m != m1)
                    ans = (ans + 2 * res) % MOD;
                else
                    ans = (ans + res) % MOD;
            }
        }

        System.out.println(ans);
    }
}
