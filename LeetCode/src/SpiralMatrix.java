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

    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return ans;
        int row = matrix.length, col = matrix[0].length;
        int x = 0, y = -1;
        while (true) {
            for (int i = 0; i < col; i++) {
                ans.add(matrix[x][++y]);
            }
            if (--row == 0) break;
            for (int i = 0; i < row; i++) {
                ans.add(matrix[++x][y]);
            }
            if (--col == 0) break;
            for (int i = 0; i < col; i++) {
                ans.add(matrix[x][--y]);
            }
            if (--row == 0) break;
            for (int i = 0; i < row; i++) {
                ans.add(matrix[--x][y]);
            }
            if (--col == 0) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

}
