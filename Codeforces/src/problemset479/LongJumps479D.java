package problemset479; /**
 * Created by yuantian on 10/20/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class LongJumps479D {
    static void go() {
        int n = in.nextInt();
        int l = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();
        int[] a = in.nextIntArray(n);
        boolean found1 = false;
        for (int i = 0; i < n; i++) {
            if (bseach(a, a[i] + x, i) > 0) {
                found1 = true;
                break;
            }
        }

        boolean found2 = false;
        for (int i = 0; i < n; i++) {
            if (bseach(a, a[i] + y, i) > 0) {
                found2 = true;
                break;
            }
        }

        // if both found
        if (found1 && found2) {
            out.println(0);
            return;
        }
        // if only one found
        if (found1 || found2) {
            out.println(1);
            out.println(found1 ? y : x);
            return;
        }

        // both not found
        // if found (x+y)
        for (int i = 0; i < n; i++) {
            if (bseach(a, a[i] + x + y, i) > 0) {
                out.println(1);
                out.println(a[i] + x);
                return;
            }
        }

        // if found (y-x)
        for (int i = 0; i < n; i++) {
            if (bseach(a, a[i] + y - x, i) > 0) {
                if (a[i] - x >= 0) {
                    out.println(1);
                    out.println(a[i] - x);
                    return;
                }
                if (a[i] + y < l) {
                    out.println(1);
                    out.println(a[i] + y);
                    return;
                }
            }
        }
        // otherwise
        out.println(2);
        out.println(x + " " + y);
    }

    static int bseach(int[] a, int x, int from) {
        int l = from, r = a.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (a[m] == x)
                return m;
            else if (a[m] > x)
                r = m - 1;
            else
                l = m + 1;
        }
        return -1;
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