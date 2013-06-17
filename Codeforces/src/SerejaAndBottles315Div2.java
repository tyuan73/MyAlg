/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 6/8/13
 * Time: 10:55 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class SerejaAndBottles315Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[][] bottles = new int[n][2];
        for (int i = 0; i < n; i++) {
            bottles[i][0] = in.nextInt();
            bottles[i][1] = in.nextInt();
        }

        int ret = n;
        boolean[] opened = new boolean[n];
        for (int i = 0; i < n; i++) {
            int toopen = bottles[i][1];
            for (int j = 0; j < n; j++) {
                if (j == i || opened[j] || toopen != bottles[j][0]) continue;
                ret--;
                opened[j] = true;
            }
        }

        System.out.println(ret);
    }
}
