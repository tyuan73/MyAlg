/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/1/13
 * Time: 10:41 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class LittleElephantAndSorting129Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++)
            a[i] = in.nextInt();

        long total = 0;
        int max = a[0];
        for(int i = 1; i < n; i++) {
            if(a[i] >= max) {
                max = a[i];
            } else if (a[i] < a[i-1])
                total += a[i-1] - a[i];
        }

        System.out.println(total);
    }
}
