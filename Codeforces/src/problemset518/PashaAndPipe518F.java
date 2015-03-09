package problemset518;

/**
 * Created by yuantian on 3/9/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class PashaAndPipe518F {
    static void go() {
        /*
        int n = 2000;
        int m = 2000;
        char[][] field = new char[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m ;j++)
                field[i][j] = '.';
        */
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] field = new char[n][];
        for (int i = 0; i < n; i++) {
            field[i] = in.nextString().toCharArray();
        }

        int[][][] v = new int[n][m][2];
        int[][][] h = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (field[i][j] == '.') {
                    if (i == 0)
                        v[i][j][0] = 0;
                    else
                        v[i][j][0] = v[i - 1][j][0];
                    if (j == 0)
                        h[i][j][0] = 0;
                    else
                        h[i][j][0] = h[i][j - 1][0];
                } else {
                    v[i][j][0] = i + 1;
                    h[i][j][0] = j + 1;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (field[i][j] == '.') {
                    if (i == n - 1)
                        v[i][j][1] = n - 1;
                    else
                        v[i][j][1] = v[i + 1][j][1];
                    if (j == m - 1)
                        h[i][j][1] = m - 1;
                    else
                        h[i][j][1] = h[i][j + 1][1];
                } else {
                    v[i][j][1] = i - 1;
                    h[i][j][1] = j - 1;
                }
            }
        }

        long total = 0;
        // no turn
        for (int i = 1; i < n - 1; i++)
            if (h[i][m - 1][0] == 0)
                total++;
        for (int i = 1; i < m - 1; i++)
            if (v[n - 1][i][0] == 0)
                total++;

        int[][][] dp = new int[n][m][2];
        // one turn
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (field[i][j] != '.')
                    continue;
                int x = 0;
                if (v[i][j][0] == 0)
                    x++;
                if (v[i][j][1] == n - 1)
                    x++;
                int y = 0;
                if (h[i][j][0] == 0)
                    y++;
                if (h[i][j][1] == m - 1)
                    y++;
                total += x * y;
                dp[i][j][0] = dp[i][j - 1][0] + x;
                dp[i][j][1] = dp[i - 1][j][1] + y;
            }
        }

        // two turns
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (field[i][j] != '.')
                    continue;
                int x = 0;
                if (v[i][j][0] == 0) {
                    x++;
                    if (j != m - 2 && v[i][j + 1][1] == n - 1)
                        total++;
                }
                if (v[i][j][1] == n - 1) {
                    x++;
                    if (j != m - 2 && v[i][j + 1][0] == 0)
                        total++;
                }
                if (x > 0 && h[i][j][1] > j + 1) {
                    int index = Math.min(h[i][j][1], m - 2);
                    total += x * (dp[i][index][0] - dp[i][j + 1][0]);
                }
                int y = 0;
                if (h[i][j][0] == 0) {
                    y++;
                    if (i != n - 2 && h[i + 1][j][1] == m - 1)
                        total++;
                }
                if (h[i][j][1] == m - 1) {
                    y++;
                    if (i != n - 2 && h[i + 1][j][0] == 0)
                        total++;
                }
                if (y > 0 && v[i][j][1] > i + 1) {
                    int index = Math.min(v[i][j][1], n - 2);
                    total += y * (dp[index][j][1] - dp[i + 1][j][1]);
                }
            }
        }

        out.println(total);
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

        public int[] nextIntArray(int len) {
            int[] ret = new int[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextInt();
            return ret;
        }

        public long[] nextLongArray(int len) {
            long[] ret = new long[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextLong();
            return ret;
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

/*
5 4
####
....
..##
..##
####

5 5
.....
.....
.....
.....
.....

2000 2000
.....
......

output: 31888139940
 */