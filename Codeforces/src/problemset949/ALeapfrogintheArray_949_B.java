package problemset949;


/*

 */

import java.util.*;
import java.io.*;

public class ALeapfrogintheArray_949_B {
    /**
     * Final solution. The best after a few round of improvements
     */
    static void go() {
        long n = in.nextLong();
        int q = in.nextInt();
        while (q-- > 0) {
            long i = in.nextLong();
            long up = 2 * n - i;
            int p = Long.numberOfTrailingZeros(up);
            long ans = n - (up >> (p + 1));
            out.println(ans);
        }
    }

    /**
     * First edition
     */
    static void go1() {
        long n = in.nextLong() - 1;
        int q = in.nextInt();
        while (q-- > 0) {
            long i = in.nextLong() - 1;
            if (i % 2 == 0) {
                out.println((i / 2 + 1));
                continue;
            }
            for (int p = 1; p < 63; p++) {
                long up = 2 * n + 1 - i - (1l << p);
                if ((up % (1l << (p + 1))) == 0) {
                    long ans = n - (up >> (p + 1)) + 1;
                    out.println(ans);
                    break;
                }
            }
        }
    }

    /**
     * Improved a little bit
     */
    static void go2() {
        long n = in.nextLong() - 1;
        int q = in.nextInt();
        while (q-- > 0) {
            long i = in.nextLong() - 1;
            if (i % 2 == 0) {
                out.println((i / 2 + 1));
                continue;
            }
            for (int p = 1; p < 63; p++) {
                long up = 2 * n + 1 - i - (1l << p);
                //if ((up % (1l << (p + 1))) == 0) {
                if (Long.numberOfTrailingZeros(up) > p) {
                    long ans = n - (up >> (p + 1)) + 1;
                    out.println(ans);
                    break;
                }
            }
        }
    }


    /**
     * Improved even more
     */
    static void go3() {
        long n = in.nextLong() - 1;
        int q = in.nextInt();
        while (q-- > 0) {
            long i = in.nextLong() - 1;
            if (i % 2 == 0) {
                out.println((i / 2 + 1));
                continue;
            }
            for (int p = 1; p < 63; p++) {
                long up = 2 * n + 1 - i; // - (1l << p);
                //if ((up % (1l << (p + 1))) == 0) {
                if (Long.numberOfTrailingZeros(up) == p) {
                    long ans = n - (up >> (p + 1)) + 1;
                    out.println(ans);
                    break;
                }
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