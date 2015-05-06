package p12532;

/**
 * Created by yuantian on 5/6/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class MainSegmentTree {
    final static int ZEROS = 0;
    final static int NEGS = 1;

    static int[] seq = new int[100001];
    static int[][] st = new int[400004][2];

    static void buildST(int idx, int l, int r) {
        if (l == r) {
            st[idx][ZEROS] = seq[l] == 0 ? 1 : 0;
            st[idx][NEGS] = seq[l] < 0 ? 1 : 0;
            return;
        }
        int mid = (l + r) / 2;
        buildST((idx << 1) + 1, l, mid);
        buildST((idx << 1) + 2, mid + 1, r);

        recalc(idx);
    }

    static void recalc(int idx) {
        st[idx][ZEROS] = st[(idx << 1) + 1][ZEROS] + st[(idx << 1) + 2][ZEROS];
        st[idx][NEGS] = st[(idx << 1) + 1][NEGS] + st[(idx << 1) + 2][NEGS];
    }

    static void update(int idx, int l, int r, int pos, int val) {
        if (pos < l || r < pos)
            return;

        if (l == r) { // == "pos" too
            if (seq[l] == 0) {
                st[idx][ZEROS]--;
            } else if (seq[l] < 0) {
                st[idx][NEGS]--;
            }
            if (val == 0) {
                st[idx][ZEROS]++;
            } else if (val < 0) {
                st[idx][NEGS]++;
            }
            seq[l] = val;
            return;
        }

        int mid = (l + r) / 2;
        if (pos <= mid) {
            update((idx << 1) + 1, l, mid, pos, val);
        } else {
            update((idx << 1) + 2, mid + 1, r, pos, val);
        }

        recalc(idx);
    }

    static int query(int idx, int l, int r, int rl, int rr) {
        if (rr < l || r < rl)
            return 1;

        if (rl <= l && r <= rr) {
            if (st[idx][ZEROS] > 0)
                return 0;
            if ((st[idx][NEGS] & 1) > 0)
                return -1;
            return 1;
        }

        int mid = (l + r) / 2;
        return query((idx << 1) + 1, l, mid, rl, rr) * query((idx << 1) + 2, mid + 1, r, rl, rr);
    }

    static void go() {
        int n, k;
        while (true) {
            try {
                n = in.nextInt();
            } catch (Exception e) {
                break;
            }

            k = in.nextInt();
            for (int i = 0; i < n; i++)
                seq[i] = in.nextInt();
            buildST(0, 0, n - 1);

            while (k-- > 0) {
                String op = in.nextString();
                int a = in.nextInt() - 1, b = in.nextInt();
                if (op.charAt(0) == 'C') {
                    if (b != seq[a] && b * seq[a] <= 0)
                        update(0, 0, n - 1, a, b);
                } else {
                    int ret = query(0, 0, n - 1, a, b - 1);
                    out.print(ret == 0 ? '0' : ret == -1 ? '-' : '+');
                }
            }
            out.println();
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