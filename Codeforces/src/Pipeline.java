/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/24/13
 * Time: 2:18 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class Pipeline {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long n = in.nextLong();
        long k = in.nextLong();

        if (n == 1) {
            System.out.println(0);
            return;
        }
        //if (k >= n) {
        //    System.out.println(1);
        //    return;
        //}

        long total = (k + 2) * (k - 1) / 2 - (k - 2);
        if (total < n) {
            System.out.println(-1);
            return;
        }

        /**
         * non-binary search version, O(n)
         * it will failed because of "over time limit error" on this case:
         *  499999998500000001 1000000000   => 999955279
         *
         total = 1;
         for(int i = (int)k; i >= 2; i--) {
         total += i - 1;
         if(total >= n) {
         System.out.println(k-i+1);
         return;
         }
         }
         */

        /**
         * binary search search version. O(lgn)
         */
        int i = 2, j = (int) k;
        while (i < j) {
            int m = (i + j + 1) / 2;
            total = (k + m) * (k - m + 1) / 2 - (k - m);
            if (total < n)
                j = m - 1;
            else
                i = m;
        }
        System.out.println(k - i + 1);
    }
}
