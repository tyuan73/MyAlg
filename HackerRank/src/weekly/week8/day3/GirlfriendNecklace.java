package weekly.week8.day3;

/**
 * Created by yuantian on 8/19/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class GirlfriendNecklace {
    static long P = 1000000007;

    static void go() {
        int t = in.nextInt();

        while (t-- > 0) {
            long n = in.nextLong();
            if (n == 1) {
                out.println(2);
                continue;
            } else if (n == 2) {
                out.println(3);
                continue;
            }

            long[][] ans = {{0, 0, 1}, {1, 0, 0}, {0, 1, 1}};

            ans = power(ans, n - 3);
            out.println((2 * ans[0][2] + 3 * ans[1][2] + 4 * ans[2][2]) % P);
        }
    }

    static long[][] power(long[][] a, long p) {
        long[][] ret = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        while (p > 0) {
            if ((p & 1) == 1)
                ret = multiply(ret, a);
            p >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    static long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                long x = 0;
                for (int k = 0; k < 3; k++) {
                    x += a[i][k] * b[k][j];
                    x %= P;
                }
                c[i][j] = x;
            }
        return c;
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

/*
input:
2
1620
2362

output:
544702968
253647311
 */