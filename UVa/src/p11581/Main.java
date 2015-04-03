package p11581;

/**
 * Created by yuantian on 4/3/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {

        int[] dp = new int[1 << 9];
        Arrays.fill(dp, -2);
        for (int i = 0; i < 1 << 9; i++) {
            if (dp[i] == -2) {
                cal(dp, i);
            }
        }

        int t = in.nextInt();
        while (t-- > 0) {
            int x = Integer.parseInt(in.nextString(), 2);
            x <<= 3;
            x |= Integer.parseInt(in.nextString(), 2);
            x <<= 3;
            x |= Integer.parseInt(in.nextString(), 2);
            out.println(dp[x]);
        }
    }

    static int cal(int[] dp, int n) {
        if (dp[n] != -2)
            return dp[n];

        int next = getNext(n);
        if (next == n) {
            dp[n] = -1;
        } else {
            dp[n] = cal(dp, next) + 1;
        }
        return dp[n];
    }

    static int getNext(int x) {
        int[][] grid = new int[5][5];
        int bit = 1 << 8;
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if ((x & bit) > 0) {
                    grid[i][j] = 1;
                }
                bit >>= 1;
            }
        }

        int[][] g = new int[5][5];
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (((grid[i - 1][j] + grid[i + 1][j] + grid[i][j - 1] + grid[i][j + 1]) & 1) > 0)
                    g[i][j] = 1;
            }
        }

        int x1 = 0;
        bit = 1 << 8;
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (g[i][j] == 1) {
                    x1 |= bit;
                }
                bit >>= 1;
            }
        }
        return x1;
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