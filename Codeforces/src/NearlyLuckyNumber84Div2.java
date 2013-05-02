/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/29/13
 * Time: 10:12 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class NearlyLuckyNumber84Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long x = in.nextLong();
        int total = 0;
        while (x > 0) {
            int y = (int) (x % 10);
            if (y == 4 || y == 7)
                total++;
            x /= 10;
        }

        if (total == 0) {
            System.out.println("NO");
            return;
        }

        while (total > 0) {
            int y = total % 10;
            if (y != 4 && y != 7) {
                System.out.println("NO");
                return;
            }
            total /= 10;
        }
        System.out.println("YES");
    }
}
