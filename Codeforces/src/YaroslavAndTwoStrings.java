/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/11/13
 * Time: 10:45 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class YaroslavAndTwoStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        String s = in.next();
        String w = in.next();
        int MOD = 1000000007;
        long total = 1, x = 1, y = 1, z = 1;
        for (int i = 0; i < n; i++) {
            int x1 = 0, x2 = 0, x3 = 0;
            char sc = s.charAt(i), wc = w.charAt(i);
            if (sc != '?' && wc != '?') {
                if (sc > wc)
                    x1 = 1;
                else if (sc < wc)
                    x2 = 1;
                else // if sc == wc
                    x3 = 1;
            } else if (sc == '?' && wc != '?') {
                x1 = '9' - wc;
                x2 = wc - '0';
                x3 = 1;
            } else if (wc == '?' && sc != '?') {
                x1 = sc - '0';
                x2 = '9' - sc;
                x3 = 1;
            } else {
                x1 = 45;
                x2 = 45;
                x3 = 10;
            }
            total = (total * (x1 + x2 + x3)) % MOD;
            x = (x * (x1 + x3)) % MOD;
            y = (y * (x2 + x3)) % MOD;
            z = (z * x3) % MOD;
        }

        total = (total + 2 * MOD - x - y + z) % MOD;
        System.out.println(total);

    }
}
