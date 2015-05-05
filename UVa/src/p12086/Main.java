package p12086;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/1/15
 * Time: 11:41 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

public class Main {
    static void go() {
        int n, tc = 1;
        while ((n = in.nextInt()) != 0) {
            int[] a = new int[n + 1];
            int[] b = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                b[i] = in.nextInt();
                add(a, i, b[i]);
            }

            if (tc != 1) out.println();
            out.println("Case " + tc + ":");

            String op;
            while (!(op = in.nextString()).equals("END")) {
                if (op.charAt(0) == 'S') {
                    int idx = in.nextInt();
                    int val = in.nextInt() - b[idx];
                    add(a, idx, val);
                } else {
                    int l = in.nextInt() - 1;
                    int r = in.nextInt();
                    out.println(sum(a, r) - sum(a, l));
                }
            }

            tc++;
        }
    }

    static void add(int[] a, int i, int val) {
        for (; i < a.length; i += i & -i) {
            a[i] += val;
        }
    }

    static int sum(int[] a, int i) {
        int ret = 0;
        for (; i > 0; i -= i & -i)
            ret += a[i];
        return ret;
    }

    static int diff(int[] a, int i, int j) {
        int ret = 0;
        while (i != j) {
            if (i > j) {
                ret -= a[i];
                i -= i & -i;
            } else {
                ret += a[j];
                j -= j & -j;
            }
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