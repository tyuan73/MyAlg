/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/2/13
 * Time: 10:55 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class Blackjack80Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] p = new int[16];
        for (int i = 1; i <= 11; i++) {
            p[i] = 4;
        }
        p[10] = 15;
        if (n < 10)
            System.out.println(0);
        else
            System.out.println(p[n - 10]);
    }
}
