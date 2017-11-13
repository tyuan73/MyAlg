package problemset878;


/*

*/

import java.util.*;
import java.io.*;

public class ShortProgram_878A {
    static void go() {
        int n = in.nextInt();
        int[] bits = new int[11];
        for (int i = 0; i < n; i++) {
            char op = in.nextChar();
            int c = in.nextInt();
            if (op == '|') {
                while (c > 0) {
                    int b = c & -c;
                    bits[Integer.numberOfTrailingZeros(b)] = 1;
                    c -= b;
                }
            } else if (op == '&') {
                int b = 0, bi = 1;
                while (bi < 1024) {
                    if ((bi & c) == 0)
                        bits[b] = 2;
                    b++;
                    bi <<= 1;
                }
            } else {
                while (c > 0) {
                    int b = c & -c;
                    int z = Integer.numberOfTrailingZeros(b);
                    bits[z] = 3 - bits[z];
                    c -= b;
                }
            }
        }
        int or = 0, and = 1023, xor = 0;
        for (int i = 0, bi = 1; i < bits.length; i++, bi <<= 1) {
            if (bits[i] == 1) or |= bi;
            else if (bits[i] == 2) and -= bi;
            else if (bits[i] == 3) xor |= bi;
        }
        out.println(3);
        out.println("| " + or);
        out.println("& " + and);
        out.println("^ " + xor);
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