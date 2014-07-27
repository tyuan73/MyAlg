package hackjuly14;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 7/26/14
 * Time: 11:12 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class BusStation {
    static void go() {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++)
            a[i] = a[i - 1] + in.nextInt();

        for (int i = 1; i <= n; i++) {
            if (a[n] % a[i] != 0)
                continue;
            int x = a[i], y = a[n] / x;
            boolean yes = true;
            int next = i;
            for (int j = 2; j < y; j++) {
                next = search(a, next, n, j * x);
                if (next == -1) {
                    yes = false;
                    break;
                }
            }
            if (yes) {
                out.print(x + " ");
            }
        }
        out.println();
    }

    static int search(int[] a, int from, int to, int v) {
        while (from <= to) {
            int mid = (from + to) / 2;
            if (a[mid] == v)
                return mid;
            else if (a[mid] > v)
                to = mid - 1;
            else
                from = mid + 1;
        }
        return -1;
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