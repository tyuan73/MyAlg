package problemset877;


/*

*/

import java.util.*;
import java.io.*;

public class DanilAndAParttimeJob877E {
    static class SegmentTree {
        int[] st, val;
        int size = 0;

        SegmentTree(int[] val) {
            this.val = Arrays.copyOf(val, val.length);
            size = val.length;
            st = new int[4 * size];
        }

        private int init(int idx, int l, int r) {
            if (l == r) {
                st[idx] = val[l];
                return st[idx];
            } else {
                int left = init(idx * 2 + 1, l, (l + r) / 2);
                int right = init(idx * 2 + 2, (l + r) / 2 + 1, r);
                return st[idx] = left + right;
            }
        }

        private int query(int idx, int l, int r, int ql, int qr) {
            if (qr < l || r < ql) return 0;
            if (ql <= l && r <= qr) return st[idx];
            int left = query(idx * 2 + 1, l, (l + r) / 2, ql, qr);
            int right = query(idx * 2 + 2, (l + r) / 2 + 1, r, ql, qr);
            return left + right;
        }

        public int query(int ql, int qr) {
            return query(0, 0, size - 1, ql, qr);
        }

        private int update(int idx, int l, int r, int ql, int qr) {
            if (qr < l || r < ql) return 0;
            if (ql <= l && r <= qr) {
                st[idx] = r - l + 1 - st[idx];
            }
            if (l < r) {
                int left = update(idx * 2 + 1, l, (l + r) / 2, ql, qr);
                int right = update(idx * 2 + 2, (l + r) / 2 + 1, r, ql, qr);
                st[idx] = left + right;
            }
            return st[idx];
        }

        public void update(int ql, int qr) {
            update(0, 0, size - 1, ql, qr);
        }
    }

    static void go() {
        int n = in.nextInt();
        List<Integer>[] tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int p = in.nextInt() - 1;
            tree[p].add(i);
        }
        int[] t = in.nextIntArray(n);
        int[] map = new int[n];
        int[][] order = new int[n][2];
        dfs(tree, 0, order, 0, map);
        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            val[map[i]] = t[i];
        }

        SegmentTree st = new SegmentTree(val);
        int q = in.nextInt();
        while (q-- > 0) {
            String op = in.nextString();
            int idx = in.nextInt() - 1;
            if (op.equals("get")) {
                int ans = st.query(map[idx], order[map[idx]][1]);
                out.println(ans);
            } else {
                st.update(map[idx], order[map[idx]][1]);
            }
        }
    }

    static int dfs(List<Integer>[] tree, int node, int[][] order, int i, int[] map) {
        order[i][0] = node;
        map[node] = i;
        int last = i;
        for (int child : tree[node]) {
            last = dfs(tree, child, order, last + 1, map);
        }
        order[i][1] = last;
        return last;
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

        public char nextChar() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            return (char) c;
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