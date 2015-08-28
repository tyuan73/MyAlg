package problemset570;

/**
 * Created by yuantian on 8/27/15.
 */

import java.util.*;
import java.io.*;

public class PigAndPalindromes570E {
    final static long P = 1000000007L;

    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] forest = new char[n + 1][];
        for (int i = 0; i < n; i++) {
            forest[i] = in.nextCharArray(m);
        }
        forest[n] = new char[m];
        int[][][] dp = new int[2][n + 2][n + 2];
        int[][] init = dp[0];
        int dist = (n + m - 2) / 2;
        int diff = 0;
        if (((n + m) & 1) == 0) {
            diff = 2;
            for (int i = 0; i < n; i++)
                if (i + m > dist && i <= dist)
                    init[i][i] = 1;
        } else {
            diff = 3;
            for (int i = 0; i < n; i++) {
                int col1 = dist - i;
                int col2 = col1 + 1;
                if (col1 >= 0 && col2 < m && forest[i][col1] == forest[i][col2]) {
                    init[i][i] = 1;
                }
                if (col1 >= 0 && col1 < m && forest[i][col1] == forest[i + 1][col1]) {
                    init[i][i + 1] = 1;
                }
            }
        }
        dist--;

        int index = 1;
        for (; dist >= 0; index ^= 1, dist--, diff += 2) {
            int[][] cur = dp[index];
            int[][] pre = dp[1 - index];
            for (int i = 0; i < n; i++)
                Arrays.fill(cur[i], 0);
            for (int i = 0; i < n; i++) {
                int col1 = dist - i;
                if (col1 < 0) break;
                if (col1 >= m) continue;
                for (int j = i, col2 = m + n - dist - 2 - j; j <= Math.min(n - 1, i + diff); j++, col2--) {
                    if (col2 >= m) continue;
                    if (col2 < 0) break;

                    if (forest[i][col1] != forest[j][col2]) continue;

                    long total = 0;

                    if (j > 0) {
                        total += pre[i][j - 1];
                        total += pre[i + 1][j - 1];
                        if (total >= P)
                            total -= P;
                    }
                    total += pre[i][j];
                    if (total >= P)
                        total -= P;
                    total += pre[i + 1][j];
                    if (total >= P)
                        total -= P;

                    cur[i][j] = (int) total;
                }
            }
        }
        out.println(dp[1 - index][0][n - 1]);
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

        public String nextLine() {
            StringBuilder sb = new StringBuilder(1024);
            int c = read();
            while (!(c == '\n' || c == '\r' || c == -1)) {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
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

        public char[] nextCharArray(int n) {
            char[] ca = new char[n];
            for (int i = 0; i < n; i++) {
                int c = read();
                while (isSpaceChar(c))
                    c = read();
                ca[i] = (char) c;
            }
            return ca;
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