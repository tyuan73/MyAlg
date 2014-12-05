package problemset490;

/**
 * Created by yuantian on 12/4/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Chocolate490D {
    static void go() {
        long a1 = in.nextInt();
        long b1 = in.nextInt();
        long a2 = in.nextInt();
        long b2 = in.nextInt();
        long s1 = a1 * b1, s2 = a2 * b2;
        int d12 = 0, d13 = 0, d22 = 0, d23 = 0;
        while (s1 % 2 == 0) {
            d12++;
            s1 /= 2;
        }
        while (s1 % 3 == 0) {
            d13++;
            s1 /= 3;
        }
        while (s2 % 2 == 0) {
            d22++;
            s2 /= 2;
        }
        while (s2 % 3 == 0) {
            d23++;
            s2 /= 3;
        }
        if (s1 != s2) {
            out.println(-1);
            return;
        }

        int total = 0;
        while (d13 > d23 && a1 % 3 == 0) {
            a1 /= 3;
            a1 *= 2;
            total++;
            d13--;
            d12++;
        }
        while (d13 > d23 && b1 % 3 == 0) {
            b1 /= 3;
            b1 *= 2;
            total++;
            d13--;
            d12++;
        }

        while (d23 > d13 && a2 % 3 == 0) {
            a2 /= 3;
            a2 *= 2;
            total++;
            d23--;
            d22++;
        }
        while (d23 > d13 && b2 % 3 == 0) {
            b2 /= 3;
            b2 *= 2;
            total++;
            d23--;
            d22++;
        }

        while (d12 > d22 && a1 % 2 == 0) {
            a1 /= 2;
            total++;
            d12--;
        }
        while (d12 > d22 && b1 % 2 == 0) {
            b1 /= 2;
            total++;
            d12--;
        }

        while (d22 > d12 && a2 % 2 == 0) {
            a2 /= 2;
            total++;
            d22--;
        }
        while (d22 > d12 && b2 % 2 == 0) {
            b2 /= 2;
            total++;
            d22--;
        }

        out.println(total);
        out.println(a1 + " " + b1);
        out.println(a2 + " " + b2);
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