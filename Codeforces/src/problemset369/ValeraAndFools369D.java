package problemset369;

/**
 * Created by yuantian on 10/22/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class ValeraAndFools369D {
    static int n = 0;
    static int k = 0;
    static int[][] dp = null;
    static boolean[][] s = null;
    static int[] p = null;

    static void go() {
        n = in.nextInt();
        k = in.nextInt();
        p = in.nextIntArray(n);
        dp = new int[n + 3][n + 3];
        s = new boolean[n + 3][2];
        for (int i = n - 1; i >= 0; i--) {
            s[i][0] = s[i + 1][0];
            s[i][1] = s[i + 1][1];
            if (p[i] == 100)
                s[i][0] = true;
            else if (p[i] > 0)
                s[i][1] = true;
        }

        dfs(0, 0, 1);
        out.println(dp[0][1]);
    }

    static int dfs(int step, int a1, int a2) {
        if (step > k || a1 >= n || a2 >= n)
            return 0;
        if (dp[a1][a2] != 0)
            return dp[a1][a2];

        dp[a1][a2] = 1;
        if (p[a1] == 100) {
            if (s[a2][0]) {
                dp[a1][a2] += dfs(step + 1, a2 + 1, a2 + 2);
            } else if (s[a2][1]) {
                dp[a1][a2] += dfs(step + 1, a1, a2 + 1) + dfs(step + 1, a2 + 1, a2 + 2);
            } else {
                dp[a1][a2] += dfs(step + 1, a1, a2 + 1);
            }
        } else if (p[a1] > 0) {
            if (s[a2][0]) {
                dp[a1][a2] += dfs(step + 1, a2, a2 + 1) + dfs(step + 1, a2 + 1, a2 + 2);
            } else if (s[a2][1]) {
                dp[a1][a2] += dfs(step + 1, a1, a2 + 1) + dfs(step + 1, a2 + 1, a2 + 2) + dfs(step + 1, a2, a2 + 1);
            } else {
                dp[a1][a2] += dfs(step + 1, a1, a2 + 1);
            }
        } else {
            dp[a1][a2] += dfs(step + 1, a2, a2 + 1);
        }

        return dp[a1][a2];
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