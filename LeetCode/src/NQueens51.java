/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 2/6/17
 * Time: 10:58 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class NQueens51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        queen(0, new int[n], n, new boolean[n], new boolean[2 * n], new boolean[2 * n], ans);
        return ans;
    }

    void queen(int r, int[] rows, int n, boolean[] cols, boolean[] d1, boolean[] d2, List<List<String>> ans) {
        if (r == n) {
            List<String> row = new ArrayList<>();
            for (int x : rows) {
                char[] line = new char[n];
                Arrays.fill(line, '.');
                line[x] = 'Q';
                row.add(new String(line));
            }
            ans.add(row);
            return;
        }

        for (int c = 0; c < n; c++) {
            int dia1 = r + c, dia2 = r - c + n;
            if (!cols[c] && !d1[dia1] && !d2[dia2]) {
                rows[r] = c;
                cols[c] = true;
                d1[dia1] = true;
                d2[dia2] = true;
                queen(r + 1, rows, n, cols, d1, d2, ans);
                cols[c] = false;
                d1[dia1] = false;
                d2[dia2] = false;
            }
        }
    }

    // for test
    static public void main(String[] args) {
        NQueens51 test = new NQueens51();
        test.solveNQueens(14);
    }
}
