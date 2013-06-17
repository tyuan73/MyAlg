/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/29/13
 * Time: 10:31 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class LuckySumOfDigits84Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int x = 0, y = 0;
        boolean found = false;
        for (int i = n / 7; i >= 0; i--) {
            int m = n - i * 7;
            if (m % 4 == 0) {
                x = i;
                y = m / 4;
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < y; i++)
            System.out.print(4);
        for (int i = 0; i < x; i++)
            System.out.print(7);
        System.out.println();
    }
}
