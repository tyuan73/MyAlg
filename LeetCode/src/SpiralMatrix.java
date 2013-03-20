/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 11:23 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class SpiralMatrix {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0)
            return ret;

        int n = matrix.length;
        int m = matrix[0].length;
        int u = 0, b = n - 1, l = 0, r = m - 1;
        while (u <= b && l <= r) {
            for (int i = l; i <= r && u <= b; i++)    // very important, don't forget to check u <= b
                ret.add(matrix[u][i]);
            u++;
            for (int i = u; i <= b && l <= r; i++)
                ret.add(matrix[i][r]);
            r--;
            for (int i = r; i >= l && u <= b; i--)
                ret.add(matrix[b][i]);
            b--;
            for (int i = b; i >= u && l <= r; i--)
                ret.add(matrix[i][l]);
            l++;
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

}
