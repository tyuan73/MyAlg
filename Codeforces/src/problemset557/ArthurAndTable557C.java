package problemset557;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 7/30/15
 * Time: 10:46 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

public class ArthurAndTable557C {
    static void go() {
        //long start = System.currentTimeMillis();
        int L = 100000, D = 200;
        int n = in.nextInt();
        int[][] info = new int[2][];
        info[0] = in.nextIntArray(n);
        info[1] = in.nextIntArray(n);
        int[] lc = new int[L + 1];
        int[] ld = new int[L + 1];

        int[][] count = new int[L + 1][D + 1];
        for (int i = 0; i < n; i++) {
            int l = info[0][i], d = info[1][i];
            count[l][d]++;
            lc[l]++;
            ld[l] += d;
        }
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= D; j++) {
                count[i][j] += count[i - 1][j];
            }
            lc[i] += lc[i - 1];
        }

        int min = Integer.MAX_VALUE;
        int pred = 0;
        for (int i = L; i > 0; i--) {
            int c = lc[i] - lc[i - 1];
            if (c == 0) continue;
            if (pred >= min) break;

            int cost = 0;
            if (c <= lc[i - 1]) {
                int diff = lc[i - 1] - c;
                int x = 0, j = 1;
                for (; j <= D; j++) {
                    x += count[i - 1][j];
                    if (x <= diff) {
                        cost += count[i - 1][j] * j;
                    } else {
                        x -= count[i - 1][j];
                        break;
                    }
                }
                cost += (diff - x + 1) * j;
            }

            min = Math.min(min, cost + pred);
            pred += ld[i];
        }
        out.println(min);
        //out.println(System.currentTimeMillis() - start);
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