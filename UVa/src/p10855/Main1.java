package p10855;

/**
 * Created by yuantian on 4/2/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main1 {
    static void go() {
        int N = 0, n = 0;
        while ((N = in.nextInt()) != 0) {
            n = in.nextInt();
            char[][] S = new char[N][];
            for (int i = 0; i < N; i++)
                S[i] = in.nextString().toCharArray();
            char[][] s = new char[n][];
            for (int i = 0; i < n; i++)
                s[i] = in.nextString().toCharArray();

            int count = 0;
            for (int c = 0; c <= N - n; c++) {
                for (int r = 0; r <= N - n; r++) {
                    boolean matched = true;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (S[c + i][r + j] != s[i][j]) {
                                matched = false;
                                break;
                            }
                        }
                        if (!matched)
                            break;
                    }
                    if (matched)
                        count++;
                }
            }
            out.print(count);

            count = 0;
            for (int c = N - 1; c >= n - 1; c--) {
                for (int r = 0; r <= N - n; r++) {
                    boolean matched = true;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (S[r + j][c - i] != s[i][j]) {
                                matched = false;
                                break;
                            }
                        }
                        if (!matched)
                            break;
                    }
                    if (matched)
                        count++;
                }
            }
            out.print(" " + count);

            count = 0;
            for (int c = N - 1; c >= n - 1; c--) {
                for (int r = N - 1; r >= n - 1; r--) {
                    boolean matched = true;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (S[r - i][c - j] != s[i][j]) {
                                matched = false;
                                break;
                            }
                        }
                        if (!matched)
                            break;
                    }
                    if (matched)
                        count++;
                }
            }
            out.print(" " + count);

            count = 0;
            for (int c = 0; c <= N - n; c++) {
                for (int r = N - 1; r >= n - 1; r--) {
                    boolean matched = true;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (S[r - j][c + i] != s[i][j]) {
                                matched = false;
                                break;
                            }
                        }
                        if (!matched)
                            break;
                    }
                    if (matched)
                        count++;
                }
            }
            out.println(" " + count);
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