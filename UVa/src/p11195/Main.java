package p11195;

/**
 * Created by yuantian on 5/27/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    static int ans;
    //static int[] rows = null;
    static boolean[] cols = null, d1 = null, d2 = null;
    static boolean[][] bad = null;
    static int n;

    static void go() {

        int tc = 1;
        while ((n = in.nextInt()) != 0) {
            ans = 0;
            //rows = new int[n];
            cols = new boolean[n];
            d1 = new boolean[2 * n];
            d2 = new boolean[2 * n];
            bad = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                String line = in.nextString();
                for (int j = 0; j < n; j++) {
                    if (line.charAt(j) == '*') {
                        bad[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < n; i++)
                check(0, i);

            out.printf("Case %d: %d\n", tc++, ans);
        }
    }

    static void check(int r, int c) {

        if (bad[r][c] || d1[r + c] || d2[r - c + n])
            return;
        if (r == n-1) {
            ans++;
            return;
        }
        cols[c] = true;
        d1[r + c] = true;
        d2[r - c + n] = true;
        for (int i = 0; i < n; i++) {
            if (!cols[i])
                check(r + 1, i);
        }
        d2[r - c + n] = false;
        d1[r + c] = false;
        cols[c] = false;
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