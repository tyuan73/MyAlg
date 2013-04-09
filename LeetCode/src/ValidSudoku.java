/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/25/13
 * Time: 5:09 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9];
        int[] col = new int[9];
        int[][] grid = new int[3][3];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int flag = 1 << (num - 1);
                    if ((row[i] & flag) > 0)
                        return false;
                    row[i] |= flag;
                    if ((col[j] & flag) > 0)
                        return false;
                    col[j] |= flag;
                    int c1 = i / 3, c2 = j / 3;
                    if ((grid[c1][c2] & flag) > 0)
                        return false;
                    grid[c1][c2] |= flag;
                }
            }
        }

        return true;
    }
}
