/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/25/13
 * Time: 10:31 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Arrays;
import java.util.Scanner;

public class Array181Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];
        int countNeg = 0;
        int countZero = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (a[i] < 0)
                countNeg++;
            if (a[i] == 0)
                countZero++;
        }
        Arrays.sort(a);
        System.out.println(1 + " " + a[0]);

        if (countNeg % 2 == 0) {
            countNeg--;
            countZero++;
        }

        System.out.print(n - 1 - countZero);
        for (int i = 1; i < countNeg; i++)
            System.out.print(" " + a[i]);
        for (int i = countNeg + countZero; i < n; i++)
            System.out.print(" " + a[i]);
        System.out.println();
        System.out.print(countZero);
        for (int i = countNeg; i < countNeg + countZero; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();

    }
}
