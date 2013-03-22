/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/21/13
 * Time: 3:10 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

public class Sudoku {

    public static void main(String[] args) {
        Sudoku sd = new Sudoku();
        //["..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."]
        char[][] board = {
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
        };

        sd.solveSudoku(board);
        for (char[] row : board) {
            for (char ch : row)
                System.out.print(" " + ch);
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {
        helper(board);
    }

    boolean helper(char[][] board) {
        int[] cell = getEmpty(board);
        if (cell[0] == -1)
            return true;

        for (char ch = '1'; ch <= '9'; ch++) {
            if (isValid(ch, cell[0], cell[1], board)) {
                board[cell[0]][cell[1]] = ch;
                if (helper(board))
                    return true;
                board[cell[0]][cell[1]] = '.';
            }
        }

        return false;
    }

    boolean isValid(char ch, int i, int j, char[][] board) {
        for (int k = 0; k < 9; k++) {
            if (ch == board[i][k] || ch == board[k][j])
                return false;
        }

        int x = i / 3 * 3, y = j / 3 * 3;
        for (int n = x; n < x + 3; n++)
            for (int m = y; m < y + 3; m++)
                if (board[n][m] == ch)
                    return false;

        return true;
    }

    int[] getEmpty(char[][] board) {
        int[] ret = {-1, -1};
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    ret[0] = i;
                    ret[1] = j;
                    return ret;
                }
            }
        return ret;
    }
}
