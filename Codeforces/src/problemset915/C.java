package problemset915;


/*

*/

import java.util.*;
import java.io.*;

public class C {
    static void go() {
        long a = in.nextLong(), b = in.nextLong();
        String A = Long.toString(a);
        int[] count = new int[10];
        for (int i = 0; i < A.length(); i++)
            count[A.charAt(i) - '0']++;

        long c = (long) Math.pow(10, A.length());
        boolean less = (b / c) > 0, back = false;
        StringBuilder sb = new StringBuilder();
        while (c > 1) {
            if (less) {
                cons(sb, count);
                break;
            }
            int up = (int) ((b % c) / (c / 10));
            int j = back ? up - 1 : up;
            while (j >= 0 && count[j] == 0) j--;
            if (j < 0) {
                c *= 10;
                back = true;
                count[sb.charAt(sb.length() - 1) - '0']++;
                sb.setLength(sb.length() - 1);
                continue;
            }
            count[j]--;
            sb.append((char) (j + '0'));
            if (j < up) {
                less = true;
            }
            c /= 10;
        }
        out.println(sb);
    }

    static void cons(StringBuilder sb, int[] count) {
        for (int i = 9; i >= 0; i--) {
            while (count[i] > 0) {
                sb.append((char) (i + '0'));
                count[i]--;
            }
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