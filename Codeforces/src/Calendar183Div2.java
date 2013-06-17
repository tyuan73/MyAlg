/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/13/13
 * Time: 10:33 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class Calendar183Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String s1 = in.next();
        String s2 = in.next();
        String[] dd1 = s1.split(":");
        int y1 = Integer.parseInt(dd1[0]);
        int m1 = Integer.parseInt(dd1[1]);
        int d1 = Integer.parseInt(dd1[2]);
        String[] dd2 = s2.split(":");
        int y2 = Integer.parseInt(dd2[0]);
        int m2 = Integer.parseInt(dd2[1]);
        int d2 = Integer.parseInt(dd2[2]);

        int date1 = y1 * 10000 + m1 * 100 + d1;
        int date2 = y2 * 10000 + m2 * 100 + d2;

        if (date1 > date2) {
            int x = date1;
            date1 = date2;
            date2 = x;
        }
        int total = 0;
        while (date1 < date2) {
            total++;
            date1++;
            if (isLeap(date1 / 10000))
                months[1] = 29;
            else
                months[1] = 28;

            int m = (date1 % 10000) / 100;
            if (date1 % 100 > months[m - 1]) {
                date1 = (date1 / 100 + 1) * 100 + 1;
            }
            if ((date1 % 10000) / 100 == 13) {
                date1 = (date1 / 10000 + 1) * 10000 + 101;
            }
        }

        System.out.println(total);
    }

    static boolean isLeap(int yr) {
        if (yr % 400 == 0)
            return true;
        if (yr % 100 == 0)
            return false;
        if (yr % 4 == 0)
            return true;
        return false;
    }
}
