package weekly.week6.day5;

/**
 * mmaxio's solution
 * https://www.hackerrank.com/rest/contests/w6/challenges/dynamic-summation/hackers/mmaxio/download_solution
 */
import java.io.*;
import java.util.*;

public class MMaxioSolution {

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    static final int[] MODS = { 64, 81, 25, 49, 11, 13, 17, 19, 23, 29, 31, 37,
            41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 };
    static final int LOG = 17;

    List<Integer>[] g;
    int[][] up;
    int inTime;
    int inOutTime;
    int[] from;
    int[] to;
    int[] depth;

    int[] enter, exit;
    int[] sz;

    void dfs(int v, int p, int curDepth) {
        sz[v] = 1;
        from[v] = inTime++;
        enter[v] = inOutTime++;
        up[v][0] = p;
        depth[v] = curDepth;
        for (int i = 1, prev = p; i < LOG; i++) {
            prev = up[v][i] = up[prev][i - 1];
        }
        for (int to : g[v]) {
            if (to != p) {
                dfs(to, v, curDepth + 1);
                sz[v] += sz[to];
            }
        }
        to[v] = inTime - 1;
        exit[v] = inOutTime++;
    }

    static int pow(int a, long b, int mod) {
        int ret = 1;
        for (; b > 0; a = a * a % mod, b >>= 1) {
            if ((b & 1) == 1) {
                ret = ret * a % mod;
            }
        }
        return ret;
    }

    int eval(long a, long b, int mod) {
        return (pow((int) (a % mod), b, mod)
                + pow((int) ((a + 1) % mod), b, mod) + pow(
                (int) ((b + 1) % mod), a, mod)) % mod;
    }

    boolean isAnc(int v1, int v2) {
        return enter[v1] <= enter[v2] && exit[v2] <= exit[v1];
    }

    int goUp(int v, int dist) {
        for (int i = 0; dist > 0; i++, dist >>= 1) {
            if ((dist & 1) == 1) {
                v = up[v][i];
            }
        }
        return v;
    }

    void solve() throws IOException {
        int n = nextInt();
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList(2);
        }
        up = new int[n][LOG];
        for (int i = 0; i < n - 1; i++) {
            int v1 = nextInt() - 1;
            int v2 = nextInt() - 1;
            g[v1].add(v2);
            g[v2].add(v1);
        }
        from = new int[n];
        to = new int[n];
        enter = new int[n];
        exit = new int[n];
        sz = new int[n];
        depth = new int[n];
        dfs(0, 0, 0);

        FenwickTree[] trees = new FenwickTree[MODS.length];
        for (int i = 0; i < MODS.length; i++) {
            trees[i] = new FenwickTree(n, MODS[i]);
        }
        int q = nextInt();

        int[] addAll = new int[MODS.length];
        int[] sumAll = new int[MODS.length];

        while (q-- > 0) {
            char type = nextToken().charAt(0);
            if (type == 'U') {
                int r = nextInt() - 1;
                int v = nextInt() - 1;
                long a = nextLong();
                long b = nextLong();
                int[] vals = new int[MODS.length];
                for (int i = 0; i < MODS.length; i++) {
                    vals[i] = eval(a, b, MODS[i]);
                }
                int cntV;
                if (v == r) {
                    cntV = n;
                    for (int i = 0; i < MODS.length; i++) {
                        addAll[i] = (addAll[i] + vals[i]) % MODS[i];
                    }
                } else if (isAnc(v, r)) {
                    for (int i = 0; i < MODS.length; i++) {
                        addAll[i] = (addAll[i] + vals[i]) % MODS[i];
                    }
                    int ch = goUp(r, depth[r] - depth[v] - 1);
                    cntV = n - sz[ch];
                    for (int i = 0; i < MODS.length; i++) {
                        trees[i].add(from[ch], to[ch], -vals[i]);
                    }
                } else {
                    cntV = sz[v];
                    for (int i = 0; i < MODS.length; i++) {
                        trees[i].add(from[v], to[v], vals[i]);
                    }
                }
                for (int i = 0; i < MODS.length; i++) {
                    int delta = (int) ((long) cntV * vals[i] % MODS[i]);
                    sumAll[i] = (sumAll[i] + delta) % MODS[i];
                }
            } else {
                int r = nextInt() - 1;
                int v = nextInt() - 1;
                int m = nextInt();
                int[] vals = new int[MODS.length];
                if (v == r) {
                    for (int i = 0; i < MODS.length; i++) {
                        vals[i] = sumAll[i];
                    }
                } else if (isAnc(v, r)) {
                    for (int i = 0; i < MODS.length; i++) {
                        vals[i] = sumAll[i];
                    }
                    int ch = goUp(r, depth[r] - depth[v] - 1);
                    for (int i = 0; i < MODS.length; i++) {
                        vals[i] = (vals[i] - trees[i].get(from[ch], to[ch]) + MODS[i])
                                % MODS[i];
                        int delta = (int) ((long) sz[ch] * addAll[i] % MODS[i]);
                        vals[i] = (vals[i] - delta + MODS[i]) % MODS[i];
                    }
                } else {
                    int cntV = sz[v];
                    for (int i = 0; i < MODS.length; i++) {
                        vals[i] = (vals[i] + trees[i].get(from[v], to[v]))
                                % MODS[i];
                        int delta = (int) ((long) cntV * addAll[i] % MODS[i]);
                        vals[i] = (vals[i] + delta) % MODS[i];
                    }
                }
                out.println(getAnswer(vals, m));
            }
        }
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static int getAnswer(int[] vals, int m) {
        int[] gcds = new int[MODS.length];
        for (int i = 0; i < MODS.length; i++) {
            gcds[i] = gcd(MODS[i], m);
        }
        outer: for (int res = 0; res < m; res++) {
            for (int i = 0; i < MODS.length; i++) {
                if (res % gcds[i] != vals[i] % gcds[i]) {
                    continue outer;
                }
            }
            return res;
        }
        throw new AssertionError();
    }

    static class FenwickTree {
        private int n;
        private int mod;

        private int[] c0;
        private int[] c1;

        private int fix(int x) {
            return (x % mod + mod) % mod;
        }

        public FenwickTree(int n, int mod) {
            this.n = n;
            this.mod = mod;
            this.c0 = new int[n];
            this.c1 = new int[n];
        }

        void add(int low, int high, int delta) {
            /**
             * [low, high]
             */
            internalUpdate(low, -(low - 1) * delta, delta);
            internalUpdate(high, high * delta, -delta);
        }

        private void internalUpdate(int x, int d0, int d1) {
            d0 = fix(d0);
            d1 = fix(d1);
            for (int i = x; i < n; i |= i + 1) {
                c0[i] = (c0[i] + d0) % mod;
                c1[i] = (c1[i] + d1) % mod;
            }
        }

        int get(int low, int high) {
            /**
             * [low, high]
             */
            return fix(get(high) - get(low - 1));
        }

        int get(int x) {
            /**
             * [0, x]
             */
            int a1 = 0;
            int a0 = 0;
            for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
                a1 = (a1 + c1[i]) % mod;
                a0 = (a0 + c0[i]) % mod;
            }
            return (int) (((long) a1 * x + a0) % mod);
        }
    }

    MMaxioSolution() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new MMaxioSolution();
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }

    String nextString() {
        try {
            return br.readLine();
        } catch (IOException e) {
            eof = true;
            return null;
        }
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }
}