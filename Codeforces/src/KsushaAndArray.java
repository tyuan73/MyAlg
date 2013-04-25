/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/22/13
 * Time: 10:35 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class KsushaAndArray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (a[i] < min)
                min = a[i];
        }

        if (min == 1)
            System.out.println(1);
        else {
            for (int i = 0; i < n; i++) {
                if (a[i] % min != 0) {
                    System.out.println(-1);
                    return;
                }
            }
            System.out.println(min);
        }
    }
}
