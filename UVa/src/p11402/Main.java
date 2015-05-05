package p11402;

/**
 * Created by yuantian on 5/1/15.
 */

/*

*/

import javax.swing.text.Segment;
import java.util.*;
import java.io.*;

public class Main {
    static class Ele {
        int ones = 0, act = 0;
    }

    static class SegmentTree {
        Ele[] node = null;
        char[] str = null;

        public SegmentTree(String input) {
            node = new Ele[4 * input.length()];
            str = input.toCharArray();
            build(0, 0, str.length - 1);
        }

        int build(int idx, int l, int r) {
            node[idx] = new Ele();
            if (l == r) {
                node[idx].ones = str[l] == '1' ? 1 : 0;
                return node[idx].ones;
            }

            int left = build((idx << 1) + 1, l, (l + r) / 2);
            int right = build((idx << 1) + 2, (l + r) / 2 + 1, r);

            return node[idx].ones = left + right;
        }

        int query(int idx, int l, int r, int rl, int rr) {
            if (rl > r || rr < l) return 0;
            if (l >= rl && r <= rr) return node[idx].ones;

            pushUpdate(idx, l, r, 0);

            int left = query((idx << 1) + 1, l, (l + r) / 2, rl, rr);
            int right = query((idx << 1) + 2, (l + r) / 2 + 1, r, rl, rr);
            return left + right;
        }

        // lazy update
        int update(int idx, int l, int r, int rl, int rr, int action) {
            if (rl > r || l > rr) return node[idx].ones;

            pushUpdate(idx, l, r, action);
            if (rl <= l && r <= rr) {
                return node[idx].ones;
            }

            int left = update((idx << 1) + 1, l, (l + r) / 2, rl, rr, action);
            int right = update((idx << 1) + 2, (l + r) / 2 + 1, r, rl, rr, action);
            node[idx].act = 0;

            return (node[idx].ones = left + right);
        }

        // push update down
        void pushUpdate(int idx, int l, int r, int action) {
            int cur = node[idx].act;
            if (cur != 0) {
                pushUpdate((idx << 1) + 1, l, (l + r) / 2, cur);
                pushUpdate((idx << 1) + 2, (l + r) / 2 + 1, r, cur);
                node[idx].act = 0;
            }
            if (action == 1) {
                node[idx].ones = 0;
            } else if (action == 2) {
                node[idx].ones = r - l + 1;
            } else if (action == 3) {
                node[idx].ones = (r - l + 1) - node[idx].ones;
            }
            node[idx].act = l == r ? 0 : action;
        }

        public int query(int rl, int rr) {
            return query(0, 0, str.length - 1, rl, rr);
        }

        public void update(int rl, int rr, int action) {
            update(0, 0, str.length - 1, rl, rr, action);
        }
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
            SegmentTree st = new SegmentTree(sb.toString());
            int q = in.nextInt();
            int qn = 1;
            while (q-- > 0) {
                String qry = in.nextString();

                int l = in.nextInt(), r = in.nextInt();
                //System.out.println("op = " + qry.charAt(0) + " from : " + l + " to: " + r);
                char op = qry.charAt(0);
                if (op == 'E') {
                    st.update(l, r, 1);
                } else if (op == 'F') {
                    st.update(l, r, 2);
                } else if (op == 'I') {
                    st.update(l, r, 3);
                } else {
                    out.printf("Q%d: %d\n", qn++, st.query(l, r));
                }
            }
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