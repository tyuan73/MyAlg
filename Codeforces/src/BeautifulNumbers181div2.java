/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/25/13
 * Time: 11:43 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class BeautifulNumbers181div2 {
    static long MOD = 1000000007;

    static long mult(long a, long b) {
        long result = 1;
        while (b > 0) {
            if (b % 2 == 1)
                result = result * a % MOD;
            b >>= 1;
            a = a * a % MOD;
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int a = in.nextInt();
        int b = in.nextInt();
        int n = in.nextInt();
        long total = 0;
        long[] p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i > n / 2 + 1) {
                p[i] = p[n - i];
                continue;
            }
            p[i] = (p[i - 1] * (n - i + 1)) % MOD;
            p[i] = (p[i] * mult(i, MOD - 2)) % MOD;
        }

        for (int i = 0; i <= n; i++) {
            int sum = i * a + (n - i) * b;
            if (isGood(sum, a, b)) {
                total = (total + p[i]) % MOD;
            }
        }
        System.out.println(total);
    }

    static boolean isGood(int x, int a, int b) {
        while (x > 0) {
            int y = x % 10;
            if (y != a && y != b)
                return false;
            x /= 10;
        }
        return true;
    }
}
