package problemset518;

/**
 * Created by yuantian on 2/27/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class IlyaAndEscalator518D {
    static void go() {
        int n = in.nextInt();
        double p = Double.parseDouble(in.nextString());
        int t = in.nextInt();

        double q = 1 - p;
        int m = Math.min(n, t);
        double[][] dp = new double[t + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= t; i++) {
            dp[i][0] = dp[i-1][0] * q;
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i-1][j-1] * p + dp[i-1][j] * q;
            }
        }

        double ans = 0;
        for (int i = 1; i < m; i++) {
            ans += dp[t][i] * i;
        }

        // cal the answer where the last one is on elevator
        double last = 0;
        for (int i = t - 1; i >= m-1; i--) {
            last += dp[i][m - 1];
        }
        ans += last * p * m;
        out.println(ans);
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