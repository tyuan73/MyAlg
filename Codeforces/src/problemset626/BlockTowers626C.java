package problemset626;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 2/15/16
 * Time: 9:38 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

*/

import java.util.*;
import java.io.*;

public class BlockTowers626C {
    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        if (n == 0) {
            out.println(3 * m);
            return;

        }
        if (m == 0) {
            out.println(2 * n);
            return;
        }

        int l = 2, r = 6000000;
        while (l < r) {
            int mid = (l + r) / 2;
            if (mid >= 2 * n && mid >= 3 * m && (mid / 2 + mid / 3 >= n + m + mid / 6)) {
                r = mid;
            } else
                l = mid + 1;
        }
        out.println(l);
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