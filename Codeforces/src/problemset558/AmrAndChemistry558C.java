package problemset558;

/**
 * Created by yuantian on 8/19/15.
 */

import java.util.*;
import java.io.*;

public class AmrAndChemistry558C {
    static void go() {
        int n = in.nextInt();
        int[] a = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            max = Math.max(max, a[i]);
        }

        int pre = a[0];
        for (int i = 1; i < n; i++) {
            pre = getPre(pre, a[i]);
        }

        int col = 0;
        while ((pre << col) <= max) {
            col++;
        }

        int[] count = new int[col];
        for (int x : a) {
            int c = 0;
            for (int m = 0; m < col; m++) {
                int c1 = getSteps(x, pre << m);
                if (c1 == -1) {
                    c1 = c + 1;
                }

                count[m] += c1;
                c = c1;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int x : count)
            min = Math.min(min, x);

        out.println(min);
    }

    static int getSteps(int x, int t) {
        int ret = 0;
        while (x > t) {
            x >>= 1;
            ret++;
        }
        return x == t ? ret : -1;
    }

    static int getPre(int pre, int x) {
        while (pre != x) {
            if (pre > x)
                pre >>= 1;
            else
                x >>= 1;
        }
        return pre;
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