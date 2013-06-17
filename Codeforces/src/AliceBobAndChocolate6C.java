/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/29/13
 * Time: 12:02 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class AliceBobAndChocolate6C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] t = new int[n];
        for (int i = 0; i < n; i++)
            t[i] = in.nextInt();

        int l = 0, r = n - 1;
        int t1 = 0, t2 = 0;
        while (l <= r) {
            if (t1 <= t2)
                t1 += t[l++];
            else
                t2 += t[r--];
        }
        //if (t1 <= t2)
        //    l++;
        System.out.println(l + " " + (n - l));
    }
}
