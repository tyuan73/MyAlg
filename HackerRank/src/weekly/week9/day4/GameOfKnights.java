package weekly.week9.day4;

/**
 * Created by yuantian on 8/28/14.
 */

/*
You are given an infinite chess board containing N knights. The bottom left corner of the board is labelled as (0,0). Two geeks decide to play the following game on the board :

In one turn, a player may move at-most K knights and each knight can be moved at-most X times in that turn. A knight can be moved from (i,j) to either (i−1,j−2) or to (i−2,j−1). Note that the knights are not allowed to go outside the board. The one who is unable to make a move loses the game.

Your task is to determine the winner of the game if both the players play optimally.

Input Format
The first line contains a single integer T, denoting the number of test cases.
Each test case consists of a line containing three positive integers N, K and X, as described in the problem statement. Next N lines contain two integers x and y denoting the co-ordinates of the corresponding knight as (x,y) before the game begins.

Output Format
For every test case, output First player wins if the first player would win or Second player wins if the second player would win.

Constraints
1≤T≤100
1≤N≤20000
1≤K≤N
1≤X≤5
0≤x,y<300

Sample Input

1
1 1 1
1 2
Sample Output

First player wins
Explanation

In the given test case, there is only one knight and it can be moved only once. Hence the first player makes the first move leaving the second player with no move to make.
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class GameOfKnights {
    static void go() {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int x = in.nextInt();
            x = 2 * x + 1;

            int[][] g = new int[x + 3][303];
            for (int i = 0; i <= x; i++) {
                for (int j = i; j < 300; j++) {
                    g[i + 1][j + 2] = g[i][j] + 1;
                    g[i + 2][j + 1] = g[i][j] + 1;
                }
            }

            int c = 0;
            while (n-- > 0) {
                int p = in.nextInt();
                int q = in.nextInt();

                if (p > q) {
                    int temp = p;
                    p = q;
                    q = temp;
                }

                q -= p / x * x;
                p %= x;
                c += g[p][q];
            }
            if (c % (k + 1) == 0)
                out.println("Second player wins");
            else
                out.println("First player wins");
        }
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