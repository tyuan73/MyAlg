package problemset734;

/**
 * Created by yuantian on 11/16/16.
 */

/*

*/

import java.util.*;
import java.io.*;

public class AntonAndMakingPotions734C {
    static void go() {
        long n = in.nextLong();
        int m = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        int s = in.nextInt();
        int[] a = in.nextIntArray(m);
        int[] b = in.nextIntArray(m);
        int[] c = in.nextIntArray(k);
        int[] d = in.nextIntArray(k);

        long min = n * x;
        int p = search(d, s);
        if (p >= 0) {
            long q = Math.min(n, c[p]);
            min = (n - q) * x;
        }
        for (int i = 0; i < m; i++) {
            int search = s - b[i];
            if (search < 0) continue;
            int idx = search(d, search);
            if (idx == -1) {
                min = Math.min(min, n * a[i]);
                continue;
            }
            long q = Math.min(n, c[idx]);
            min = Math.min(min, (n - q) * a[i]);
        }

        out.println(min);
    }

    static int search(int[] d, int x) {
        if (x < d[0])
            return -1;
        if (x >= d[d.length - 1])
            return d.length - 1;
        int l = 0, r = d.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (d[mid] > x) {
                r = mid - 1;
            } else
                l = mid;
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