/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/8/13
 * Time: 2:34 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class YaroslavAndSequence182Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[2 * n - 1];
        int count = 0;
        int total = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            a[i] = in.nextInt();
            if (a[i] < 0) {
                total += -a[i];
                count++;
            } else
                total += a[i];
        }

        count %= n;
        if (count % 2 == 1 && n % 2 == 0) {
            int negMax = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++)
                if (a[i] <= 0)
                    negMax = Math.max(negMax, a[i]);
            total += 2 * negMax;
        }

        System.out.println(total);
    }
}
