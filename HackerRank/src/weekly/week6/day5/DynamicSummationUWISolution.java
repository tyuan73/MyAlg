package weekly.week6.day5;

/**
 * uwi's solution
 * https://www.hackerrank.com/rest/contests/w6/challenges/dynamic-summation/hackers/uwi/download_solution
 *
 */

/*
Submissions will no longer be placed on the leaderboard. You may still attempt this problem for practice.
Given a tree of N nodes, where each node is uniquely numbered in between [1, N]. Each node also has a value which is initially 0. You need to perform following two operations in the tree.

Update Operation
Report Operation
Update Operation

U r t a b
Adds ab + (a+1)b + (b+1)a to all nodes in the subtree rooted at t, considering that tree is rooted at r (see explanation for more details).

Report Operation

R r t m
Output the sum of all nodes in the subtree rooted at t, considering that tree is rooted at r. Output the sum modulo m (see explanation for more details).

Input Format

First line contains N, number of nodes in the tree.
Next N-1 lines contain two space separated integers x and y which denote that there is an edge between node x and node y.
Next line contains Q, number of queries to follow.
Next Q lines follow, each line will be either a report operation or an update operation.

Output Format

For each report query output the answer in a separate line.

Constraints

1 ≤ N ≤ 100000
1 ≤ Q ≤ 100000
1 ≤ m ≤ 101
1 ≤ r, t, x, y ≤ N
x ≠ y
1 ≤ a, b ≤ 1018

Notes

There will be at most one edge between a pair of nodes.
There will be no loop.
Tree will be completely connected.
Sample Input

4
1 2
2 3
3 4
4
U 3 2 2 2
U 2 3 2 2
R 1 2 8
R 4 3 9
Sample Output

2
3
Explanation

Initially Values in each node : [0,0,0,0]
The first query is U 3 2 2 2. Here, tree is rooted at 3. It looks like

    3(0)
   / \
  /   \
 2(0)  4(0)
 |
 |
 1(0)
For the sub tree rooted at 2 ( nodes 2 and 1 ), we add ab + (a+1)b + (b+1)a = 22 + 32 + 32 = 22. After first update operation, nodes 1, 2, 3, and 4 will have values 22, 22, 0 and 0 respectively.

    3(0)
   / \
  /   \
 2(22) 4(0)
 |
 |
 1(22)
The second query is U 2 3 2 2. Here, tree is rooted at 2. It looks like

    2(22)
   / \
  /   \
 1(22) 3(0)
       |
       |
       4(0)
For the sub tree rooted at 3 (nodes 3 and 4), we add ab + (a+1)b + (b+1)a = 22 + 32 + 32 = 22. After second update operation, nodes 1, 2, 3, and 4 each have values 22,22,22,22 respectively.

    2(22)
   / \
  /   \
 1(22) 3(22)
       |
       |
       4(22)
The first report query is R 1 2 8 asks for the sum modulo 8 of the subtree rooted at 2, when the tree is rooted at 1. The tree looks like

1(22)
 \
  \
   2*(22)
   |
   |
   3*(22)
   |
   |
   4*(22)
The sum of the values of nodes 2, 3 and 4 are

(22 + 22 + 22) % 8 = 2
The second report query is R 4 3 9 asks for the sum modulo 9 of the subtree rooted at 3 when the tree is rooted at 4. The tree looks like

4(22)
 \
  \
   3*(22)
   |
   |
   2*(22)
   |
   |
   1*(22)
The sum of the values of nodes 3, 2 and 1 are

(22 + 22 + 22) % 9 = 3
Time Limits:
C, C++: 4s | Java and other JVM based languages: 10s | Python, Python3 = 45s | Other interpreted Language: 30s | C#, Haskell: 10s | Rest: 3 times of default.
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.BitSet;
import java.util.InputMismatchException;

public class DynamicSummationUWISolution {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";

    static void solve()
    {
//		int[] primes = sieveEratosthenes(101);
//		int[] f = Arrays.copyOf(primes, primes.length);
//		for(int i = 0;i < f.length;i++){
//			while(f[i] <= 101){
//				f[i] *= primes[i];
//			}
//			f[i] /= primes[i];
//		}
//		tr(f);
//		int x = 1;
//		for(int i = 25;i < f.length;i++){
//			x *= f[i];
//		}
//		tr(x);
        // [0,6) 908107200
        // [6,12) 247110827
        // [12,17) 259106347
        // [17,21) 21182921
        // [21,25) 56606581
        // [26,27) 101
//		if(true)return;
        int[] mods = {908107200, 247110827, 259106347, 21182921, 56606581, 101};
//		for(int i = 1;i <= 101;i++){
//			int x = 1;
//			for(int m : mods){
//				x *= gcd(i, m);
//			}
//			if(i != x)throw new RuntimeException();
//		}

        int P = mods.length;

        int n = ni();
        int[] from = new int[n-1];
        int[] to = new int[n-1];
        for(int i = 0;i < n-1;i++){
            from[i] = ni()-1;
            to[i] = ni()-1;
        }
        int[][] g = packU(n, from, to);
        int[][] pars = parents3(g, 0);
        int[] par = pars[0], dep = pars[2];
        int[][] spar = logstepParents(par);
        int[][] rights = makeRights(g, par, 0);
        int[] iord = rights[1], right = rights[2];

        long[][][] ft = new long[P][2][n+1];

        int Q = ni();
        for(int q = 0;q < Q;q++){
            char type = nc();
            if(type == 'U'){
                int r = ni()-1;
                int t = ni()-1;
                long a = nl();
                long b = nl();
                int[][] qs = null;
                if(r == t){
                    qs = new int[][]{
                            {0, n-1, 1}
                    };
                }else if(dep[r] > dep[t] && ancestor(r, dep[r]-dep[t], spar) == t){
                    int ct = ancestor(r, dep[r]-dep[t]-1, spar);
                    qs = new int[][]{
                            {0, n-1, 1},
                            {iord[ct], right[iord[ct]], -1},
                    };
                }else{
                    qs = new int[][]{
                            {iord[t], right[iord[t]], 1}
                    };
                }

                for(int j = 0;j < P;j++){
                    int mod = mods[j];
                    long v = pow(a, b, mod);
                    v += pow(a+1, b, mod);
                    if(v >= mod)v -= mod;
                    v += pow(b+1, a, mod);
                    if(v >= mod)v -= mod;
                    for(int[] z : qs){
                        addRangeFenwick(ft[j][0], ft[j][1], z[0], z[1], v*z[2]);
                    }
                }
            }else if(type == 'R'){
                int r = ni()-1;
                int t = ni()-1;
                int m = ni();
                if(m == 1){
                    out.println(0);
                }else{
                    int[][] qs = null;
                    if(r == t){
                        qs = new int[][]{
                                {0, n-1, 1}
                        };
                    }else if(dep[r] > dep[t] && ancestor(r, dep[r]-dep[t], spar) == t){
                        int ct = ancestor(r, dep[r]-dep[t]-1, spar);
                        qs = new int[][]{
                                {0, n-1, 1},
                                {iord[ct], right[iord[ct]], -1},
                        };
                    }else{
                        qs = new int[][]{
                                {iord[t], right[iord[t]], 1}
                        };
                    }
                    long[] divs = new long[P];
                    long[] vals = new long[P];
                    for(int j = 0;j < P;j++){
                        int mod = gcd(mods[j], m);
                        long ret = 0;
                        for(int[] z : qs){
                            ret += sumRangeFenwick(ft[j][0], ft[j][1], z[1])*z[2];
                            ret -= sumRangeFenwick(ft[j][0], ft[j][1], z[0]-1)*z[2];
                        }
                        ret %= mod;
                        if(ret < 0)ret += mod;
                        divs[j] = mod;
                        vals[j] = ret;
                    }
                    out.println(crt(divs, vals));
                }
            }
        }

    }

    public static int gcd(int a, int b) {
        while (b > 0){
            int c = a;
            a = b;
            b = c % b;
        }
        return a;
    }

    public static long[] exGCD(long a, long b)
    {
        if(a == 0 || b == 0)return null;
        int as = Long.signum(a);
        int bs = Long.signum(b);
        a = Math.abs(a); b = Math.abs(b);
        long p = 1, q = 0, r = 0, s = 1;
        while(b > 0){
            long c = a / b;
            long d;
            d = a; a = b; b = d % b;
            d = p; p = q; q = d - c * q;
            d = r; r = s; s = d - c * s;
        }
        return new long[]{a, p * as, r * bs};
    }

    public static long crt(final long[] divs, final long[] mods)
    {
        long div = divs[0];
        long mod = mods[0];
        for(int i = 1;i < divs.length;i++){
            long[] apr = exGCD(div, divs[i]);
            if((mods[i] - mod) % apr[0] != 0)return -1;
            long ndiv = div / apr[0] * divs[i];
            long da = div / apr[0];
            long nmod = (mul(mul(apr[1], mods[i]-mod, ndiv), da, ndiv)+mod)%ndiv;
            if(nmod < 0)nmod += ndiv;
            div = ndiv;
            mod = nmod;
        }
        return mod;
    }

    public static long mul(long a, long b, long mod)
    {
        a %= mod; if(a < 0)a += mod;
        b %= mod; if(b < 0)b += mod;
        long ret = 0;
        int x = 63-Long.numberOfLeadingZeros(b);
        for(;x >= 0;x--){
            ret += ret;
            if(ret >= mod)ret -= mod;
            if(b<<63-x<0){
                ret += a;
                if(ret >= mod)ret -= mod;
            }
        }
        return ret;
    }

    public static void addRangeFenwick(long[] ft0, long[] ft1, int i, long v)
    {
        addFenwick(ft1, i+1, -v);
        addFenwick(ft1, 0, v);
        addFenwick(ft0, i+1, v*(i+1));
    }

    public static void addRangeFenwick(long[] ft0, long[] ft1, int a, int b, long v)
    {
        if(a <= b){
            addFenwick(ft1, b+1, -v);
            addFenwick(ft0, b+1, v*(b+1));
            addFenwick(ft1, a, v);
            addFenwick(ft0, a, -v*a);
        }
    }

    public static long sumRangeFenwick(long[] ft0, long[] ft1, int i)
    {
        return sumFenwick(ft1, i) * (i+1) + sumFenwick(ft0, i);
    }

    public static long[] restoreRangeFenwick(long[] ft0, long[] ft1)
    {
        int n = ft0.length-1;
        long[] ret = new long[n];
        for(int i = 0;i < n;i++)ret[i] = sumRangeFenwick(ft0, ft1, i);
        for(int i = n-1;i >= 1;i--)ret[i] -= ret[i-1];
        return ret;
    }

    public static long sumFenwick(long[] ft, int i)
    {
        long sum = 0;
        for(i++;i > 0;i -= i&-i)sum += ft[i];
        return sum;
    }

    public static void addFenwick(long[] ft, int i, long v)
    {
        if(v == 0)return;
        int n = ft.length;
        for(i++;i < n;i += i&-i)ft[i] += v;
    }

    public static long pow(long a, long n, long mod) {
        a %= mod;
        long ret = 1;
        int x = 63 - Long.numberOfLeadingZeros(n);
        for(;x >= 0;x--){
            ret = ret * ret % mod;
            if(n << 63 - x < 0)
                ret = ret * a % mod;
        }
        return ret;
    }

    public static int[] sieveEratosthenes(int n) {
        if(n <= 32){
            int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
            for(int i = 0;i < primes.length;i++){
                if(n < primes[i]){
                    return Arrays.copyOf(primes, i);
                }
            }
            return primes;
        }

        int u = n + 32;
        double lu = Math.log(u);
        int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
        ret[0] = 2;
        int pos = 1;

        int[] isp = new int[(n + 1) / 32 / 2 + 1];
        int sup = (n + 1) / 32 / 2 + 1;

        int[] tprimes = { 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
        for(int tp : tprimes){
            ret[pos++] = tp;
            int[] ptn = new int[tp];
            for(int i = (tp - 3) / 2;i < tp << 5;i += tp)
                ptn[i >> 5] |= 1 << (i & 31);
            for(int i = 0;i < tp;i++){
                for(int j = i;j < sup;j += tp)
                    isp[j] |= ptn[i];
            }
        }

        // 3,5,7
        // 2x+3=n
        int[] magic = { 0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4,
                13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14 };
        int h = n / 2;
        for(int i = 0;i < sup;i++){
            for(int j = ~isp[i];j != 0;j &= j - 1){
                int pp = i << 5 | magic[(j & -j) * 0x076be629 >>> 27];
                int p = 2 * pp + 3;
                if(p > n)
                    break;
                ret[pos++] = p;
                for(int q = pp;q <= h;q += p)
                    isp[q >> 5] |= 1 << (q & 31);
            }
        }

        return Arrays.copyOf(ret, pos);
    }

    public static int lca2(int a, int b, int[][] spar, int[] depth) {
        if(depth[a] < depth[b]){
            b = ancestor(b, depth[b] - depth[a], spar);
        }else if(depth[a] > depth[b]){
            a = ancestor(a, depth[a] - depth[b], spar);
        }

        if(a == b)
            return a;
        int sa = a, sb = b;
        for(int low = 0, high = depth[a], t = Integer.highestOneBit(high), k = Integer
                .numberOfTrailingZeros(t);t > 0;t >>>= 1, k--){
            if((low ^ high) >= t){
                if(spar[k][sa] != spar[k][sb]){
                    low |= t;
                    sa = spar[k][sa];
                    sb = spar[k][sb];
                }else{
                    high = low | t - 1;
                }
            }
        }
        return spar[0][sa];
    }

    protected static int ancestor(int a, int m, int[][] spar) {
        for(int i = 0;m > 0 && a != -1;m >>>= 1, i++){
            if((m & 1) == 1)
                a = spar[i][a];
        }
        return a;
    }

    public static int[][] logstepParents(int[] par) {
        int n = par.length;
        int m = Integer.numberOfTrailingZeros(Integer.highestOneBit(n - 1)) + 1;
        int[][] pars = new int[m][n];
        pars[0] = par;
        for(int j = 1;j < m;j++){
            for(int i = 0;i < n;i++){
                pars[j][i] = pars[j - 1][i] == -1 ? -1
                        : pars[j - 1][pars[j - 1][i]];
            }
        }
        return pars;
    }

    public static int[][] parents3(int[][] g, int root) {
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);

        int[] depth = new int[n];
        depth[0] = 0;

        int[] q = new int[n];
        q[0] = root;
        for(int p = 0, r = 1;p < r;p++){
            int cur = q[p];
            for(int nex : g[cur]){
                if(par[cur] != nex){
                    q[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][] { par, q, depth };
    }

    public static int[] sortByPreorder(int[][] g, int root){
        int n = g.length;
        int[] stack = new int[n];
        int[] ord = new int[n];
        BitSet ved = new BitSet();
        stack[0] = root;
        int p = 1;
        int r = 0;
        ved.set(root);
        while(p > 0){
            int cur = stack[p-1];
            ord[r++] = cur;
            p--;
            for(int e : g[cur]){
                if(!ved.get(e)){
                    stack[p++] = e;
                    ved.set(e);
                }
            }
        }
        return ord;
    }

    public static int[][] makeRights(int[][] g, int[] par, int root)
    {
        int n = g.length;
        int[] ord = sortByPreorder(g, root);
        int[] iord = new int[n];
        for(int i = 0;i < n;i++)iord[ord[i]] = i;

        int[] right = new int[n];
        for(int i = n-1;i >= 0;i--){
            int v = i;
            for(int e : g[ord[i]]){
                if(e != par[ord[i]]){
                    v = Math.max(v, right[iord[e]]);
                }
            }
            right[i] = v;
        }
        return new int[][]{ord, iord, right};
    }

    static int[][] packU(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for(int f : from)
            p[f]++;
        for(int t : to)
            p[t]++;
        for(int i = 0;i < n;i++)
            g[i] = new int[p[i]];
        for(int i = 0;i < from.length;i++){
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    public static void main(String[] args) throws Exception
    {
        long S = System.currentTimeMillis();
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        solve();
        out.flush();
        long G = System.currentTimeMillis();
        tr(G-S+"ms");
    }

    private static boolean eof()
    {
        if(lenbuf == -1)return true;
        int lptr = ptrbuf;
        while(lptr < lenbuf)if(!isSpaceChar(inbuf[lptr++]))return false;

        try {
            is.mark(1000);
            while(true){
                int b = is.read();
                if(b == -1){
                    is.reset();
                    return true;
                }else if(!isSpaceChar(b)){
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

    private static int readByte()
    {
        if(lenbuf == -1)throw new InputMismatchException();
        if(ptrbuf >= lenbuf){
            ptrbuf = 0;
            try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
            if(lenbuf <= 0)return -1;
        }
        return inbuf[ptrbuf++];
    }

    private static boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
    private static int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }

    private static double nd() { return Double.parseDouble(ns()); }
    private static char nc() { return (char)skip(); }

    private static String ns()
    {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private static char[] ns(int n)
    {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while(p < n && !(isSpaceChar(b))){
            buf[p++] = (char)b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private static char[][] nm(int n, int m)
    {
        char[][] map = new char[n][];
        for(int i = 0;i < n;i++)map[i] = ns(m);
        return map;
    }

    private static int[] na(int n)
    {
        int[] a = new int[n];
        for(int i = 0;i < n;i++)a[i] = ni();
        return a;
    }

    private static int ni()
    {
        int num = 0, b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){
            minus = true;
            b = readByte();
        }

        while(true){
            if(b >= '0' && b <= '9'){
                num = num * 10 + (b - '0');
            }else{
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static long nl()
    {
        long num = 0;
        int b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){
            minus = true;
            b = readByte();
        }

        while(true){
            if(b >= '0' && b <= '9'){
                num = num * 10 + (b - '0');
            }else{
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}