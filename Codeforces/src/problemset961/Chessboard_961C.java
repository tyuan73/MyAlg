package problemset961;


/*

 */

import java.util.*;
import java.io.*;

public class Chessboard_961C {
    static void go() {
        int n = in.nextInt();
        char[][][] b = new char[4][n][];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++)
                b[i][j] = in.nextString().toCharArray();
        }
        int[][] cost = new int[4][2];
        for (int i = 0; i < 4; i++) {
            int c = 0;
            for (int j = 0, v = 0; j < n; j++) {
                for (int k = 0; k < n; k++, v ^= 1) {
                    if (b[i][j][k] != v + '0')
                        c++;
                }
            }
            cost[i][0] = c;
            c = 0;
            for (int j = 0, v = 1; j < n; j++) {
                for (int k = 0; k < n; k++, v ^= 1) {
                    if (b[i][j][k] != v + '0')
                        c++;
                }
            }
            cost[i][1] = c;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < 4; j++) {
                int c = cost[0][i] + cost[j][i];
                for (int k = 1; k < 4; k++)
                    if (k != 0 && k != j)
                        c += cost[k][i ^ 1];
                min = Math.min(min, c);
            }

        }
        out.println(min);
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