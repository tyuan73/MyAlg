/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/27/13
 * Time: 12:06 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

/**
 * Lucas Theorem:
 * http://planetmath.org/lucasstheorem
 * <p/>
 * But if p is very large, it is not allowed to allocate memory to memorize n! in Pascal Triangle, we
 * have to use "modular inverse" and "Euler's Theorem"
 * <p/>
 * http://en.wikipedia.org/wiki/Modular_multiplicative_inverse
 * <p/>
 * original post:
 * http://stackoverflow.com/questions/13802649/how-to-calculate-ncr-modulo-1000000007-when-n-400000?rq=1
 * <p/>
 * Use the modular inverse. (a / b) mod p = (a * b^-1) mod p
 * We have:
 * (n choose r) = n! / (r!*(n - r)!) = n! * (r!*(n - r)!)^-1 (mod p)
 * For p as a prime, the inverse of any number x mod p is x^(p - 2) mod p (Euler's Theorem).
 * that is:
 * x^-1 % p = x^(p-2) % p
 */

public class BinCoeff {
    final static int MOD = 1009;
    static long[] factorial = new long[MOD + 1];
    static long[] inverse = new long[MOD + 1];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long f = 1, v = 1;
        factorial[0] = inverse[0] = 1;
        for (int i = 1; i <= MOD; i++) {
            f = (f * i) % MOD;
            factorial[i] = f;
            v = (v * modInverse(i)) % MOD;
            inverse[i] = v;
        }

        int t = in.nextInt();
        while (t-- > 0) {
            long n = in.nextLong();
            long k = in.nextLong();

            long res = 1;
            while (n > 0 && k > 0) {
                int n1 = (int) (n % MOD);
                int k1 = (int) (k % MOD);

                if (k1 > n1) {
                    res = 0;
                    break;
                }

                res = res * factorial[n1] % MOD;
                res = res * inverse[k1] % MOD;
                res = res * inverse[n1 - k1] % MOD;

                n /= MOD;
                k /= MOD;
            }
            System.out.println(res);
        }
    }

    static long modInverse(long i) {
        long pow = MOD - 2;
        long ret = 1;
        while (pow > 0) {
            if ((pow & 1) > 0)
                ret = ret * i % MOD;
            i = i * i % MOD;
            pow >>= 1;
        }
        return ret;
    }
}
