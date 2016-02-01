/**
 * Created by yuantian on 2/1/16.
 */
public class OJ36ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[][] grids = new int[3][3];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch == '.') continue;
                int x = ch - '0';
                if (x <= 0 || x > 9)
                    return false;
                int bit = 1 << x;
                // check row
                if ((rows[i] & bit) > 0)
                    return false;
                // check col
                if ((cols[j] & bit) > 0)
                    return false;
                // check grid
                if ((grids[i / 3][j / 3] & bit) > 0)
                    return false;

                rows[i] |= bit;
                cols[j] |= bit;
                grids[i / 3][j / 3] |= bit;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        char[][] input = {
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };

        OJ36ValidSudoku test = new OJ36ValidSudoku();
        System.out.println(test.isValidSudoku(input));
    }
}
