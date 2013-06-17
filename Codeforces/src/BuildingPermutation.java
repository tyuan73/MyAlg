/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/21/13
 * Time: 11:48 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BuildingPermutation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();

        shuffle(a);
        Arrays.sort(a);
        long total = 0;
        for (int i = 0; i < n; i++) {
            total += Math.abs(a[i] - i - 1);
        }
        System.out.println(total);
    }

    static void shuffle(long[] a) {
        int n = a.length;
        Random rnd = new Random(System.nanoTime());
        for (int i = 0; i < n; ++i) {
            int v = rnd.nextInt(n - i);
            long tmp = a[i + v];
            a[i + v] = a[i];
            a[i] = tmp;
        }
    }
}
