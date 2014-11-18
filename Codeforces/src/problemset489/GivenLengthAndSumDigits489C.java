package problemset489;

/**
 * Created by yuantian on 11/18/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class GivenLengthAndSumDigits489C {
    static void go() {
        int m = in.nextInt();
        int s = in.nextInt();

        // corner case: s == 0
        if (s == 0) {
            out.println(m == 1 ? "0 0" : "-1 -1");
            return;
        }

        int[] min = new int[m];
        int[] max = new int[m];

        // find the min value
        min[0] = 1;
        int n = s - 1;
        for (int i = m - 1; i > 0; i--) {
            min[i] = Math.min(n, 9);
            n -= min[i];
        }
        min[0] += n; // don't forget

        // the first digit can not be over 9
        if (min[0] > 9) {
            out.println("-1 -1");
            return;
        }

        // find the max value
        n = s;
        max[0] = Math.min(9, n);
        n -= max[0];
        for (int i = 1; i < m; i++) {
            max[i] = Math.min(9, n);
            n -= max[i];
        }

        // print out
        for (int x : min) {
            out.print(x);
        }
        out.print(" ");
        for (int y : max) {
            out.print(y);
        }
        out.println();
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