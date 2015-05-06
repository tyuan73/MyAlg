package p11402;

/**
 * Created by yuantian on 5/5/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    final static int VAL = 0, ACT = 1;
    static int[][] st = new int[5000000][2];
    static String inputstr = null;
    static int last = 0;

    static int buildST(int idx, int l, int r) {
        st[idx][ACT] = 0;
        if (l == r) {
            st[idx][VAL] = inputstr.charAt(l) == '1' ? 1 : 0;
            return st[idx][VAL];
        }

        int left = buildST((idx << 1) + 1, l, (l + r) / 2);
        int right = buildST((idx << 1) + 2, (l + r) / 2 + 1, r);
        return (st[idx][VAL] = left + right);
    }

    static int query(int idx, int l, int r, int rl, int rr) {
        if (rr < l || rl > r)
            return 0;

        if (rl <= l && r <= rr)
            return st[idx][VAL];

        int cur = st[idx][ACT];
        if (cur != 0) {
            pushUpdate((idx << 1) + 1, l, (l + r) / 2, cur);
            pushUpdate((idx << 1) + 2, (l + r) / 2 + 1, r, cur);
            st[idx][ACT] = 0;
        }

        int left = query((idx << 1) + 1, l, (l + r) / 2, rl, rr);
        int right = query((idx << 1) + 2, (l + r) / 2 + 1, r, rl, rr);
        return left + right;
    }

    static int update(int idx, int l, int r, int rl, int rr, int act) {
        if (rr < l || rl > r)
            return st[idx][VAL];

        if (rl <= l && r <= rr) {
            pushUpdate(idx, l, r, act);
            return st[idx][VAL];
        }

        int cur = st[idx][ACT];
        if (cur != 0) {
            pushUpdate((idx << 1) + 1, l, (l + r) / 2, cur);
            pushUpdate((idx << 1) + 2, (l + r) / 2 + 1, r, cur);
            st[idx][ACT] = 0;
        }

        int left = update((idx << 1) + 1, l, (l + r) / 2, rl, rr, act);
        int right = update((idx << 1) + 2, (l + r) / 2 + 1, r, rl, rr, act);
        return (st[idx][VAL] = left + right);
    }

    static void pushUpdate(int idx, int l, int r, int act) {
        if (act == 1) {
            st[idx][VAL] = 0;
            st[idx][ACT] = 1;
        } else if (act == 2) {
            st[idx][VAL] = r - l + 1;
            st[idx][ACT] = 2;
        } else if (act == 3) {
            st[idx][VAL] = (r - l + 1) - st[idx][VAL];
            st[idx][ACT] = 3 - st[idx][ACT]; // 1 => 2, 2 => 1, 0 => 3, 3 => 0
        }
        if (l == r)
            st[idx][ACT] = 0;
    }

    static void go() {
        int t = in.nextInt();
        for (int tt = 1; tt <= t; tt++) {
            out.printf("Case %d:\n", tt);
            int k = in.nextInt();
            StringBuilder sb = new StringBuilder();
            while (k-- > 0) {
                int dup = in.nextInt();
                String pat = in.nextString();
                while (dup-- > 0) sb.append(pat);
            }
            //System.out.println("String = " + sb.toString());
            inputstr = sb.toString();
            last = inputstr.length() - 1;
            buildST(0, 0, last);

            //print();

            int q = in.nextInt();
            int qn = 1;
            while (q-- > 0) {
                String qry = in.nextString();

                int rl = in.nextInt(), rr = in.nextInt();
                //System.out.println("op = " + qry.charAt(0) + " from : " + rl + " to: " + rr);
                char op = qry.charAt(0);
                if (op == 'E') {
                    update(0, 0, last, rl, rr, 1);
                } else if (op == 'F') {
                    update(0, 0, last, rl, rr, 2);
                } else if (op == 'I') {
                    update(0, 0, last, rl, rr, 3);
                } else {
                    out.printf("Q%d: %d\n", qn++, query(0, 0, last, rl, rr));
                }
                //print();
            }
        }
    }

    static void print() {
        for (int i = 0; i < 20; i++)
            System.out.printf("%2d ", st[i][VAL]);
        System.out.println();
        for (int i = 0; i < 20; i++)
            System.out.printf("%2d ", st[i][ACT]);
        System.out.println();
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