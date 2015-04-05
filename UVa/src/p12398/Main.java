package p12398;/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/4/15
 * Time: 10:57 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

class Main {
    final static int[][] map = {{1, 1}, {1, 2}, {1, 3}, {2, 1}, {2, 2}, {2, 3}, {3, 1}, {3, 2}, {3, 3}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int cs = 1;
        while (in.hasNextLine()) {
            String str = in.nextLine();
            int[][] ans = new int[5][5];
            for (int i = str.length() - 1; i >= 0; i--) {
                char ch = str.charAt(i);
                int[] x = map[ch - 'a'];
                ans[x[0]][x[1]]++;
                ans[x[0] - 1][x[1]]++;
                ans[x[0] + 1][x[1]]++;
                ans[x[0]][x[1] - 1]++;
                ans[x[0]][x[1] + 1]++;
            }

            System.out.println("Case #" + cs + ":");
            for (int i = 1; i <= 3; i++) {
                System.out.println((ans[i][1] % 10) + " " + (ans[i][2] % 10) + " " + (ans[i][3] % 10));
            }
            cs++;
        }
    }
}
