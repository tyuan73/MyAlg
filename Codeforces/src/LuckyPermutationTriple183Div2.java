/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/13/13
 * Time: 11:28 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class LuckyPermutationTriple183Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        if (n % 2 == 0) {
            System.out.println(-1);
            return;
        }
        int[] out = new int[n];
        for (int i = 0; i < n - 1; i++)
            System.out.print(i + " ");

        System.out.println(n - 1);

        int step = n / 2;
        for (int i = 0, next = n - 1; i < n; i++, next = (next + step) % n)
            out[i] = next;

        for (int i = 0; i < n - 1; i++)
            System.out.print(out[i] + " ");

        System.out.println(out[n - 1]);

        for (int i = 0; i < n - 1; i++)
            System.out.print(((i + out[i]) % n) + " ");

        System.out.println(((n - 1 + out[n - 1]) % n));
    }
}
