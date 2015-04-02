package p10646;

/**
 * Created by yuantian on 4/2/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {
        int t = in.nextInt();
        for (int c = 1; c <= t; c++) {
            int[] cards = new int[52];
            String[] v = new String[52];
            boolean[] removed = new boolean[52];
            for (int i = 0; i < 52; i++) {
                v[i] = in.nextString();
                if (v[i].charAt(0) >= '2' && v[i].charAt(0) <= '9') {
                    cards[i] = (v[i].charAt(0) - '0');
                } else
                    cards[i] = 10;
            }

            int y = 0;
            int top = 25;
            System.out.println(v[top]);
            for (int j = 0; j < 3; j++) {
                int x = cards[top];
                for (int i = top; i <= top + 10 - x; i++) {
                    removed[i] = true;
                }
                top += 10 - x + 1;
                y += x;
                System.out.println(v[top]);
            }
            int ans = 51;
            while (y > 0) {
                if (!removed[ans]) {
                    y--;
                }
                ans--;
            }
            out.println("Case " + c + ": " + v[ans]);
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