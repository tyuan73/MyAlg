package problemset962;


/*

 */

import java.util.*;
import java.io.*;

public class MakeASquare_962C {

    static void go() {
        int n = in.nextInt();
        char[] str = Integer.toString(n).toCharArray();
        for (int m = (int) Math.sqrt(n); m > 0; m--) {
            char[] sq = Integer.toString(m * m).toCharArray();
            int i = 0;
            for (int j = 0; j < str.length; j++) {
                if (i < sq.length && str[j] == sq[i])
                    i++;
            }
            if (i == sq.length) {
                out.println(str.length - sq.length);
                return;
            }
        }
        out.println(-1);
    }

    /**
     * First try. recursive. Not the best. > 500ms
     */
    static int[] SQ = new int[44721];

    static {
        for (int i = 1; i <= SQ.length; i++)
            SQ[i - 1] = i * i;
    }

    static void go1() {
        char[] num = in.nextString().toCharArray();
        int n = num.length;
        boolean[] mask = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (op(num, mask, i)) {
                out.println(i);
                return;
            }
        }
        out.println(-1);
    }

    static boolean op(char[] num, boolean[] mask, int count) {
        if (count == 0) return isSQ(num, mask);
        int n = num.length;
        for (int i = 0; i < n; i++) {
            if (mask[i]) continue;
            mask[i] = true;
            if (!isValid(num, mask)) {
                mask[i] = false;
                continue;
            }
            if (op(num, mask, count - 1))
                return true;
            mask[i] = false;
        }
        return false;
    }

    static boolean isValid(char[] n, boolean[] mask) {
        for (int i = 0; i < n.length; i++)
            if (!mask[i])
                return n[i] != '0';
        return true;
    }

    static boolean isSQ(char[] n, boolean[] mask) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n.length; i++)
            if (!mask[i])
                sb.append(n[i]);
        int m = Integer.parseInt(sb.toString());
        return Arrays.binarySearch(SQ, m) >= 0;
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