/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/21/13
 * Time: 11:19 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Stack;

public class SurroundedRegions {
    Stack<Integer> s = new Stack<Integer>();
    int n, m;
    char[][] b;

    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;

        n = board.length;
        m = board[0].length;
        b = board;

        for (int i = 0; i < n; i++) {
            if (b[i][0] == 'O')
                mark(i, 0);
            if (b[i][m - 1] == 'O')
                mark(i, m - 1);
        }

        for (int i = 0; i < m; i++) {
            if (b[0][i] == 'O')
                mark(0, i);
            if (b[n - 1][i] == 'O')
                mark(n - 1, i);
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (b[i][j] == 'O')
                    b[i][j] = 'X';
                else if (b[i][j] == '-')
                    b[i][j] = 'O';
            }
    }

    void mark(int i, int j) {
        s.push(i);
        s.push(j);
        while (!s.empty()) {
            j = s.pop();
            i = s.pop();
            b[i][j] = '-';
            if (i - 1 >= 0 && b[i - 1][j] == 'O') {
                s.push(i - 1);
                s.push(j);
            }
            if (i + 1 < n && b[i + 1][j] == 'O') {
                s.push(i + 1);
                s.push(j);
            }
            if (j - 1 >= 0 && b[i][j - 1] == 'O') {
                s.push(i);
                s.push(j - 1);
            }
            if (j + 1 < m && b[i][j + 1] == 'O') {
                s.push(i);
                s.push(j + 1);
            }
        }
    }
}
