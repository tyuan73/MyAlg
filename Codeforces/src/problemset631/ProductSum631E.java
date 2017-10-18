package problemset631;


/*

*/

import java.util.*;
import java.io.*;

public class ProductSum631E {
    static int start = 0, end = 0;

    static void go() {
        int n = in.nextInt();
        int[] a = new int[n];
        long[] sum = new long[n];
        long t = 0, total = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            total += (i + 1) * a[i];
            sum[i] = t + a[i];
            t = sum[i];
        }

        start = 0;
        end = 0;
        long[][] lines = new long[n][];
        lines[0] = new long[]{a[0], 0};
        long ans = 0;
        for (int i = 1; i < n; i++) {
            long max = query(lines, i + 1);
            ans = Math.max(ans, max - sum[i]);
            add(lines, a[i], sum[i] - a[i] * (long) (i + 1));
        }

        start = 0;
        end = 0;
        lines[0] = new long[]{a[n - 1], sum[n - 2] - a[n - 1] * n};
        for (int i = n - 2; i >= 0; i--) {
            long max = query(lines, i + 1);
            if (i == 0) {
                ans = Math.max(ans, max);
                //System.out.println("ans = " + ans);
                //add(lines, a[i], -a[i]);
            } else {
                ans = Math.max(ans, max - sum[i - 1]);
                add(lines, a[i], sum[i - 1] - a[i] * (long) (i + 1));
            }
        }

        out.println(total + ans);
    }

    static long query(long[][] lines, int x) {
        while (start < end && lines[start][0] * x + lines[start][1] < lines[start + 1][0] * x + lines[start + 1][1])
            start++;
        return lines[start][0] * x + lines[start][1];
    }

    static void add(long[][] lines, long a, long b) {
        long[] pair = {a, b};
        while (end > start && isBad(lines[end - 1], lines[end], pair))
            end--;
        lines[++end] = pair;
    }

    static boolean isBad(long[] a, long[] b, long[] c) {
        return (a[1] - b[1]) * (c[0] - b[0]) <= (b[1] - c[1]) * (b[0] - a[0]);
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