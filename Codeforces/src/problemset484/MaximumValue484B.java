package problemset484;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 11/13/14
 * Time: 11:40 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/*

 How to find maximum in O(1) ?

For example consider sorted array [2,4,7,11], then
A(0 indexed) will be [-1,-1,-1,2,2,4,4,4,7,7,7,7,11...]
-1 means no element is smaller than i.


http://codeforces.com/blog/entry/14592

 */

public class MaximumValue484B {
    static void go() {
        int n = in.nextInt();
        int[] a = new int[1000001];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            a[x] = x;
        }
        int[] b = new int[n];
        int count = 0;
        for (int i = 1; i < 1000001; i++) {
            if (i != a[i]) {
                a[i] = a[i - 1];
            } else {
                b[count++] = i;
            }
        }

        int max = 0;
        int maxB = b[count - 1];
        for (int i = count - 1; i >= 0 && b[i] > max; i--) {
            int x = b[i];
            for (int j = x * 2; j <= maxB; j += x) {
                max = Math.max(max, a[j - 1] - j + x);
            }
            max = Math.max(max, maxB % x);
        }
        out.println(max);
    }

    static void go1() {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Arrays.sort(a);

        int count = 1;
        for (int i = 1; i < n; i++) {
            if (a[i] != a[i - 1])
                a[count++] = a[i];
        }

        int M = a[count - 1] * 2;
        int[] b = new int[M];
        int pre = -1;
        for (int i = 0, j = 0; i < M; i++) {
            b[i] = pre;
            if (j < n && i == a[j]) {
                pre = a[j++];
            }
        }

        int max = 0;
        for (int i = 0; i < count - 1; i++) {
            for (int j = a[i] * 2; j < M; j += a[i]) {
                max = Math.max(max, b[j] - j + a[i]);
            }
        }

        out.println(max);
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