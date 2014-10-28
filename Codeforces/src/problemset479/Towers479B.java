package problemset479;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 10/27/14
 * Time: 11:07 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

/*

*/

public class Towers479B {
    static void go() {
        int m = in.nextInt();
        int k = in.nextInt();
        int[][] a = new int[m][2];
        for (int i = 0; i < m; i++) {
            a[i][0] = in.nextInt();
            a[i][1] = i + 1;
        }
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int diff = a[m - 1][0] - a[0][0];
        int[][] ans = new int[k][2];
        int step = 0;
        for (int i = 0; i < k; i++, step++) {
            if (diff <= 1)
                break;
            ans[step][0] = a[m - 1][1];
            ans[step][1] = a[0][1];
            a[m - 1][0]--;
            int j = m - 1, v = a[m - 1][0], w = a[m - 1][1];
            while (v < a[j - 1][0]) {
                a[j][0] = a[j - 1][0];
                a[j][1] = a[j - 1][1];
                j--;
            }
            a[j][0] = v;
            a[j][1] = w;
            a[0][0]++;
            j = 0;
            v = a[0][0];
            w = a[0][1];
            while (v > a[j + 1][0]) {
                a[j][0] = a[j + 1][0];
                a[j][1] = a[j + 1][1];
                j++;
            }
            a[j][0] = v;
            a[j][1] = w;
            diff = a[m - 1][0] - a[0][0];
        }

        out.println(diff + " " + step);
        for (int i = 0; i < step; i++)
            out.println(ans[i][0] + " " + ans[i][1]);
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