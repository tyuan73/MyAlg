package CodeSprintIndia2014;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 9/20/14
 * Time: 11:01 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

https://www.hackerrank.com/contests/csindia/challenges/cutting-paper

Yami is taking a Physics class in her school. She doesn't like Physics and gets bored very fast. As a hyperactive girl, she wants to use this time in a productive manner. So she takes a rectangular sheet of paper and a pair of scissors and decides to cut the paper. While cutting the paper, the following rules are followed

Paper is cut along a line that is parallel to one of the sides of the paper.
Paper is cut such that the resultant dimensions are always integers.
The process stops when all the pieces are squares. What is the minimum number of paper pieces cut by Yami such that all are squares?

Input Format
The first line of the input is number T, the number of test cases.
Each test case contains two space separated integers N and M, the dimensions of the sheet.

Constraints
1<=T<=100
1<=N,M<=100

Output Format
For each testcase, print in a newline the minimum number of squares that can be cut by Yami.

Sample Input

2
1 1
1 2
Sample Output

1
2
Explanation

For the first testcase, the minimum number of squares that can be cut is just 1 ( the original paper )
For the second testcase, the minimum number of squares that can be cut is 2 ( the paper is cut horizontally along the smaller side in the middle ).
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class CuttingPaper {
    static void go() {
        int t = in.nextInt();
        int[][] dp = new int[101][101];

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                    continue;
                }

                int min = Integer.MAX_VALUE;
                for (int k = j - 1; k >= 1; k--) {
                    min = Math.min(min, dp[i][k] + dp[i][j - k]);
                }
                for (int k = i - 1; k >= 1; k--) {
                    min = Math.min(min, dp[k][j] + dp[i - k][j]);
                }
                dp[i][j] = min;
            }
        }

        /**
         the following is the same as above, but with some minor optimizations.
         it calculated half of dp table only.
         **/
        /*
        for (int i = 1; i <= 100; i++) {
            dp[i][i] = 1;
            for (int j = 1; j <= 100; j++) {
                if (j <= i) {
                    dp[i][j] = dp[j][i];
                    continue;
                }
                if (i == 1) {
                    dp[i][j] = j;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for (int k = j - 1; k >= j / 2; k--) {
                    min = Math.min(min, dp[i][k] + dp[i][j - k]);
                }
                for (int k = i - 1; k >= i / 2; k--) {
                    min = Math.min(min, dp[k][j] + dp[i - k][j]);
                }
                dp[i][j] = min;
            }
        }
        */

        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();

            out.println(dp[n][m]);
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