package problemset965;


/*

 */

import java.util.*;
import java.io.*;

public class B {
    static void go() {
        int n = in.nextInt(), k = in.nextInt();
        int[][] f = new int[n][n], g = new int[n][n];
        char[][] str = new char[n][];
        for (int i = 0; i < n; i++) {
            str[i] = in.nextString().toCharArray();
        }
        int max = 0, x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (str[i][j] == '#') continue;
                if (j == 0) f[i][j] = 1;
                else f[i][j] = f[i][j - 1] + 1;
                if (f[i][j] >= k) {
                    for (int p = j - k + 1; p <= j; p++)
                        g[i][p]++;
                }
            }
        }
        // for(int i = 0; i < n; i++) Arrays.fill(f[i], 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (str[j][i] == '#') continue;
                if (j == 0) f[j][i] = 1;
                else f[j][i] = f[j - 1][i] + 1;
                if (f[j][i] >= k) {
                    for (int p = j - k + 1; p <= j; p++)
                        g[p][i]++;
                }
            }
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (g[i][j] > max) {
                    max = g[i][j];
                    x = i;
                    y = j;
                }
            }
        out.println((x + 1) + " " + (y + 1));
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