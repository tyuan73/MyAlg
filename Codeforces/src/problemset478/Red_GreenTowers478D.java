package problemset478;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 11/12/14
 * Time: 9:57 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Red_GreenTowers478D {
    static int P = (int) 1e9 + 7;

    static void go() {
        int r = in.nextInt();
        int g = in.nextInt();

        if (r > g) {
            int temp = r;
            r = g;
            g = temp;
        }

        int[] dp = new int[r + 1];
        dp[0] = 1;
        int maxH = 1;
        int upper = 0;
        while (maxH * (maxH + 1) / 2 <= r + g) {
            upper = Math.min(r, upper + maxH);
            for (int i = upper; i >= maxH; i--) {
                dp[i] += dp[i - maxH];
                if (dp[i] > P)
                    dp[i] -= P;
            }
            maxH++;
        }
        maxH--;
        int total = 0;
        //int sum = maxH * (maxH + 1) / 2;
        for (int i = Math.max(maxH * (maxH + 1) / 2 - g, 0); i <= r; i++) {
            total += dp[i];
            if (total > P)
                total -= P;
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