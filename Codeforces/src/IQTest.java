/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/24/13
 * Time: 1:53 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class IQTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[][] square = new int[4][4];
        for (int i = 0; i < 4; i++) {
            String s = in.next();
            for (int j = 0; j < 4; j++) {
                if (s.charAt(j) == '#')
                    square[i][j] = 1;
                else
                    square[i][j] = 0;
            }
        }
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                int x = square[i][j] + square[i][j + 1] + square[i + 1][j] + square[i + 1][j + 1];
                if (x != 2) {
                    System.out.println("YES");
                    return;
                }
            }
        System.out.println("NO");
    }
}
