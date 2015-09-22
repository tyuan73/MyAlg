package p00416;

/**
 * Created by yuantian on 9/22/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {

    /*
    static int[] MAP = new int[128];

    static {
        Arrays.fill(MAP, 200);
        MAP[123] = 9;
        MAP[127] = 8;
        MAP[112] = 7;
        MAP[95] = 6;
        MAP[91] = 5;
        MAP[51] = 4;
        MAP[121] = 3;
        MAP[109] = 2;
        MAP[48] = 1;
        MAP[126] = 0;
    }
    */

    static int[] VALID = {123, 127, 112, 95, 91, 51, 121, 109, 48, 126};
    static int[] input = null;
    static boolean valid = false;
    static int n;

    static void go() {
        while ((n = in.nextInt()) != 0) {
            input = new int[n];
            for (int i = 0; i < n; i++) {
                input[i] = convert(in.nextCharArray(7));
            }

            valid = false;

            check(0, 0, (1 << 7) - 1);

            out.println(valid ? "MATCH" : "MISMATCH");
        }
    }

    static void check(int idx, int from, int mask) {
        if (idx == n) {
            valid = true;
            return;
        }

        int num = input[idx];
        for (int i = from; i < 10; i++) {
            int target = VALID[i];
            if ((num | target) != target)
                continue;

            int invalid = num ^ target;
            if ((invalid | mask) != mask)
                continue;

            check(idx + 1, i + 1, mask ^ invalid);

            if (valid) return;
        }
    }

    static int convert(char[] str) {
        int ret = 0;
        for (int i = 6, bit = 1; i >= 0; i--, bit <<= 1) {
            if (str[i] == 'Y')
                ret |= bit;
        }
        return ret;
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