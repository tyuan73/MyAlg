/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/13/17
 * Time: 5:46 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class SudokuSolver37 {
    public void solveSudoku(char[][] board) {
        List<int[]> empty = new ArrayList<>();
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] grps = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    empty.add(new int[]{i, j});
                else {
                    int val = board[i][j] - '1';
                    rows[i][val] = true;
                    cols[j][val] = true;
                    grps[i / 3 * 3 + j / 3][val] = true;
                }
            }
        }

        sudoku(board, empty, 0, rows, cols, grps);
    }

    private boolean sudoku(char[][] board, List<int[]> empty, int idx, boolean[][] rows, boolean[][] cols, boolean[][] grps) {
        if (idx == empty.size()) return true;
        int[] loc = empty.get(idx);
        int x = loc[0], y = loc[1];
        for (int i = 0; i < 9; i++) {
            if (rows[x][i] || cols[y][i] || grps[x / 3 * 3 + y / 3][i]) continue;
            board[x][y] = (char) (i + '1');
            rows[x][i] = true;
            cols[y][i] = true;
            grps[x / 3 * 3 + y / 3][i] = true;
            if (sudoku(board, empty, idx + 1, rows, cols, grps))
                return true;
            rows[x][i] = false;
            cols[y][i] = false;
            grps[x / 3 * 3 + y / 3][i] = false;
        }
        return false;
    }
}
