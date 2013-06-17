/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/8/13
 * Time: 2:06 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class EugenyAndArray182Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int c1 = 0, c2 = 0;
        for (int i = 1; i <= n; i++) {
            if (in.nextInt() == -1)
                c1++;
            else
                c2++;
        }

        while (m-- > 0) {
            int x = in.nextInt();
            int y = in.nextInt();
            int c = y - x + 1;
            if (c % 2 == 1)
                System.out.println(0);
            else {
                c = c / 2;
                if (c1 >= c && c2 >= c)
                    System.out.println(1);
                else
                    System.out.println(0);
            }
        }
    }
}
