package problemset980;


/*

 */

import java.util.*;
import java.io.*;

public class B {
    static void go() {
        int n = in.nextInt(), k = in.nextInt();
        char[][] ans = new char[4][n];
        for (char[] l : ans) Arrays.fill(l, '.');
        for (int r = 1; r <= 2; r++)
            for (int i = 1, j = n - 2; i < j - 1 && k > 1; i++, j--) {
                ans[r][i] = ans[r][j] = '#';
                k -= 2;
            }
        if (k == 1) {
            ans[1][n / 2] = '#';
        } else if (k == 2) {
            ans[1][n / 2] = ans[2][n / 2] = '#';
        }
        out.println("YES");
        for (char[] l : ans)
            out.println(l);
    }

    /**
     * This following solution fails at test case "5 3"
     */
    static void go1() {
        int n = in.nextInt(), k = in.nextInt();
        char[][] ans = new char[4][n];
        for (char[] l : ans) Arrays.fill(l, '.');
        boolean odd = (k % 2) == 1;
        if (odd) k++;
        for (int i = 1; i <= k / 2; i++) {
            ans[1][i] = ans[2][i] = '#';
        }
        if (odd)
            ans[1][k / 2 - 1] = '.';
        out.println("YES");
        for (char[] l : ans)
            out.println(l);
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