package hack101.oct2014;

/**
 * Created by yuantian on 11/6/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class CandlesCounting {
    static long P = 1000000007;

    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] c = new int[n][2];
        for (int i = 0; i < n; i++) {
            c[i][0] = in.nextInt();
            c[i][1] = in.nextInt();
        }

        long ans = 0;
        long[] fwt = new long[50001];
        for (int mask = (1 << k) - 1; mask > 0; mask--) {
            Arrays.fill(fwt, 0);
            long count = 0;
            for (int i = 0; i < n; i++) {
                if ((1 << (c[i][1] - 1) & mask) > 0) {
                    long x = 1 + fwtAdd(fwt, c[i][0] - 1);
                    fwtUpdate(fwt, c[i][0], x);
                    count += x;
                    if (count > P)
                        count -= P;
                }
            }

            if ((countOne(mask) & 1) == (k & 1))
                ans += count;
            else
                ans += P - count;

            if (ans > P)
                ans -= P;
        }
        out.println(ans);
    }

    static private int countOne(int mask) {
        int total = 0;
        while (mask > 0) {
            total++;
            mask &= (mask - 1);
        }
        return total;
    }

    static private void fwtUpdate(long[] fwt, int i, long val) {
        for (; i < fwt.length; i += i & -i) {
            fwt[i] += val;
            if (fwt[i] > P)
                fwt[i] -= P;
        }
    }

    static private long fwtAdd(long[] fwt, int i) {
        long ret = 0;
        for (; i > 0; i -= i & -i) {
            ret += fwt[i];
            if (ret > P)
                ret -= P;
        }
        return ret;
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