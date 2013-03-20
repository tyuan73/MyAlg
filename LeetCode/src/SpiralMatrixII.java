/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 11:23 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] m = new int[n][n];

        int l = 0, r = n - 1, u = 0, b = n - 1;
        int x = 1;
        while (x <= n * n) {
            for (int i = l; i <= r; i++)
                m[u][i] = x++;
            u++;
            for (int i = u; i <= b; i++)
                m[i][r] = x++;
            r--;
            for (int i = r; i >= l; i--)
                m[b][i] = x++;
            b--;
            for (int i = b; i >= u; i--)
                m[i][l] = x++;
            l++;
        }
        return m;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
