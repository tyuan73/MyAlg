package projecteuler1;

/**
 * Created by yuantian on 9/18/14.
 */

import java.util.*;

public class Selfpowers {
    static long M = 10000000000L;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        long total = 0L;
        for(int i = 1; i <= n; i++) {
            total += pow(i);
            total %= M;
        }
        System.out.println(total);
    }

    static long pow(long n) {
        long res = 1L;
        long base = n;
        while(n > 0) {
            if ((n&1) == 1)
                res = multiply(res, base);
            base = multiply(base, base);
            n >>= 1;
        }
        return res;
    }

    static long multiply(long a, long b) {
        long[] res = new long[10];
        int index = 10;
        while(b > 0) {
            index--;
            long c = (b % 10) * a;
            b /= 10;
            for(int i = 9, j = index; i >= 0 && j >= 0; i--, j--) {
                res[j] += c % 10;
                c /= 10;
                c += res[j] / 10;
                res[j] %= 10;
            }
        }

        long ret = 0;
        for(long x : res)
            ret = ret * 10 + x;
        return ret % M;
    }
}
