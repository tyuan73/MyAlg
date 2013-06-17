/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 6/8/13
 * Time: 11:49 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class SerejaAndPeriods315Div2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int b = in.nextInt();
        int d = in.nextInt();
        String s1 = in.next();
        String s2 = in.next();

        boolean[] count = new boolean[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a'] = true;
        }
        for (int i = 0; i < s2.length(); i++) {
            int index = s2.charAt(i) + 'a';
            if (count[index] == false) {
                System.out.println(0);
                return;
            }
        }

        int n = s1.length();
        int m = s2.length();
        boolean[][] map = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            char ch = s2.charAt(i);
            for (int j = 0; j < n; j++)
                if (ch == s2.charAt(j))
                    map[i][j] = true;
        }

        /**
         * no done. to be continued later.
         */
    }
}
