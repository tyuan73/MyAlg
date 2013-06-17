/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/21/13
 * Time: 11:28 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class SlightlyDecreasingPermutations {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++)
            p[i] = i + 1;

        for (int i = n - 1 - k, j = n - 1; i < j; i++, j--) {
            int x = p[i];
            p[i] = p[j];
            p[j] = x;
        }

        System.out.print(p[0]);
        for (int i = 1; i < n; i++)
            System.out.print(" " + p[i]);
        System.out.println();
    }
}
