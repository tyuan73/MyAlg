package ioi2014.contest1;

/**
 * Created by yuantian on 7/7/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class ColorGridFaster {
    static void go() {
        int n = in.nextInt();
        int p = in.nextInt();
        boolean[][] lines = new boolean[2][n];
        int[][] op = new int[p][3];
        for(int i = 0; i < p; i++) {
            op[i][0] = in.nextString().charAt(0) == 'C' ? 0 : 1;
            op[i][1] = in.nextInt()-1;
            op[i][2] = in.nextInt();
        }

        long total = 0;
        long[] num = new long[2];
        num[0] = num[1] = n;
        for(int i = p-1; i>= 0; i--) {
            int di = op[i][0];
            int idx = op[i][1];
            if (!lines[di][idx]) {
                lines[di][idx] = true;
                total += num[1-di] * op[i][2];
                num[di]--;
            }
        }
        out.println(total);
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