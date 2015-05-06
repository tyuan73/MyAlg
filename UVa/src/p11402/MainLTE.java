package p11402;

/**
 * Created by yuantian on 5/1/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class MainLTE {
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
            if (rl > r || rr < l)
                return 0;

            if (l >= rl && r <= rr)
                return node[idx].ones;

            int cur = node[idx].act;
            if (cur != 0) {
                pushUpdate((idx << 1) + 1, l, (r + l) / 2, cur);
                pushUpdate((idx << 1) + 2, (r + l) / 2 + 1, r, cur);
                node[idx].act = 0;
            }

            int left = query((idx << 1) + 1, l, (l + r) / 2, rl, rr);
            int right = query((idx << 1) + 2, (l + r) / 2 + 1, r, rl, rr);
            return left + right;
        }

        // lazy update
        int update(int idx, int l, int r, int rl, int rr, int action) {
            if (rl > r || l > rr)
                return node[idx].ones;

            if (rl <= l && r <= rr) {
                pushUpdate(idx, l, r, action);
                return node[idx].ones;
            }

            int cur = node[idx].act;
            if (cur != 0) {
                pushUpdate((idx << 1) + 1, l, (r + l) / 2, cur);
                pushUpdate((idx << 1) + 2, (r + l) / 2 + 1, r, cur);
                node[idx].act = 0;
            }

            int left = update((idx << 1) + 1, l, (l + r) / 2, rl, rr, action);
            int right = update((idx << 1) + 2, (l + r) / 2 + 1, r, rl, rr, action);

            return (node[idx].ones = left + right);
        }

        // push update down
        void pushUpdate(int idx, int l, int r, int action) {
            if (action == 1) {
                node[idx].ones = 0;
                node[idx].act = 1;
            } else if (action == 2) {
                node[idx].ones = r - l + 1;
                node[idx].act = 2;
            } else if (action == 3) {
                node[idx].ones = (r - l + 1) - node[idx].ones;
                node[idx].act = 3 - node[idx].act; // 1 => 2, 2 => 1, 0 => 3, 3 => 0
            }
            if (l == r)
                node[idx].act = 0;
        }

        public int query(int rl, int rr) {
            return query(0, 0, str.length - 1, rl, rr);
        }

        public void update(int rl, int rr, int action) {
            update(0, 0, str.length - 1, rl, rr, action);
        }
    }

    static void testUpdate(char[] str, int a, int b, int act) {
        for(int i = a; i <=b ; i++) {
            if (act == 1) {
                str[i] = '0';
            } else if (act == 2)
                str[i] = '1';
            else if (act == 3)
                str[i] = str[i] == '1' ? '0' : '1';
        }
    }

    static int testQuery(char[] str, int a, int b) {
        int ret = 0;
        for(int i = a; i <= b;i++)
            if (str[i] == '1')
                ret++;
        return ret;
    }

    static void go() {
        long start = System.currentTimeMillis();
        boolean debug = false;
        char[] ops = {'F', 'E', 'I', 'S'};

        for(int j = 0; j < 100; j++) {

            int n = 1024000;
            StringBuilder sb = new StringBuilder(1024000);
            for (int i = 0; i < n; i++)
                sb.append('0');

            char[] test = sb.toString().toCharArray();

            SegmentTree st = new SegmentTree(sb.toString());
            Random ran = new Random();
            for (int i = 0; i < 1000; i++) {
                int opi = ran.nextInt(4);
                int a = ran.nextInt(100);
                int b = ran.nextInt(1000) + 1023000;
                //int a = ran.nextInt(n), b = ran.nextInt(n);
                //if (a > b) {
                //    int c = a;
                //    a = b;
                //    b = c;
                //}
                if (ops[opi] == 'E') {
                    st.update(a, b, 1);
                    if (debug) testUpdate(test, a, b, 1);
                } else if (ops[opi] == 'F') {
                    st.update(a, b, 2);
                    if (debug) testUpdate(test, a, b, 2);
                } else if (ops[opi] == 'I') {
                    st.update(a, b, 3);
                    if (debug) testUpdate(test, a, b, 3);
                } else {
                    if (!debug) out.println(st.query(a, b));
                    if (debug) {
                        int my = st.query(a, b);
                        int testret = testQuery(test, a, b);
                        if (my != testret)
                            out.println("Wrong!");
                    }
                    //else
                    //out.println("Correct!");
                }
            }
        }
        out.println("total time: " + (System.currentTimeMillis() - start));
        /*
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
        */
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