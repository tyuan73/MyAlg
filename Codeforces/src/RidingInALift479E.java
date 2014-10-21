/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 10/20/14
 * Time: 10:54 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class RidingInALift479E {
    static long P = 1000000007;

    static void go() {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        int max = n;
        if (b > a) {
            a = b - a;
            max = b;
        } else {
            max -= b - 1;
            a -= b;
        }
        long[] dp = new long[max];
        Arrays.fill(dp, 1);
        dp[0] = dp[1] = 0;

        for (int j = 1; j <= k; j++) {
            long subtotal = j == 1 ? 1 : 0;
            for (int i = 2; i < max; i++) {
                if (i * 2 - 2 < max) {
                    subtotal += dp[i * 2 - 2];
                    if (i * 2 - 1 < max) {
                        subtotal += dp[i * 2 - 1];
                    }
                }
                subtotal %= P;
                dp[i] = subtotal - dp[i];
                if (dp[i] < 0)
                    dp[i] += P;
            }
        }
        out.println(dp[a]);
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