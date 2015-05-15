package p01232;

/**
 * Created by yuantian on 5/15/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    final static int MAX = 100000;
    static int[] maxO = new int[4 * MAX]; // max outline
    static int[] minO = new int[4 * MAX]; // min outline
    static int[] update = new int[4 * MAX]; // need update? for lazy update

    static int query(int idx, int l, int r, int rl, int rr, int height) {
        if (rr < l || r < rl)
            return 0;

        int left = (idx << 1) + 1, right = left + 1, mid = (l + r) / 2;
        if (update[idx] != 0) {
            doUpdate(left, l, mid, update[idx]);
            doUpdate(right, mid + 1, r, update[idx]);
            update[idx] = 0;
        }

        if (rl <= l && r <= rr) {
            if (height >= maxO[idx]) {
                update[idx] = l == r ? 0 : height;
                maxO[idx] = minO[idx] = height;
                return r - l + 1;
            } else if (height < minO[idx]) {
                return 0;
            }
        }

        int ol = query(left, l, mid, rl, rr, height);
        int or = query(right, mid + 1, r, rl, rr, height);
        maxO[idx] = Math.max(maxO[left], maxO[right]);
        minO[idx] = Math.min(minO[left], minO[right]);
        return ol + or;
    }

    static void doUpdate(int idx, int l, int r, int height) {
        update[idx] = l == r ? 0 : height;
        maxO[idx] = minO[idx] = height;
    }

    static void go() {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            Arrays.fill(maxO, 0);
            Arrays.fill(minO, 0);
            Arrays.fill(update, 0);
            int ans = 0;
            for (int i = 0; i < n; i++) {
                int rl = in.nextInt(), rr = in.nextInt() - 1, h = in.nextInt();
                ans += query(0, 0, 100000, rl, rr, h);
            }
            out.println(ans);
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