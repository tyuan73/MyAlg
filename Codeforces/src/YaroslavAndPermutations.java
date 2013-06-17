/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/11/13
 * Time: 10:35 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class YaroslavAndPermutations {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] count = new int[1001];
        int max = 0;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            count[a]++;
            max = Math.max(max, count[a]);
        }
        if (max <= (n + 1) / 2)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
