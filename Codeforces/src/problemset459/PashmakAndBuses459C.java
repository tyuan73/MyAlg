package problemset459;

/**
 * Created by yuantian on 10/27/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class PashmakAndBuses459C {
    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();
        int d = in.nextInt();

        int x = 1;
        for (int i = 0; i < d; i++) {
            x *= k;
            if (x >= n)
                break;
        }
        if (x < n) {
            out.println(-1);
            return;
        }

        int[][] ans = new int[n][d];
        for (int i = 1; i < n; i++) {
            int c = 1;
            for (int j = d - 1; j >= 0; j--) {
                ans[i][j] = ans[i - 1][j] + c;
                if (ans[i][j] == k) {
                    c = 1;
                    ans[i][j] = 0;
                } else
                    c = 0;
            }
        }

        for (int i = 0; i < d; i++, out.println()) {
            for (int j = 0; j < n; j++) {
                out.print((ans[j][i] + 1) + " ");
            }
            //out.println();
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