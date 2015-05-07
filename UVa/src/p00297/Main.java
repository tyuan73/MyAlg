package p00297;

/**
 * Created by yuantian on 5/7/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    final static int M = 32 * 32;
    static int[] qt = new int[4 * M];
    static String line = null;
    static int pos = 0;

    static void apply(int idx, int l, int r) {
        if (pos >= line.length())
            return;
        char ch = line.charAt(pos);
        pos++;
        if (ch == 'p') {
            int base = idx << 2;
            int size = (r - l + 1) >> 2;
            apply(base + 1, l, l + size - 1);
            apply(base + 2, l + size, l + 2 * size - 1);
            apply(base + 3, l + 2 * size, l + 3 * size - 1);
            apply(base + 4, l + 3 * size, r);
            int sum = qt[base + 1] + qt[base + 2] + qt[base + 3] + qt[base + 4];
            qt[idx] = Math.max(qt[idx], sum);
        } else if (ch == 'f') {
            qt[idx] = r - l + 1;
        }
    }

    static void go() {
        int t = in.nextInt();
        while (t-- > 0) {
            Arrays.fill(qt, 0);
            line = in.nextString();
            pos = 0;
            apply(0, 0, M - 1);
            line = in.nextString();
            pos = 0;
            apply(0, 0, M - 1);
            out.printf("There are %d black pixels.\n", qt[0]);
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