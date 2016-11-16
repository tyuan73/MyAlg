package problemset733;

/**
 * Created by yuantian on 11/16/16.
 */

/*

*/

import java.util.*;
import java.io.*;

public class KostyaTheSculptor733D {
    static class Marble implements Comparable<Marble> {
        int a, b, c, idx;

        public Marble(int a, int b, int c, int idx) {
            this.idx = idx;
            if (a < b) {
                int x = a;
                a = b;
                b = x;
            }
            if (a < c) {
                int x = a;
                a = c;
                c = x;
            }
            if (b < c) {
                int x = b;
                b = c;
                c = x;
            }
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int compareTo(Marble m) {
            if (this.a == m.a) {
                if (this.b == m.b) {
                    return m.c - this.c;
                }
                return m.b - this.b;
            }
            return m.a - this.a;
        }
    }

    static void go() {
        int maxValue = 0;
        int maxIdx = -1, maxIdx1 = -1;
        int n = in.nextInt();
        Marble[] marbles = new Marble[n];
        for (int i = 0; i < n; i++) {
            int x[] = in.nextIntArray(3);
            marbles[i] = new Marble(x[0], x[1], x[2], i + 1);
            if (marbles[i].c > maxValue) {
                maxValue = marbles[i].c;
                maxIdx = i + 1;
            }
        }
        Arrays.sort(marbles);
        for (int i = 1; i < n; i++) {
            Marble m1 = marbles[i - 1];
            Marble m2 = marbles[i];
            if (m2.a == m1.a && m2.b == m1.b) {
                int c = m1.c + m2.c;
                int min = Math.min(m1.b, c);
                if (min > maxValue) {
                    maxValue = min;
                    maxIdx = m1.idx;
                    maxIdx1 = m2.idx;
                }
            }
        }

        if (maxIdx1 == -1) {
            out.println(1);
            out.println(maxIdx);
        } else {
            out.println(2);
            out.println(maxIdx + " " + maxIdx1);
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