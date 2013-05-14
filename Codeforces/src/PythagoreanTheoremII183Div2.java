/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/13/13
 * Time: 10:26 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class PythagoreanTheoremII183Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int total = 0;
        for (int c = 5; c <= n; c++) {
            int cc = c * c;
            int a = 1, b = (int) Math.sqrt(cc);
            while (a <= b) {
                int x = a * a + b * b;
                if (x == cc) {
                    a++;
                    b--;
                    total++;
                } else if (x < cc) {
                    a++;
                } else
                    b--;
            }
        }
        System.out.println(total);
    }
}
