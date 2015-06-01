package p11236;

/**
 * Created by yuantian on 6/1/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    static void go() {
        long MAX = 2000;
        int count = 0;
        /*
        for (long a = 1; a <= MAX / 4; a++) {
            for (long b = a; b <= (MAX - a) / 3; b++) {
                for (long c = b; c <= (MAX - a - b) / 2; c++) {
                    for (long d = c; d <= MAX - a - b - c; d++) {
                        if (1000000 * (a + b + c + d) == a * b * c * d) {
                            out.printf("%d %d %d %d\n", a, b, c, d);
                            count++;
                        }
                    }
                }
            }
        }
        out.println("total = " + count);
        count = 0;
        */

        for (long a = 1; a <= MAX / 4; a++) {
            for (long b = a; b <= (MAX - a) / 3; b++) {
                long from = Math.max(b, 1000000 / a / b);
                //long to = Math.min(MAX - a - b, MAX / a / b);
                for (long c = from; c <= (MAX - a - b) / 2; c++) {
                    long x = 1000000 * (a + b + c);
                    long y = a * b * c - 1000000;
                    if (y > 0 && (x % y) == 0 && x / y >= c && (a + b + c + x / y) <= MAX) {
                        out.printf("%.2f %.2f %.2f %.2f\n", a / 100.0, b / 100.0, c / 100.0, x / y / 100.0);
                        //out.printf("%d %d %d %d\n", a, b, c, x / y);
                        //if (1000000 * (a + b + c + x / y) != a * b * c * (x / y))
                        //    System.out.println("wrong!");
                        count++;
                    }
                    if (y > 0 && x / y <= c)
                        break;
                }
            }
        }
        //out.println("total = " + count);
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