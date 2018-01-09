package problemset912;


/*

*/

import java.util.*;
import java.io.*;

public class D {
    static void go() {
        long n = in.nextInt(), m = in.nextInt(), r = in.nextInt(), k = in.nextInt();
        long n1 = Math.max(0, n - 2 * r + 2), m1 = Math.max(0, m - 2 * r + 2);
        long sum = 0;
        long count = Math.min(k, m1 * n1);
        k -= count;
        sum += count * r * r;
        System.out.println("n1 = " + n1 + " m1 = " + m1);
        System.out.println("sum = " + sum);
        for (long i = r - 1; i >= 1 && k > 0; i--) {
            if (i + r > n+1) continue;
            for (long j = r; j >= i && k > 0; j--) {
                if (j + r > m+1) continue;
                if (j == r)
                    count = Math.min(k, 2 * (m1 + n1));
                else if (i != j)
                    count = Math.min(k, 8);
                else {
                    count = Math.min(k, 8);
                }
                //if (j == r) {
                //    count = Math.min(k, 2 * (m1 + n1));
                //}
                sum += i * j * count;
                k -= count;
                System.out.println("v = " + (i * j) + " count = " + count);
                System.out.println("sum = " + sum);
            }
        }
        n = n - r + 1;
        m = m - r + 1;
        out.println(sum / (double) (n * m));
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