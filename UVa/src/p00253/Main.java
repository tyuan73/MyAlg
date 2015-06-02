package p00253;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 6/1/15
 * Time: 11:34 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

public class Main {
    static int[][][] C = {
            {{1, 2}, {2, 4}, {4, 3}, {3, 1}},
            {{0, 3}, {3, 5}, {5, 2}, {2, 0}},
            {{0, 1}, {1, 5}, {5, 4}, {4, 0}},
            {{4, 5}, {0, 4}, {1, 0}, {5, 1}},
            {{2, 5}, {0, 2}, {3, 0}, {5, 3}},
            {{3, 4}, {1, 3}, {2, 1}, {4, 2}}
    };

    static void go() {
        while (true) {
            String line;
            try {
                line = in.nextString();
            } catch (Exception e) {
                break;
            }

            char[] c1 = line.substring(0, 6).toCharArray();
            char[] c2 = line.substring(6).toCharArray();
            boolean match = false;
            for (int i = 0; i < 6 && !match; i++) {
                for (int j = 0; j < 4; j++) {
                    if (c2[i] == c1[0] && c2[C[i][j][0]] == c1[3] && c2[C[i][j][1]] == c1[1]) {
                        int r = 5 - i;
                        if (c2[r] == c1[5] && c2[C[r][j][0]] == c1[4] && c2[C[r][j][1]] == c1[2]) {
                            match = true;
                            break;
                        }
                    }
                }
            }
            out.println(match ? "TRUE" : "FALSE");
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