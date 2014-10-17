package hack101.Sept2014;

/**
 * Created by yuantian on 10/15/14.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class JimAndHisLanPartyUWISolution {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";

    static void solve() {
        int n = ni(), m = ni(), Q = ni();
        int[] a = na(n);
        int[][] gg = makeBuckets(a, m + 1);
        int s = (int) Math.sqrt(n);

        int[][] qs = new int[Q][];
        for (int i = 0; i < Q; i++) {
            qs[i] = new int[]{ni() - 1, ni() - 1};
        }

        int[] ret = new int[m + 1];
        Arrays.fill(ret, -1);
        for (int i = 1; i <= m; i++) {
            if (gg[i].length == 1) {
                ret[i] = 0;
            }
        }

        DJSet ds = new DJSet(n);

        for (int i = 0; i < Q; i += s) {
            DJSet xds = new DJSet(ds);

            for (int j = i; j < i + s && j < Q; j++) {
                ds.union(qs[j][0], qs[j][1]);
            }

            int[] id = new int[m + 1];
            Arrays.fill(id, -1);
            for (int j = 0; j < n; j++) {
                int v = a[j];
                if (id[v] == -1) {
                    id[v] = ds.root(j);
                } else if (id[v] >= 0) {
                    if (id[v] != ds.root(j)) {
                        id[v] = -2;
                    }
                }
            }

            int[] an = new int[m];
            int p = 0;
            for (int j = 1; j <= m; j++) {
                if (id[j] >= 0 && ret[j] == -1) {
                    an[p++] = j;
                }
            }

            for (int j = i; j < i + s && j < Q; j++) {
                xds.union(qs[j][0], qs[j][1]);
                inner:
                for (int k = 0; k < p; k++) {
                    if (ret[an[k]] == -1) {
                        for (int u : gg[an[k]]) {
                            if (!xds.equiv(u, gg[an[k]][0])) continue inner;
                        }
                        ret[an[k]] = j + 1;
                    }
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            out.println(ret[i]);
        }
    }

    public static int[][] makeBuckets(int[] a, int sup) {
        int n = a.length;
        int[][] bucket = new int[sup + 1][];
        int[] bp = new int[sup + 1];
        for (int i = 0; i < n; i++) bp[a[i]]++;
        for (int i = 0; i <= sup; i++) bucket[i] = new int[bp[i]];
        for (int i = n - 1; i >= 0; i--) bucket[a[i]][--bp[a[i]]] = i;
        return bucket;
    }

    public static class DJSet {
        public int[] upper;

        public DJSet(int n) {
            upper = new int[n];
            Arrays.fill(upper, -1);
        }

        public DJSet(DJSet ds) {
            upper = Arrays.copyOf(ds.upper, ds.upper.length);
        }

        public int root(int x) {
            return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
        }

        public boolean equiv(int x, int y) {
            return root(x) == root(y);
        }

        public boolean union(int x, int y) {
            x = root(x);
            y = root(y);
            if (x != y) {
                if (upper[y] < upper[x]) {
                    int d = x;
                    x = y;
                    y = d;
                }
                upper[x] += upper[y];
                upper[y] = x;
            }
            return x == y;
        }

        public int count() {
            int ct = 0;
            for (int u : upper)
                if (u < 0)
                    ct++;
            return ct;
        }
    }

    public static void main(String[] args) throws Exception {
        long S = System.currentTimeMillis();
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        solve();
        out.flush();
        long G = System.currentTimeMillis();
        tr(G - S + "ms");
    }

    private static boolean eof() {
        if (lenbuf == -1) return true;
        int lptr = ptrbuf;
        while (lptr < lenbuf) if (!isSpaceChar(inbuf[lptr++])) return false;

        try {
            is.mark(1000);
            while (true) {
                int b = is.read();
                if (b == -1) {
                    is.reset();
                    return true;
                } else if (!isSpaceChar(b)) {
                    is.reset();
                    return false;
                }
            }
        } catch (IOException e) {
            return true;
        }
    }

    private static byte[] inbuf = new byte[1024];
    static int lenbuf = 0, ptrbuf = 0;

    private static int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private static boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private static int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    private static double nd() {
        return Double.parseDouble(ns());
    }

    private static char nc() {
        return (char) skip();
    }

    private static String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private static char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private static char[][] nm(int n, int m) {
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) map[i] = ns(m);
        return map;
    }

    private static int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private static int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static long nl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static void tr(Object... o) {
        if (INPUT.length() != 0) System.out.println(Arrays.deepToString(o));
    }
}
