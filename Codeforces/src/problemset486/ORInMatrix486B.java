package problemset486;

/**
 * Created by yuantian on 11/11/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class ORInMatrix486B {
    static void go() {
        int m = in.nextInt();
        int n = in.nextInt();
        boolean[] rAllZero = new boolean[m];
        boolean[] cAllZero = new boolean[n];
        int countR = 0;
        int countC = 0;
        boolean allZero = false;
        int[][] A = new int[m][n];
        for (int i = 0; i < m; i++)
            A[i] = in.nextIntArray(n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) {
                    if (!rAllZero[i]) {
                        rAllZero[i] = true;
                        countR++;
                    }
                    if (!cAllZero[j]) {
                        cAllZero[j] = true;
                        countC++;
                    }
                }
            }
        if (countR == m || countC == n) {
            allZero = true;
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1 && ((rAllZero[i] && cAllZero[j]) || allZero)) {
                    out.println("NO");
                    return;
                }
            }

        for (int i = 0; i < m; i++) {
            Arrays.fill(A[i], rAllZero[i] ? 0 : 1);
        }
        for (int i = 0; i < n; i++) {
            if (cAllZero[i]) {
                for (int j = 0; j < m; j++)
                    A[j][i] = 0;
            }
        }
        out.println("YES");
        for (int[] row : A) {
            for (int x : row)
                out.print(x + " ");
            out.println();
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