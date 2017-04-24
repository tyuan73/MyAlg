package problemset797;

/**
 * Created by yuantian on 4/24/17.
 */

/*

*/

import java.util.*;
import java.io.*;

public class ArrayQueries797E_imp1 {
    static void go() {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt() + i;
        }

        int q = in.nextInt();
        int[][] Q = new int[q][3];
        for (int i = 0; i < q; i++) {
            Q[i][0] = in.nextInt(); // p
            Q[i][1] = in.nextInt(); // k
            Q[i][2] = i;
        }
        Arrays.sort(Q, (x, y) -> (x[1] == y[1] ? x[0] - y[0] : x[1] - y[1]));
        int[] ret = new int[q];
        int[] dp = new int[n + 1];
        int preK = -1;
        int m = (int) Math.sqrt(n);
        for (int i = 0; i < q; i++) {
            int k = Q[i][1];
            if (k > m) {
                int op = 0;
                int p = Q[i][0];
                while (p <= n) {
                    p = a[p] + k;
                    op++;
                }
                ret[Q[i][2]] = op;
            } else {
                if (k != preK) {
                    preK = k;
                    int minP = Q[i][0];
                    for (int j = n; j >= minP; j--) {
                        if (a[j] + k > n) {
                            dp[j] = 1;
                        } else {
                            dp[j] = dp[a[j] + k] + 1;
                        }
                    }
                }
                ret[Q[i][2]] = dp[Q[i][0]];
            }
        }

        for (int z : ret)
            out.println(z);
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