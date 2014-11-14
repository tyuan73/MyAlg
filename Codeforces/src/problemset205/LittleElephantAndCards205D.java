package problemset205;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 11/14/14
 * Time: 12:14 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class LittleElephantAndCards205D {
    static void go() {
        int n = in.nextInt();
        int[] c = new int[2 * n];
        for (int i = 0; i < 2 * n; i++)
            c[i] = in.nextInt();
        int[] s = shrink(c);
        int[][] count = new int[2 * n][2];
        for (int i = 0; i < 2 * n; i += 2) {
            count[s[i]][0]++;
            if (s[i + 1] != s[i])
                count[s[i + 1]][1]++;
        }

        boolean ok = false;
        int min = Integer.MAX_VALUE;
        int half = (n + 1) / 2;
        for (int i = 0; i < 2 * n; i++) {
            if (count[i][0] >= half) {
                min = 0;
                ok = true;
                break;
            }
            if (count[i][0] + count[i][1] >= half) {
                ok = true;
                min = Math.min(min, half - count[i][0]);
            }
        }
        out.println(ok ? min : -1);
    }

    public static int[] shrink(int[] a) {
        int n = a.length;
        long[] b = new long[n];
        for (int i = 0; i < n; i++) b[i] = (long) a[i] << 32 | i;
        b = radixSort(b);
        int[] ret = new int[n];
        int p = 0;
        ret[(int) b[0]] = 0;
        for (int i = 1; i < n; i++) {
            if (b[i] >> 32 != b[i - 1] >> 32) p++;
            ret[(int) b[i]] = p;
        }
        return ret;
    }

    public static long[] radixSort(long[] f) {
        long[] to = new long[f.length];
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] >>> 16 & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] >>> 16 & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] >>> 32 & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] >>> 32 & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < f.length; i++) b[1 + (int) (f[i] >>> 48 & 0xffff)]++;
            for (int i = 1; i <= 65536; i++) b[i] += b[i - 1];
            for (int i = 0; i < f.length; i++) to[b[(int) (f[i] >>> 48 & 0xffff)]++] = f[i];
            long[] d = f;
            f = to;
            to = d;
        }
        return f;
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