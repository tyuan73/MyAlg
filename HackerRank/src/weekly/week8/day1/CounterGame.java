package weekly.week8.day1;

/**
 * Created by yuantian on 8/18/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class CounterGame {
    static void go() {
        int t = in.nextInt();

        while (t-- > 0) {
            long n = in.nextLong();
            boolean isL = false;
            if (n < 0) {
                n ^= 1l << 63;
                isL = true;
            }
            long x = 1l << 62;
            while (n > 1) {
                while (x >= n)
                    x >>= 1;
                n -= x;
                isL = !isL;

            }
            out.println(isL ? "Louise" : "Richard");
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

/*
5
6703734870638684097
7597026128958891522
13174607262084689114
6959712971461184279
12572864054437627553
 */

/*
Richard
Richard
Louise
Louise
Louise
 */