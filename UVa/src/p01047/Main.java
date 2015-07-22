package p01047;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 7/21/15
 * Time: 10:49 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

public class Main {
    static void go() {
        int tc = 0;
        int n;
        while ((n = in.nextInt()) != 0) {
            tc++;
            int m = in.nextInt();

            int[] a = new int[n];
            for (int i = n - 1; i >= 0; i--)
                a[i] = in.nextInt();


            int k = in.nextInt();
            int[][] over = new int[k][2];
            for (int i = 0; i < k; i++) {
                int t = in.nextInt();
                int mask = 0;
                int[] o = in.nextIntArray(t);
                int x = in.nextInt();
                for (int y : o) {
                    a[n - y] -= x;
                    mask |= 1 << (n - y);
                }
                over[i][0] = mask;
                over[i][1] = x;
            }

            int max = 0, mask = 0;
            for (int i = (1 << n) - (1 << (n - m)); i >= (1 << m) - 1; i--) {
                if (!checkBits(i, m)) continue;
                int total = 0;
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0) {
                        total += a[j];
                    }
                }
                for (int[] x : over) {
                    if ((x[0] & i) > 0)
                        total += x[1];
                }
                if (total > max) {
                    max = total;
                    mask = i;
                }
            }
            out.printf("Case Number  %d\n", tc);
            out.printf("Number of Customers: %d\n", max);
            out.print("Locations recommended:");
            for (int i = 1; i <= n; i++) {
                if ((mask & (1 << (n - i))) > 0)
                    out.print(" " + i);
            }
            out.println();
            out.println();
        }

    }

    static boolean checkBits(int a, int t) {
        int bit = 1;
        int c = 0;
        while (bit <= a) {
            if ((a & bit) > 0)
                c++;
            bit <<= 1;
        }
        return c == t;
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