/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/22/13
 * Time: 11:03 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class WeirdGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        String a1 = in.next();
        String a2 = in.next();

        int[] count = new int[3];
        for (int i = 0; i < 2 * n; i++) {
            char c1 = a1.charAt(i);
            char c2 = a2.charAt(i);
            if (c1 == '1' && c2 == '1')
                count[0]++;
            else if (c1 == '1' && c2 == '0')
                count[1]++;
            else if (c1 == '0' && c2 == '1')
                count[2]++;
        }

        int t1 = 0, t2 = 0;
        int min = Math.min(count[1], count[2]);
        t1 = (count[0] + 1) / 2 + min;
        t2 = count[0] / 2 + min;
        count[1] -= min;
        count[2] -= min;
        if (t1 > t2) {
            if (count[1] == 0 && (count[2] == 2 || count[1] == 1))
                System.out.println("Draw");
            else if (count[2] > 2)
                System.out.println("Second");
            else
                System.out.println("First");
        } else {
            if (count[1] == 0 && count[2] <= 1)
                System.out.println("Draw");
            else if (count[1] > 1)
                System.out.println("First");
            else
                System.out.println("Second");
        }
    }
}
