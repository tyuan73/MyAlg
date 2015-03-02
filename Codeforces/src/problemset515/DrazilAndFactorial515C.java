package problemset515;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/2/15
 * Time: 12:00 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class DrazilAndFactorial515C {
    static int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 2
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // 3
            {0, 0, 2, 1, 0, 0, 0, 0, 0, 0}, // 4! = 3! * 2!
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 5
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, // 6! = 5! * 3!
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // 7
            {0, 0, 3, 0, 0, 0, 0, 1, 0, 0}, // 8! = 7! * 2! * 2! * 2!
            {0, 0, 1, 2, 0, 0, 0, 1, 0, 0} // 9! = 7! * 3! * 3! * 2!
    };

    static void go() {
        int n = in.nextInt();
        String str = in.nextString();
        int[] m = new int[10];
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            int[] k = map[ch - '0'];
            for (int j = 0; j < 10; j++)
                m[j] += k[j];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i > 0; i--) {
            while (m[i] > 0) {
                sb.append((char) ('0' + i));
                m[i]--;
            }
        }
        out.println(sb.toString());
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