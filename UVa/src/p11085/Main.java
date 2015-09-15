package p11085;

/**
 * Created by yuantian on 9/15/15.
 */

import java.util.*;
import java.io.*;

public class Main {
    static boolean[] d1 = null;
    static boolean[] d2 = null;
    static boolean[] r = null;
    static int[] input = null;
    static int[] cols = null;
    static int min = 100000;

    static void go() {
        try {
            int ct = 1;
            while (true) {
                input = in.nextIntArray(8);
                d1 = new boolean[20];
                d2 = new boolean[20];
                r = new boolean[8];
                cols = new int[8];
                min = 100000;

                check(0);

                out.printf("Case %d: %d\n", ct++, min);
            }

        } catch (Exception e) {

        }
    }

    static void check(int col) {
        if (col == 8) {
            cal();
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (r[i] || d1[i + col] || d2[i - col + 8]) continue;
            r[i] = true;
            d1[i + col] = true;
            d2[i - col + 8] = true;
            cols[col] = i + 1;
            check(col + 1);
            r[i] = false;
            d1[i + col] = false;
            d2[i - col + 8] = false;
        }
    }

    static void cal() {
        int diff = 0;
        for (int i = 0; i < 8; i++) {
            if (input[i] != cols[i])
                diff++;
        }
        min = Math.min(min, diff);
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