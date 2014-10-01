package hack101.Sept2014;

/**
 * Created by yuantian on 9/29/14.
 */

/*
https://www.hackerrank.com/contests/101sep14/challenges/the-white-lotus-and-caterpillar-game

As usual Gary and Flo are sitting at their favourite burger restaurant called Jim's Burgers. They want to treat themselves with delicious burger after an interesting day with lots of competitive programming. So they have ordered their burgers and are waiting for them. But with nothing to do, they get bored and decide to play a game.

The game is played on a sheet of paper with n rows and m columns and goes as follows:

Flo places his white lotus tile somewhere at the top row and Gary places a caterpillar tile somewhere on the bottom row. Flo begins the game and their turns alternate. Flo can move his tile to any of the 8 adjacent cells, while Gary's caterpillar tile can only move left or right, or stay at the same cell. Of course, they cannot step outside of the grid. Flo's goal is to catch Gary as fast as possible, that is, with the minimum number of moves, while Gary (with the caterpillar tile) has to survive for as long as possible.

Now they are wondering: If they place their tiles in the corresponding rows and some random columns, what is the expected number of moves Flo has to make to win the game (assuming they will play optimally)?

Can you help them answer this question?

Constraints

2≤n,m≤5000
Input Format

You will be given two space separated integers n and m, denoting the number of rows and the number of columns on the board respectively.

Output Format

Output the answer in one line.

Note: The answer will be considered valid if it differs from the correct answer by at most 10−6.

Sample input

2 3
Sample output

1.2222222
Consider the pair (x,y) as the starting column of the rabbit and the starting column of the caterpillar respectively.

For n=2 and m=3 we get the following scenario.

(1,1),(1,2),(2,1),(2,2),(2,3),(3,2),(3,3) will lead to 1 move. The remaining pairs (1,3) and (3,1) will lead to 2 moves. So the expected value is 1+1+1+1+1+1+1+2+29=1.222..

*/

import java.util.*;
import java.io.*;

public class WhiteLotusAndCaterpillarGame {
    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        long total = 0;
        for (int i = 1; i <= m; i++) {
            total += (Math.min(m, i + 1) - Math.max(1, i - 1) + 1) * (n - 1);
            total += Math.max(0, i - 2) * Math.max(n - 1, i - 1);
            total += Math.max(0, m - i - 1) * Math.max(n - 1, m - i);
        }
        out.println(total / (double) (m * m));
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