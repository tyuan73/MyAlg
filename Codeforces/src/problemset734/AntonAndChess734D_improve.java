package problemset734;

/**
 * Created by yuantian on 11/16/16.
 */

/*

*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class AntonAndChess734D_improve {
    static void go() {
        int n = in.nextInt();
        int x0 = in.nextInt(), y0 = in.nextInt();

        // x, y, z
        // [0] -> x == 0 & y < 0
        // [1] -> x == 0 & y > 0
        // [2] -> y == 0 & x < 0
        // [3] -> y == 0 & x > 0
        // [4] -> x == y & x < 0
        // [5] -> x == y & x > 0
        // [6] -> x + y == 0 & x < 0
        // [7] -> x + y == 0 & x > 0
        int[][] d = {
                {Integer.MIN_VALUE, 0},
                {Integer.MAX_VALUE, 0},
                {Integer.MIN_VALUE, 0},
                {Integer.MAX_VALUE, 0},
                {Integer.MIN_VALUE, 0},
                {Integer.MAX_VALUE, 0},
                {Integer.MIN_VALUE, 0},
                {Integer.MAX_VALUE, 0}
        };

        for (int i = 0; i < n; i++) {
            char c = in.nextChar();
            int type = 0;
            if (c == 'R')
                type = 1;
            else if (c == 'B')
                type = 2;
            else
                type = 3;
            int x = in.nextInt() - x0, y = in.nextInt() - y0;

            if (x == 0) {
                if (y < 0 && y > d[0][0]) {
                    d[0][0] = y;
                    d[0][1] = type;
                } else if (y > 0 && y < d[1][0]) {
                    d[1][0] = y;
                    d[1][1] = type;
                }
            } else if (y == 0) {
                if (x < 0 && x > d[2][0]) {
                    d[2][0] = x;
                    d[2][1] = type;
                } else if (x > 0 && x < d[3][0]) {
                    d[3][0] = x;
                    d[3][1] = type;
                }
            } else if (x == y) {
                if (x < 0 && x > d[4][0]) {
                    d[4][0] = x;
                    d[4][1] = type;
                } else if (x > 0 && x < d[5][0]) {
                    d[5][0] = x;
                    d[5][1] = type;
                }
            } else if (x + y == 0) {
                if (x < 0 && x > d[6][0]) {
                    d[6][0] = x;
                    d[6][1] = type;
                } else if (x > 0 && x < d[7][0]) {
                    d[7][0] = x;
                    d[7][1] = type;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            if ((d[i][1] & 1) > 0) {
                out.println("YES");
                return;
            }
        }
        for (int i = 4; i < 8; i++) {
            if ((d[i][1] & 2) > 0) {
                out.println("YES");
                return;
            }
        }

        out.println("NO");
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

        public char nextChar() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            return (char) c;
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