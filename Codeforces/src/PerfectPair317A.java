/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 6/16/13
 * Time: 10:19 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class PerfectPair317A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long x = in.nextLong();
        long y = in.nextLong();
        long m = in.nextLong();
        if (y < x) {
            long t = x;
            x = y;
            y = t;
        }

        if (y >= m) {
            System.out.println(0);
            return;
        }
        if (y <= 0) {
            System.out.println(-1);
            return;
        }

        // y > 0 and y < m
        long total = 0;
        while (y < m) {
            if (x < 0) {
                if (x % y == 0) {
                    total += (y - x) / y;
                    x = y;
                } else {
                    total += (y - x) / y + 1;
                    long t = y * total + x;
                    x = y;
                    y = t;
                }
            } else {
                long t = x + y;
                x = y;
                y = t;
                total++;
            }
        }
        System.out.println(total);
    }
}
