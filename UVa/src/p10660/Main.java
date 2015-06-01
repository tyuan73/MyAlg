package p10660;

/**
 * Created by yuantian on 6/1/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    static void go() {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[][] res = new int[n][];
            for (int i = 0; i < n; i++) {
                res[i] = in.nextIntArray(3);
            }

            int min = Integer.MAX_VALUE;
            int[] idx = new int[5];
            for (int o1 = 0; o1 < 21; o1++) {
                int o1x = o1 / 5, o1y = o1 % 5;
                for (int o2 = o1 + 1; o2 < 22; o2++) {
                    int o2x = o2 / 5, o2y = o2 % 5;
                    for (int o3 = o2 + 1; o3 < 23; o3++) {
                        int o3x = o3 / 5, o3y = o3 % 5;
                        for (int o4 = o3 + 1; o4 < 24; o4++) {
                            int o4x = o4 / 5, o4y = o4 % 5;
                            for (int o5 = o4 + 1; o5 < 25; o5++) {
                                int o5x = o5 / 5, o5y = o5 % 5;
                                int dist = 0;
                                for (int[] r : res) {
                                    int d1 = Math.abs(r[0] - o1x) + Math.abs(r[1] - o1y);
                                    int d2 = Math.abs(r[0] - o2x) + Math.abs(r[1] - o2y);
                                    int d3 = Math.abs(r[0] - o3x) + Math.abs(r[1] - o3y);
                                    int d4 = Math.abs(r[0] - o4x) + Math.abs(r[1] - o4y);
                                    int d5 = Math.abs(r[0] - o5x) + Math.abs(r[1] - o5y);
                                    int d0 = Math.min(Math.min(d1, d2), Math.min(d3, d4));
                                    d0 = Math.min(d0, d5) * r[2];
                                    dist += d0;
                                }
                                if (dist < min) {
                                    min = dist;
                                    idx[0] = o1;
                                    idx[1] = o2;
                                    idx[2] = o3;
                                    idx[3] = o4;
                                    idx[4] = o5;
                                }
                            }
                        }
                    }
                }
            }
            out.printf("%d %d %d %d %d\n", idx[0], idx[1], idx[2], idx[3], idx[4]);
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