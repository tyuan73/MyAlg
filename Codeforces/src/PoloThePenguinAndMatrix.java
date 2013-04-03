/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/2/13
 * Time: 10:39 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class PoloThePenguinAndMatrix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int d = in.nextInt();
        int[] a = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            a[i] = in.nextInt();
        }

        Arrays.sort(a);
        int m1 = a[n * m / 2];
        int max1 = 0;
        for (int i = 0; i < n * m; i++) {
            int diff = Math.abs(m1 - a[i]);
            if ((diff % d) != 0) {
                System.out.println(-1);
                return;
            }
            max1 += diff / d;
        }

        System.out.println(max1);
    }
}
