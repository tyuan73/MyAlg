/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/13/13
 * Time: 11:59 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class RectanglePuzzleII183Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        long a = in.nextInt();
        long b = in.nextInt();

        long gcd = a > b ? gcd(b, a) : gcd(a, b);
        a = a / gcd;
        b = b / gcd;

        long r = Math.min(n / a, m / b);
        long len = a * r;
        long wid = b * r;
        long x1 = x - (len + 1) / 2;
        long y1 = y - (wid + 1) / 2;
        if (x1 < 0)
            x1 = 0;
        if (y1 < 0)
            y1 = 0;
        long x2 = x1 + len;
        long y2 = y1 + wid;
        if (x2 > n) {
            x2 = n;
            x1 = n - len;
        }
        if (y2 > m) {
            y2 = m;
            y1 = m - wid;
        }
        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
    }

    static long gcd(long a, long b) {
        while (a > 0) {
            long x = a;
            a = b % a;
            b = x;
        }
        return b;
    }
}
