package p11959;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 6/1/15
 * Time: 11:15 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

public class Main {
    static int[][][] C = {
            {{5, 2}, {2, 3}, {3, 4}, {4, 5}},
            {{4, 3}, {5, 4}, {2, 5}, {3, 2}},
            {{1, 3}, {3, 0}, {0, 5}, {5, 1}},
            {{0, 2}, {2, 1}, {1, 4}, {4, 0}},
            {{5, 0}, {1, 5}, {3, 1}, {0, 3}},
            {{4, 1}, {0, 4}, {2, 0}, {1, 2}}
    };

    static int[] PAIR = {1, 0, 4, 5, 2, 3};

    static void go() {
        int t = in.nextInt();
        while (t-- > 0) {
            char[] d1 = in.nextString().toCharArray();
            char[] d2 = in.nextString().toCharArray();
            boolean match = false;
            for (int i = 0; i < 6 && !match; i++) {
                for (int j = 0; j < 4; j++) {
                    if (d2[i] == d1[0] && d2[C[i][j][0]] == d1[5] && d2[C[i][j][1]] == d1[2]) {
                        int r = PAIR[i];
                        if (d2[r] == d1[1] && d2[C[r][j][0]] == d1[3] && d2[C[r][j][1]] == d1[4]) {
                            match = true;
                            break;
                        }
                    }
                }
            }
            out.println(match ? "Equal" : "Not Equal");
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