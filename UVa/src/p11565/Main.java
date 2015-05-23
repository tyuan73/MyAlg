package p11565;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/22/15
 * Time: 11:04 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

public class Main {
    static int[] sqrt = null;

    static void go() {
        sqrt = new int[20000];
        for (int i = 0; i <= 100; i++) {
            for (int j = i * i; j < (i + 1) * (i + 1); j++)
                sqrt[j] = i;
        }

        int n = in.nextInt();

        while (n-- > 0) {
            int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
            if (!check(a, b, c)) {
                out.println("No solution.");
            }
        }
    }

    static boolean check(int a, int b, int c) {
        int n = sqrt[c / 3];
        for (int x = -sqrt[c]; x <= n; x++) {
            int r = c - x * x;
            int m = sqrt[r / 2];
            for (int y = -sqrt[r]; y <= m; y++) {
                int r1 = r - y * y;
                int z = sqrt[r1];
                if (z * z != r1) continue;
                if (z <= y) break;
                if (x * y * z == b) {
                    if (x + y + z == a && x != y && x != z) {
                        out.println(x + " " + y + " " + z);
                        return true;
                    }
                    if (x - y - z == a && x != -y && x != -z) {
                        out.println(x + " " + (-z) + " " + (-y));
                        return true;
                    }
                }
            }
        }
        return false;
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