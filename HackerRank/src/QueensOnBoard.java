/**
 * Created by yuantian on 5/16/14.
 */

/*
You have an N * M chessboard on which some squares are blocked out. In how many ways can you place one or more queens on the board, such that, no two queens attack each other? Two queens attack each other, if one can reach the other by moving horizontally, vertically, or diagonally without passing over any blocked square. At most one queen can be placed on a square. A queen cannot be placed on a blocked square.

Input Format
The first line contains the number of test cases T. T test cases follow. Each test case contains integers N and M on the first line. The following N lines contain M characters each, and represent a board. A ‘#’ represents a blocked square and a ‘.’ represents an unblocked square.

Constraints
1 <= T <= 100
1 <= N <= 50
1 <= M <= 5

Output Format
Output T lines containing the required answer for each test case. As the answers can be really large, output them modulo 109+7.

Sample Input:

4
3 3
...
...
...
3 3
.#.
.#.
...
2 4
.#..
....
1 1
#
Sample Output:

17
18
14
0
 */

import java.util.*;

public class QueensOnBoard {
    static long total = 0;
    static int[][] bd = null;
    static final long P = 1000000007;
    static int n, m;

    static public void main(String[] args) {
        Scanner in  = new Scanner(System.in);

        int t = in.nextInt();
        while(t-- > 0) {
            n = in.nextInt();
            m = in.nextInt();

            in.nextLine();
            bd = new int[n][m];
            for (int i = 0; i < n; i++) {
                String line = in.nextLine();
                for(int j = 0; j < m; j++) {
                    char c = line.charAt(j);
                    if (c == '#')
                        bd[i][j] = -1;
                    else
                        bd[i][j] = 0;
                }
            }

            total = 0;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    count(i, j);
            System.out.println(total);
        }
    }

    static void count(int row, int col) {
        //System.out.println("row = " + row + " col = " + col);
        if (bd[row][col] != 0)
            return;

        total = (total + 1) % P;

        bd[row][col] = 1;
        change(row, col, 1, 0, 1);
        change(row, col, 0, 1, 1);
        change(row, col, -1, 0, 1);
        change(row, col, 0, -1, 1);
        change(row, col, 1, 1, 1);
        change(row, col, 1, -1, 1);
        change(row, col, -1, 1, 1);
        change(row, col, -1, -1, 1);

        for (int i = row * m + col + 1; i < n * m; i++) {
            count(i/m, i % m);
        }
        change(row, col, 1, 0, -1);
        change(row, col, 0, 1, -1);
        change(row, col, -1, 0, -1);
        change(row, col, 0, -1, -1);
        change(row, col, 1, 1, -1);
        change(row, col, 1, -1, -1);
        change(row, col, -1, 1, -1);
        change(row, col, -1, -1, -1);
        bd[row][col] = 0;
    }

    static void change(int row, int col, int rd, int cd, int diff) {
        row += rd; col += cd;
        while(row >= 0 && row < n && col >= 0 && col < m) {
            if (bd[row][col] < 0)
                break;

            bd[row][col] += diff;
            row += rd; col += cd;
        }
    }
}
