package weekly.week6.day4;

/**
 * Created by yuantian on 7/3/14.
 */

/*
In Chile, land are partitioned into a one large grid, where each element represents a land of size 1x1.

Shaka is a newcomer in Chile and is trying to start his own business. He is planning to build a store. He has his own ideas for the "perfect store" which can be represented by a HxW grid. Element at position (i, j) represents height of land at index (i, j) in the grid.

Shaka has purchased a land area which can be represented RxC grid (H <= R, W <= C). Shaka is interested in finding best HxW sub-grid in the acquired land. In order to compare the possible sub-grids, Shaka will be using the sum of squared difference between each cell of his "perfect store" and it's corresponding cell in the subgrid. Amongst all possible sub-grids, he will choose the one with smallest such sum.

Note

The grids are 1-indexed and rows increase from top to bottom and columns increase from left to right.
If x is the height of a cell in the "perfect store" and y is the height of the corresponding cell in a sub-grid of the acquired land, then the squared difference is defined as (x-y)2
Input Format
The first line of the input consists of two integers, R C, separated by single space.
Then R lines follow, each one containing C space separated integers, which describe the height of each land spot of the purchased land.
The next line contains two integers, H W, separated by a single space, followed by H lines with W space separated integers, which describes the "perfect store".

Output Format

In the first line, output the smallest possible sum (as defined above) Shaka can find on exploring all the sub-grids (of size HxW) in the purchased land.
In second line, output two space separated integers, i j, which represents the index of top left corner of sub-grid (on the acquired land) with the minimal such sum. If there are multiple sub-grids with minimal sum, output the one with the smaller row index. If there are still multiple sub-grids with minimal sum, output the one with smaller column index.

Constraints

1 <= R, C <= 500
1 <= H <= R
1 <= W <= C
No height will have an absolute value greater than 20.

Sample Input:

3 3
19 19 -12
5 8 -14
-12 -11 9
2 2
-18 -12
-10 -7
Sample Output:

937
2 2
Explanation

The result is computed as follows: (8 - (-18)) 2 + (-14 - (-12)) 2 + (-11 - (-10)) 2 + (9 - (-7)) 2 = 937
*/

import java.util.*;
import java.io.*;

public class BestSpot {
    static void go() {
        long start = System.currentTimeMillis();
        int r = in.nextInt();
        int c = in.nextInt();
        int[][] land = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++)
                //land[i][j] = in.nextInt();
                land[i][j] = 20;
        }
        int h = in.nextInt();
        int w = in.nextInt();
        int[][] store = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++)
                //store[i][j] = in.nextInt();
                store[i][j] = -20;
        }

        int min = Integer.MAX_VALUE;
        int oi = 0, oj = 0;
        for (int i = 0; i <= r - h; i++) {
            for (int j = 0; j <= c - w; j++) {
                int sum = 0;
                for (int p = i; p < i + h; p++) {
                    for (int q = j; q < j + w; q++) {
                        int x = land[p][q] - store[p - i][q - j];
                        sum += x * x;
                    }
                }
                if (sum < min) {
                    min = sum;
                    oi = i + 1;
                    oj = j + 1;
                }
            }
        }
        out.println(min);
        out.println(oi + " " + oj);
        out.println(System.currentTimeMillis() - start);
    }

    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);

        go();

        out.close();
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            return (int) nextLong();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder sb = new StringBuilder(1024);
            do {
                sb.append((char) c);
                c = read();
            } while (!isSpaceChar(c));
            return sb.toString();
        }

        public static boolean isSpaceChar(int c) {
            switch (c) {
                case -1:
                case ' ':
                case '\n':
                case '\r':
                case '\t':
                    return true;
                default:
                    return false;
            }
        }
    }
}