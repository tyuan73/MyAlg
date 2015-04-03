package p12187;

/**
 * Created by yuantian on 4/3/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {
        int n, r, c, k;
        while ((n = in.nextInt()) != 0) {
            r = in.nextInt();
            c = in.nextInt();
            k = in.nextInt();
            int[][] country = new int[r + 2][c + 2];
            for (int i = 1; i <= r; i++) {
                for (int j = 1; j <= c; j++) {
                    country[i][j] = in.nextInt();
                }
            }

            while (k-- > 0) {
                int[][] c1 = new int[r + 2][c + 2];
                for (int i = 1; i <= r; i++)
                    System.arraycopy(country[i], 1, c1[i], 1, c);

                for (int i = 1; i <= r; i++) {
                    for (int j = 1; j <= c; j++) {
                        int atk = (country[i][j] + 1) % n;
                        if (country[i - 1][j] == atk)
                            c1[i - 1][j] = country[i][j];
                        if (country[i + 1][j] == atk)
                            c1[i + 1][j] = country[i][j];
                        if (country[i][j - 1] == atk)
                            c1[i][j - 1] = country[i][j];
                        if (country[i][j + 1] == atk)
                            c1[i][j + 1] = country[i][j];
                    }
                }
                country = c1;
            }

            for (int i = 1; i <= r; i++) {
                out.print(country[i][1]);
                for (int j = 2; j <= c; j++) {
                    out.print(" " + country[i][j]);
                }
                out.println();
            }
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