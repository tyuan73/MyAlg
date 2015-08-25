package problemset570;

/**
 * Created by yuantian on 8/24/15.
 */

import java.util.*;
import java.io.*;

public class TreeRequests570D {
    static int[] level = null;
    static int[] parent = null;

    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();

        List<Integer>[] tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList();
        }

        for (int i = 2; i <= n; i++) {
            int from = in.nextInt();
            tree[from].add(i);
        }
        tree[0].add(1);

        char[] seq = in.nextCharArray(n);

        level = new int[n + 1];
        parent = new int[n + 1];
        int[] map = new int[n + 1]; // original to new
        int[] rmap = new int[n + 1]; // new to original
        int idx = 1;

        int start = 0, end = 0;
        int layer = 1;
        int[][] bit = new int[26][n + 1];
        ArrayList<Integer> delim = new ArrayList<Integer>();
        while (start <= end) {
            delim.add(idx);
            for (int i = start; i <= end; i++) {
                for (int next : tree[rmap[i]]) {
                    rmap[idx] = next;
                    map[next] = idx;
                    level[idx] = layer;
                    parent[idx] = i;

                    add(bit[seq[next - 1] - 'a'], idx);

                    idx++;
                }
            }
            layer++;
            start = end + 1;
            end = idx - 1;
        }

        for (int i = 0; i < m; i++) {
            int nidx = map[in.nextInt()];
            int h = in.nextInt();
            if (h <= level[nidx] || h >= delim.size()) {
                out.println("Yes");
                continue;
            }

            int ll = delim.get(h - 1);
            int rr = delim.get(h) - 1;
            int l = getLeft(ll, rr, nidx);
            if (l == -1) {
                out.println("Yes");
                continue;
            }
            int r = getRight(ll, rr, nidx);

            int oddcount = 0;
            for (int c = 0; c < 26; c++) {
                int count = get(bit[c], l - 1, r);
                if ((count & 1) > 0)
                    oddcount++;
            }
            if (oddcount > 1) {
                out.println("No");
            } else {
                out.println("Yes");
            }
        }
    }

    static int getLeft(int ll, int rr, int target) {
        int lev = level[target];
        while (ll < rr) {
            int mid = (ll + rr) / 2;
            if (getParent(level, parent, mid, lev) < target) {
                ll = mid + 1;
            } else {
                rr = mid;
            }
        }
        if (getParent(level, parent, ll, lev) == target)
            return ll;
        else
            return -1;
    }

    static int getRight(int ll, int rr, int target) {
        int lev = level[target];
        while (ll < rr) {
            int mid = (ll + rr + 1) / 2;
            if (getParent(level, parent, mid, lev) > target) {
                rr = mid - 1;
            } else {
                ll = mid;
            }
        }
        return ll;
    }

    static int getParent(int[] level, int[] parent, int p, int lev) {
        int mylev = level[p];
        while (mylev > lev) {
            p = parent[p];
            mylev--;
        }
        return p;
    }

    static void add(int[] a, int i) {
        for (; i < a.length; i += (i & -i)) {
            a[i]++;
        }
    }

    static int get(int[] a, int l, int r) {
        int ret = 0;
        while (r != l) {
            if (r > l) {
                ret += a[r];
                r -= (r & -r);
            } else {
                ret -= a[l];
                l -= (l & -l);
            }
        }
        return ret;
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

/**
 * input:
 * <p>
 * 5 6
 * 1 2 2 1
 * baabb
 * 1 1
 * 1 2
 * 5 1
 * 4 1
 * 4 2
 * 3 2
 * <p>
 * output:
 * <p>
 * Yes
 * No
 * Yes
 * Yes
 * Yes
 * Yes
 * <p>
 * <p>
 * input:
 * <p>
 * 5 9
 * 1 1 1 2
 * edbcb
 * 1 3
 * 2 1
 * 1 3
 * 2 1
 * 2 2
 * 2 2
 * 1 1
 * 1 3
 * 2 1
 * <p>
 * output:
 * <p>
 * Yes
 * Yes
 * Yes
 * Yes
 * Yes
 * Yes
 * Yes
 * Yes
 * Yes
 * <p>
 * <p>
 * 5 1
 * 1 1 1 2
 * edbcb
 * 1 3
 */