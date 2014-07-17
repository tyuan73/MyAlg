package projecteuler1;

/**
 * Created by yuantian on 7/17/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class LargestProductInGrid {
    static void go() {
        int max = Integer.MIN_VALUE;
        int[][] a = new int[20][20];
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++)
                a[i][j] = in.nextInt();
        }

        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                if (j >= 3) {
                    max = Math.max(max, a[i][j-3]*a[i][j-2]*a[i][j-1]*a[i][j]);
                }
                if (i >= 3) {
                    max = Math.max(max, a[i-3][j]*a[i-2][j]*a[i-1][j]*a[i][j]);
                }
                if (j >= 3 && i >= 3) {
                    max = Math.max(max, a[i-3][j-3]*a[i-2][j-2]*a[i-1][j-1]*a[i][j]);
                }
                if (j <= 16 && i >= 3) {
                    max = Math.max(max, a[i-3][j+3]*a[i-2][j+2]*a[i-1][j+1]*a[i][j]);
                }
            }
        }
        out.println(max);
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