/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/2/13
 * Time: 12:40 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class RussianRoulette80Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long n = in.nextLong();
        long k = in.nextLong();
        int p = in.nextInt();
        long s = n - k;
        long start = 0, end = 0;
        if (s <= k) {
            start = 0;
            end = s * 2;
        } else {
            start = s - k;
            end = n;
            if (start % 2 == 1) {
                start++;
                end--;
            }
        }

        while (p-- > 0) {
            long x = in.nextLong();
            if (x <= start) {
                System.out.print('.');
            } else if (x >= end)
                System.out.print('X');
            else {
                if ((x - start) % 2 == 0)
                    System.out.print('X');
                else
                    System.out.print('.');
            }
        }
    }
}
