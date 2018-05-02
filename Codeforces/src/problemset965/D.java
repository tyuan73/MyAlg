package problemset965;


/*

 */

import java.util.*;
import java.io.*;

public class D {
    /**
     * First ACed solution. Binary search.
     * A better solution below.
     */
    static void go1() {
        int w = in.nextInt(), l = in.nextInt();
        int[] s = in.nextIntArray(w - 1);
        int max = 0;
        for (int x : s) max += x;
        int a = 0, b = max;
        while (a < b) {
            int mid = (a + b + 1) / 2;
            if (ok(s, l, mid))
                a = mid;
            else
                b = mid - 1;
        }
        out.println(a);
    }

    static private boolean ok(int[] s, int l, int m) {
        int w = s.length + 2;
        int[] s1 = new int[w], s2 = new int[w];
        System.arraycopy(s, 0, s1, 1, w - 2);
        s1[0] = m;
        s2[w - 1] = m;
        while (w-- > 1) {
            for (int i = Math.max(0, w - l); i < w; i++) {
                if (s2[w] == 0) break;
                int x = Math.min(s2[w], s1[i]);
                s2[w] -= x;
                s2[i] += x;
                s1[i] -= x;
            }
            if (s2[w] > 0) return false;
        }
        return true;
    }

    /**
     * A much faster and easier solution
     */
    static void go() {
        int w = in.nextInt(), l = in.nextInt();
        int[] sum = new int[w];
        for (int i = 0; i < w - 1; i++) {
            int a = in.nextInt();
            sum[i + 1] = sum[i] + a;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = l; i < w; i++) {
            ans = Math.min(ans, sum[i] - sum[i - l]);
        }
        out.println(ans);
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