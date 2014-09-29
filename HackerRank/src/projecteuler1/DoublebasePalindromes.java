package projecteuler1;

/**
 * Created by yuantian on 9/24/14.
 */

/*
Project Euler #36: Double-base palindromes

https://www.hackerrank.com/contests/projecteuler/challenges/euler036

*/

import java.util.*;
import java.io.*;

public class DoublebasePalindromes {
    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();
        int total = 0;
        for (int i = 1; i < n; i++) {
            if (i % 10 == 0 || i % k == 0)
                continue;
            if (isPalin(i, 10) && isPalin(i, k))
                total += i;
        }
        out.print(total);
    }

    static private boolean isPalin(int x, int k) {
        int y = 0;
        while (y < x) {
            y = y * k + x % k;
            if (x == y)
                break;
            x /= k;
        }
        return y == x;
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