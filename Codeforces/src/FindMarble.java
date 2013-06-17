/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/21/13
 * Time: 11:37 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class FindMarble {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int s = in.nextInt() - 1;
        int t = in.nextInt() - 1;
        int[] shuffle = new int[n];
        for (int i = 0; i < n; i++)
            shuffle[i] = in.nextInt() - 1;

        boolean[] f = new boolean[n];
        f[s] = true;
        int total = 0;
        while (s != t) {
            s = shuffle[s];
            if (f[s]) {
                System.out.println(-1);
                return;
            }
            f[s] = true;
            total++;
        }
        System.out.println(total);
    }
}
