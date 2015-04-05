package p10920;

/**
 * Created by yuantian on 4/2/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    static void go() {
        int n = 0;

        while ((n = in.nextInt()) != 0) {
            long p = in.nextLong();
            long base = (long) Math.sqrt(p);

            // the position of "1"
            int origin = (n + 1) / 2;

            //long upper = 0;
            if ((base & 1) == 0) {
                base++;
            } else {
                if (p == base * base) {
                    // output: Line = 2, column = 2.
                    out.print("Line = " + (origin + base / 2));
                    out.println(", column = " + (origin + base / 2) + ".");
                    continue;
                }
                base += 2;
            }

            long max = base * base;
            long l = max - base + 1;
            if (p >= l) {
                out.print("Line = " + (origin - base / 2 + p - l));
                out.println(", column = " + (origin + base / 2) + ".");
                continue;
            }
            l -= base - 1;
            if (p >= l) {
                out.print("Line = " + (origin - base / 2));
                out.println(", column = " + (origin - base / 2 + p - l) + ".");
                continue;
            }
            l -= base - 1;
            if (p >= l) {
                out.print("Line = " + (origin + base / 2 - p + l));
                out.println(", column = " + (origin - base / 2) + ".");
                continue;
            }
            l -= base - 1;
            out.print("Line = " + (origin + base / 2));
            out.println(", column = " + (origin + base / 2 - p + l) + ".");
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