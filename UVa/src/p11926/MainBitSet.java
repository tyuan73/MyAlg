package p11926;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/8/15
 * Time: 11:20 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.InputMismatchException;

public class MainBitSet {
    static void go() {
        int n, m;
        BitSet bs = new BitSet(1000001);
        while (true) {
            n = in.nextInt();
            m = in.nextInt();
            if (n == 0 && m == 0)
                break;

            bs.clear();
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                if (ok) {
                    if (y > 1000000) y = 1000000;
                    if (bs.get(x, y).cardinality() > 0) {
                        ok = false;
                    } else {
                        bs.set(x, y);
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int inv = in.nextInt();
                while (ok && x <= 1000000) {
                    if (y > 1000000) y = 1000000;
                    if (bs.get(x, y).cardinality() > 0) {
                        ok = false;
                    } else {
                        bs.set(x, y);
                        x += inv;
                        y += inv;
                    }
                }
            }

            out.println(ok ? "NO CONFLICT" : "CONFLICT");
        }
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