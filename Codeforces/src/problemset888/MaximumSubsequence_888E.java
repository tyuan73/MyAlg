package problemset888;


/*

*/

import java.util.*;
import java.io.*;

public class MaximumSubsequence_888E {
    static void go() {
        int n = in.nextInt(), m = in.nextInt();
        int[] a = in.nextIntArray(n);
        int h = n / 2;
        int[] b = new int[1 << h];
        for (int i = 0; i < 1 << h; i++) {
            long x = 0;
            for (int bit = 1, j = 0; j < h; bit <<= 1, j++) {
                if ((i & bit) > 0)
                    x += a[j];
            }
            b[i] = (int) (x % m);
        }

        Arrays.sort(b);
        long max = 0;
        for (int i = 0; i < 1 << n - h; i++) {
            long x = 0;
            for (int bit = 1, j = 0; j < n - h; bit <<= 1, j++) {
                if ((i & bit) > 0)
                    x += a[j + h];
            }
            x %= m;
            int idx = bSearch(b, m - x - 1);
            if (idx < 0) idx = b.length - 1;
            max = Math.max(max, (x + b[idx]) % m);
        }
        out.println(max);
    }

    static int bSearch(int[] b, long t) {
        int l = -1, r = b.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (b[mid] <= t)
                l = mid;
            else
                r = mid - 1;
        }
        return l;
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