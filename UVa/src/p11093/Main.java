package p11093;

/**
 * Created by yuantian on 4/1/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {
        int t = in.nextInt();
        for (int j = 1; j <= t; j++) {
            int n = in.nextInt();
            int[] gas = new int[2 * n];
            for (int i = 0; i < n; i++) {
                gas[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                gas[i] -= in.nextInt();
                gas[i + n] = gas[i];
            }
            if (!check(gas, n, j)) {
                out.println("Case " + j + ": Not possible");
            }
        }
    }

    static boolean check(int[] gas, int n, int j) {
        int from = 0, rem = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (rem < 0) {
                from = i;
                rem = gas[i];
            } else {
                rem += gas[i];
                if (rem >= 0 && i == from + n - 1) {
                    out.println("Case " + j + ": Possible from station " + (from + 1));
                    return true;
                }
            }

        }
        return false;
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