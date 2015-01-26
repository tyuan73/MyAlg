package weekly.week12.day4;

/**
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class UWISolution {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";
    static int mod = 1000000007;

    static void solve() {
        int n = ni();
        int[][] co = new int[n][];
        for (int i = 0; i < n; i++) {
            co[i] = new int[]{ni(), ni()};
        }
        int[] from = new int[n - 1];
        int[] to = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = ni() - 1;
            to[i] = ni() - 1;
        }
        int[][] g = packU(n, from, to);
        int[][] pars = parents3(g, 0);
        int[] par = pars[0], ord = pars[1], dep = pars[2];
        int[] clus = decomposeToHeavyLight(g, par, ord);
        int[][] cluspath = clusPaths(clus, ord);
        int[] clusiind = clusIInd(cluspath, n);
        SegmentTreeNodePlus[] sts = new SegmentTreeNodePlus[cluspath.length];
        for (int i = 0; i < cluspath.length; i++) {
            int[][] lco = new int[cluspath[i].length][];
            for (int j = 0; j < cluspath[i].length; j++) {
                lco[j] = co[cluspath[i][j]];
            }
            sts[i] = new SegmentTreeNodePlus(lco);
        }

        int[][] spar = logstepParents(par);
        int Q = ni();
        for (int z = 0; z < Q; z++) {
            int t = ni();
            if (t == 1) {
                int u = ni() - 1, v = ni() - 1, a = ni(), b = ni();
                int lca = lca2(u, v, spar, dep);
                int[][] pr = query2(u, lca, v, clus, cluspath, clusiind, par);
                for (int[] e : pr) {
                    sts[e[0]].update(Math.min(e[1], e[2]), Math.max(e[1], e[2]) + 1, a, b);
                }
            } else {
                int u = ni() - 1, v = ni() - 1;
                long x = ni();
                int lca = lca2(u, v, spar, dep);
                int[][] pr = query2(u, lca, v, clus, cluspath, clusiind, par);
                for (int[] e : pr) {
                    if (e[1] <= e[2]) {
                        x = sts[e[0]].apply(e[1], e[2] + 1, x, false);
                    } else {
                        x = sts[e[0]].apply(e[2], e[1] + 1, x, true);
                    }
                }
                out.println(x);
            }
        }
    }

    public static int lca2(int a, int b, int[][] spar, int[] depth) {
        if (depth[a] < depth[b]) {
            b = ancestor(b, depth[b] - depth[a], spar);
        } else if (depth[a] > depth[b]) {
            a = ancestor(a, depth[a] - depth[b], spar);
        }

        if (a == b)
            return a;
        int sa = a, sb = b;
        for (int low = 0, high = depth[a], t = Integer.highestOneBit(high), k = Integer
                .numberOfTrailingZeros(t); t > 0; t >>>= 1, k--) {
            if ((low ^ high) >= t) {
                if (spar[k][sa] != spar[k][sb]) {
                    low |= t;
                    sa = spar[k][sa];
                    sb = spar[k][sb];
                } else {
                    high = low | t - 1;
                }
            }
        }
        return spar[0][sa];
    }

    protected static int ancestor(int a, int m, int[][] spar) {
        for (int i = 0; m > 0 && a != -1; m >>>= 1, i++) {
            if ((m & 1) == 1)
                a = spar[i][a];
        }
        return a;
    }

    public static int[][] logstepParents(int[] par) {
        int n = par.length;
        int m = Integer.numberOfTrailingZeros(Integer.highestOneBit(n - 1)) + 1;
        int[][] pars = new int[m][n];
        pars[0] = par;
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                pars[j][i] = pars[j - 1][i] == -1 ? -1
                        : pars[j - 1][pars[j - 1][i]];
            }
        }
        return pars;
    }

    public static class SegmentTreeNodePlus {
        public int M, H, N;
        public Node[] node;
        public int[][] cover;

        private static class Node {
            long co;
            long lc;
            long rc;

            public Node() {
                co = 1;
                lc = rc = 0;
            }

            public Node(long co, long lc, long rc) {
                this.co = co;
                this.lc = lc;
                this.rc = rc;
            }

            public long apply(long x, boolean dir) {
                if (!dir) {
                    return (co * x + lc) % mod;
                } else {
                    return (co * x + rc) % mod;
                }
            }
        }

        public SegmentTreeNodePlus(int[][] co) {
            N = co.length;
            M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
            H = M >>> 1;

            node = new Node[M];
            cover = new int[H][];
            for (int i = 0; i < N; i++) {
                node[H + i] = new Node(co[i][0], co[i][1], co[i][1]);
            }
            for (int i = H - 1; i >= 1; i--) propagate(i);
        }

        private void propagate(int cur) {
            node[cur] = prop2(node[2 * cur], node[2 * cur + 1], cover[cur], node[cur], H / Integer.highestOneBit(cur));
        }

        static final int mod = 1000000007;

        // ax+b
        // cx+d
        // c(ax+b)+d=cax+cb+d
        // a(cx+d)+b=acx+ad+b
        // a(ax+b)+b=a^2*x+ab+b
        private Node prop2(Node L, Node R, int[] cover, Node C, int len) {
            if (L != null && R != null) {
                if (C == null) C = new Node();
                if (cover == null) {
                    C.co = L.co * R.co % mod;
                    C.lc = (R.co * L.lc + R.lc) % mod;
                    C.rc = (L.co * R.rc + L.rc) % mod;
                } else {
                    long co = cover[0], c = cover[1];
                    for (int x = len; x > 1; x >>>= 1) {
                        long nco = co * co % mod;
                        long nc = (co * c + c) % mod;
                        co = nco;
                        c = nc;
                    }
                    C.co = co;
                    C.lc = C.rc = c;
                }
                return C;
            } else if (L != null) {
                return prop1(L, cover, C, len);
            } else if (R != null) {
                return prop1(R, cover, C, len);
            } else {
                return null;
            }
        }

        private Node prop1(Node L, int[] cover, Node C, int len) {
            if (C == null) C = new Node();
            if (cover == null) {
                C.co = L.co;
                C.lc = L.lc;
                C.rc = L.rc;
            } else {
                long co = cover[0], c = cover[1];
                for (int x = len; x > 1; x >>>= 1) {
                    long nco = co * co % mod;
                    long nc = (co * c + c) % mod;
                    co = nco;
                    c = nc;
                }
                C.co = co;
                C.lc = C.rc = c;
            }
            return C;
        }

        int[] temp = null;

        public void update(int l, int r, int a, int b) {
            temp = new int[]{a, b};
            if (l < r) update(l, r, a, b, 0, H, 1);
        }

        protected void update(int l, int r, int a, int b, int cl, int cr, int cur) {
            if (cur >= H) {
                node[cur].co = a;
                node[cur].lc = node[cur].rc = b;
            } else if (l <= cl && cr <= r) {
                cover[cur] = temp;
                propagate(cur);
            } else {
                int mid = cl + cr >>> 1;
                boolean bp = false;
                if (cover[cur] != null) {
                    if (2 * cur < H) {
                        cover[2 * cur] = cover[cur];
                        cover[2 * cur + 1] = cover[cur];
                        cover[cur] = null;
                        bp = true;
                    } else {
                        node[2 * cur].co = cover[cur][0];
                        node[2 * cur].lc = node[2 * cur].rc = cover[cur][1];
                        node[2 * cur + 1].co = cover[cur][0];
                        node[2 * cur + 1].lc = node[2 * cur + 1].rc = cover[cur][1];
                        cover[cur] = null;
                    }
                }
                if (cl < r && l < mid) {
                    update(l, r, a, b, cl, mid, 2 * cur);
                } else if (bp) {
                    propagate(2 * cur);
                }

                if (mid < r && l < cr) {
                    update(l, r, a, b, mid, cr, 2 * cur + 1);
                } else if (bp) {
                    propagate(2 * cur + 1);
                }
                propagate(cur);
            }
        }

        public long apply(int l, int r, long x, boolean dir) {
            return apply(l, r, x, dir, 0, H, 1);
        }

        protected long apply(int l, int r, long x, boolean dir, int cl, int cr, int cur) {
            if (l <= cl && cr <= r) {
                return node[cur].apply(x, dir);
            } else {
                int mid = cl + cr >>> 1;
                if (cover[cur] != null) {
                    long co = cover[cur][0], c = cover[cur][1];
                    for (int h = Math.min(r, cr) - Math.max(l, cl); h > 0; h >>>= 1) {
                        if ((h & 1) == 1) {
                            x = (co * x + c) % mod;
                        }
                        long nco = co * co % mod;
                        long nc = (co * c + c) % mod;
                        co = nco;
                        c = nc;
                    }
                    return x;
                }
                if (!dir) {
                    if (cl < r && l < mid) {
                        x = apply(l, r, x, dir, cl, mid, 2 * cur);
                    }
                    if (mid < r && l < cr) {
                        x = apply(l, r, x, dir, mid, cr, 2 * cur + 1);
                    }
                } else {
                    if (mid < r && l < cr) {
                        x = apply(l, r, x, dir, mid, cr, 2 * cur + 1);
                    }
                    if (cl < r && l < mid) {
                        x = apply(l, r, x, dir, cl, mid, 2 * cur);
                    }
                }
                return x;
            }
        }
    }

    public static int[][] parents3(int[][] g, int root) {
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);

        int[] depth = new int[n];
        depth[0] = 0;

        int[] q = new int[n];
        q[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = q[p];
            for (int nex : g[cur]) {
                if (par[cur] != nex) {
                    q[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][]{par, q, depth};
    }

    public static int[] decomposeToHeavyLight(int[][] g, int[] par, int[] ord) {
        int n = g.length;
        int[] size = new int[n];
        Arrays.fill(size, 1);
        for (int i = n - 1; i > 0; i--) size[par[ord[i]]] += size[ord[i]];

        int[] clus = new int[n];
        Arrays.fill(clus, -1);
        int p = 0;
        outer:
        for (int i = 0; i < n; i++) {
            int u = ord[i];
            if (clus[u] == -1) clus[u] = p++;
            for (int v : g[u]) {
                if (par[u] != v && size[v] >= size[u] / 2) {
                    clus[v] = clus[u];
                    continue outer;
                }
            }
            for (int v : g[u]) {
                if (par[u] != v) {
                    clus[v] = clus[u];
                    break;
                }
            }
        }
        return clus;
    }

    public static int[][] clusPaths(int[] clus, int[] ord) {
        int n = clus.length;
        int[] rp = new int[n];
        int sup = 0;
        for (int i = 0; i < n; i++) {
            rp[clus[i]]++;
            sup = Math.max(sup, clus[i]);
        }
        sup++;

        int[][] row = new int[sup][];
        for (int i = 0; i < sup; i++) row[i] = new int[rp[i]];

        for (int i = n - 1; i >= 0; i--) {
            row[clus[ord[i]]][--rp[clus[ord[i]]]] = ord[i];
        }
        return row;
    }

    public static int[] clusIInd(int[][] clusPath, int n) {
        int[] iind = new int[n];
        for (int[] path : clusPath) {
            for (int i = 0; i < path.length; i++) {
                iind[path[i]] = i;
            }
        }
        return iind;
    }

    public static int[][] query2(int x, int anc, int y, int[] clus, int[][] cluspath, int[] clusiind, int[] par) {
        int[][] stack = new int[60][];
        int sp = 0;

        int cx = clus[x]; // ????
        int indx = clusiind[x]; // ?????????
        while (cx != clus[anc]) {
            stack[sp++] = new int[]{cx, indx, 0};
            int con = par[cluspath[cx][0]];
            indx = clusiind[con];
            cx = clus[con];
        }
        stack[sp++] = new int[]{cx, indx, clusiind[anc]};

        int top = sp;
        int cy = clus[y]; // ????
        int indy = clusiind[y]; // ?????????
        while (cy != clus[anc]) {
            stack[sp++] = new int[]{cy, 0, indy};
            int con = par[cluspath[cy][0]];
            indy = clusiind[con];
            cy = clus[con];
        }
        if (clusiind[anc] < indy) {
            stack[sp++] = new int[]{cy, clusiind[anc] + 1, indy};
        }
        for (int p = top, q = sp - 1; p < q; p++, q--) {
            int[] dum = stack[p];
            stack[p] = stack[q];
            stack[q] = dum;
        }
        return Arrays.copyOf(stack, sp);
    }

    static int[][] packU(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
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
