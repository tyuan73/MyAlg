/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 11:51 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class SqrtX {
    public int sqrt(int x) {
        long r1 = 0;
        long r2 = x;
        while (r1 < r2) {
            long m = (r1 + r2 + 1) / 2;
            //if (m * m == x)
            //    return (int) m;
            if (m * m > x) {
                r2 = m - 1;
            } else
                r1 = m;
        }

        return (int) r1; // or r2
    }

    /**
     * A better solution.
     *
     * @param x
     * @return
     */
    public int sqrt1(int x) {
        int r1 = 0;
        int r2 = x;
        while (r1 < r2) {
            int m = (r1 + r2 + 1) / 2;
            if (x / m < m) {
                r2 = m - 1;
            } else
                r1 = m;
        }

        return r1;
    }

    /**
     * The best solution which is based on Newton method.
     *
     * @param x
     * @return
     */
    public int sqrtNewton(int x) {
        double r = (double) x;
        while (Math.abs(r - x / r) > 0.0001) {
            r = (x / r + r) / 2;
        }
        return (int) r;
    }

    /**
     * a solution with bit operation. It finds the most signifisant bit
     * and then next bit until end.
     *
     */
    public int sqrtBitOp(int x) {
        long ans = 0;
        long bit = 1l << 16;
        while(bit > 0) {
            ans |= bit;
            if (ans * ans > x) {
                ans ^= bit;
            }
            bit >>= 1;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        SqrtX x = new SqrtX();
        System.out.println(x.sqrtNewton(214739559));
        System.out.println(x.sqrt(2147395599));
        System.out.println(x.sqrt1(2147395599));
        System.out.println(x.sqrtBitOp(2147395599));
    }
}
