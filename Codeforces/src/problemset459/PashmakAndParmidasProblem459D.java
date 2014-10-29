package problemset459;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 10/28/14
 * Time: 10:26 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;

public class PashmakAndParmidasProblem459D {
    static void go() {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] l = new int[n];
        for (int i = 0; i < n; i++) {
            if (map.containsKey(a[i]))
                l[i] = map.get(a[i]) + 1;
            else
                l[i] = 1;
            map.put(a[i], l[i]);
        }
        map.clear();
        int[] r = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (map.containsKey(a[i]))
                r[i] = map.get(a[i]) + 1;
            else
                r[i] = 1;
            map.put(a[i], r[i]);
        }

        // fenwick tree
        int[] ft = new int[n + 1];
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            ans += sum(ft, l[i] - 1);
            add(ft, r[i], 1);
        }
        out.println(ans);
    }

    private static int sum(int[] ft, int i) {
        int ret = 0;
        for (; i > 0; i -= i & -i)
            ret += ft[i];
        return ret;
    }

    private static void add(int[] ft, int i, int val) {
        int len = ft.length;
        for (; i < len; i += i & -i)
            ft[i] += val;
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
