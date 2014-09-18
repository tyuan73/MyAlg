package weekly.week10.day3;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 9/17/14
 * Time: 11:20 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*
https://www.hackerrank.com/contests/w10/challenges/towers


One day John had to take care of his little nephew Jim. He was very busy, so he gave Jim a big bag full of building bricks. The bricks are of various heights: at most 15 different heights. For each height, the bag contains infinitely many bricks.

Now, Jim’s task is to build every possible tower of height N from the given bricks. Bricks are stacked vertically only and stand in an upright position. Two towers are different if their brick height sequences are different.
Jim is good at building towers and can build one tower in exactly 2 minutes, regardless of its height. John wants to know the time Jim requires to build all possible towers.

Input Format
There are 3 lines of input. First line contains an integer, N, the height of tower to be built. Second line contains another integer, K, which represents different heights of bricks available in the bag. Third line contains K distinct integers representing the available heights.

Output Format
In one line print the number of minutes Jim requires to build all possible towers. As this number can be very large, print the number modulo (109+7).

Constraints
1≤N≤1018
1≤K≤15
All heights will be unique.
Height of each brick will lie in range [1, 15].

Sample Input#00

10
1
1
Sample Output#00

2
Explanation#00: There is exactly one type of brick, so there is exactly one tower of height 10. Building any tower takes 2 minutes, so the answer is 2.

Sample Input#01

5
2
2 3
Sample Output#01

4
Explanation #01: There are two types of bricks. There are two different towers of height 5 which can be build from these types of bricks: [2,3] and [3,2]. They are different, because the sequence of bricks in them is different. Building any tower takes 2 minutes, so the answer is 2×2=4.

Sample Input#03

19
2
4 5
Sample Output#03

8
Explanation #03: There are two types of bricks. Jim can build 4 different towers of height 19 from these bricks: [5,5,5,4], [5,5,4,5], [5,4,5,5] and [4,5,5,5]. Building any tower takes 2 minutes, so the answer is 2×4=8.
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Towers {
    static long P = 1000000007;

    static void go() {
        long n = in.nextLong();
        int k = in.nextInt();
        int[] b = new int[k + 1];
        b[k] = 20;
        for (int i = 0; i < k; i++)
            b[i] = in.nextInt();
        Arrays.sort(b);

        int[] first = new int[16];
        first[0] = 1;
        for (int i = 1; i < 16; i++) {
            int j = 0;
            while (b[j] <= i) {
                first[i] += first[i - b[j]];
                j++;
            }
        }

        if (n <= 15) {
            out.println(first[(int) n] * 2);
            return;
        }

        long[][] matrix = new long[15][15];
        for (int i = 1; i < 15; i++)
            matrix[i][i - 1] = 1;

        for (int i = 0; i < k; i++)
            matrix[15 - b[i]][14] = 1;

        //printMatrix(matrix);

        matrix = power(matrix, n - 15);

        //printMatrix(matrix);

        long ans = 0;
        for (int i = 0; i < 15; i++) {
            ans += first[i + 1] * matrix[i][14];
            ans %= P;
        }
        out.println((ans * 2) % P);
    }

    static long[][] power(long[][] m, long p) {
        long[][] res = new long[15][15];
        for (int i = 0; i < 15; i++)
            res[i][i] = 1;

        while (p > 0) {
            if ((p & 1) == 1)
                res = multiply(res, m);
            p >>= 1;
            m = multiply(m, m);
        }
        return res;
    }

    static long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                long x = 0;
                for (int k = 0; k < 15; k++) {
                    x += a[i][k] * b[k][j];
                    x %= P;
                }
                c[i][j] = x;
            }
        }
        return c;
    }

    static void printMatrix(long[][] m) {
        for (long[] r : m) {
            for (long x : r)
                out.print(" " + x);
            out.println();
        }
        out.println();
        out.println();
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