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
        int[][] info = new int[n][2];
        for (int i = 0; i < n; i++) {
            info[i][0] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            info[i][1] = in.nextInt();
        }

        int[][] count = new int[L + 1][D + 1];
        int[][] cost = new int[L + 1][D + 1];
        for (int i = 0; i < n; i++) {
            count[info[i][0]][info[i][1]]++;
            //cost[info[i][0]][info[i][1]] += info[i][1];
        }
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= D; j++) {
                cost[i][j] = count[i][j] * j + cost[i - 1][j] + cost[i][j - 1] - cost[i - 1][j - 1];
                count[i][j] = count[i][j] + count[i - 1][j] + count[i][j - 1] - count[i - 1][j - 1];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = L; i > 0; i--) {

            int c = count[i][D] - count[i - 1][D];
            if (c == 0) continue;
            int total = cost[L][D] - cost[i][D];
            if (total >= min) break;
            if (c <= count[i - 1][D]) {
                c = count[i - 1][D] - c;
                int j = i - 1;
                int l = 0, r = D;
                while (l < r) {
                    int m = (l + r) / 2;
                    if (count[j][m] <= c) {
                        l = m + 1;
                    } else {
                        r = m;
                    }
                }
                total += cost[j][l - 1] + (c + 1 - count[j][l - 1]) * l;
            }
            min = Math.min(min, total);
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