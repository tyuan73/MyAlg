package weekly.week9.day5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class WUIsSolution {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";

    static void solve()
    {
        int n = ni(), m = ni();
        int[][] es = new int[m][];
        Polynomial[][] M = new Polynomial[n-1][n-1];
        for(int i = 0;i < n-1;i++){
            for(int j = 0;j < n-1;j++){
                M[i][j] = Polynomial.build(0);
            }
        }

        FB[][] A = new FB[n-1][n-1];
        for(int i = 0;i < n-1;i++){
            for(int j = 0;j < n-1;j++){
                A[i][j] = new FB();
            }
        }
        for(int i = 0;i < m;i++){
            int from = ni()-1, to = ni()-1, w = ni();
            es[i] = new int[]{from, to, w};
            if(from != to){
                Polynomial t = Polynomial.build(new Polynomial.Term(1L, w));
                Polynomial tn = Polynomial.build(new Polynomial.Term(-1L, w));
                if(from < n-1)M[from][from] = Polynomial.add(M[from][from], t);
                if(to < n-1)M[to][to] = Polynomial.add(M[to][to], t);
                if(from < n-1 && to < n-1){
                    M[from][to] = Polynomial.add(M[from][to], tn);
                    M[to][from] = Polynomial.add(M[to][from], tn);
                }

                if(from < n-1)A[from][from].add(new FB(1));
                if(to < n-1)A[to][to].add(new FB(1));
                if(from < n-1 && to < n-1){
                    A[from][to].sub(new FB(1));
                    A[to][from].sub(new FB(1));
                }
            }
        }
        FB all = det(A);

        Arrays.sort(es, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });
        int[] from = new int[n-1];
        int[] to = new int[n-1];
        int p = 0;
        DJSet ds = new DJSet(n);
        for(int[] e : es){
            if(!ds.union(e[0], e[1])){
                from[p] = e[0];
                to[p] = e[1];
                p++;
            }
        }

        int[][] g = packU(n, from, to);
        int[][] pars = parents3(g, n-1);
        int[] par = pars[0], ord = pars[1];
        for(int i = n-1;i >= 1;i--){
            int cur = ord[i];
            if(par[cur] == n-1)continue;
//			tr(cur, par[cur]);
            for(int j = 0;j < n-1;j++){
                M[j][par[cur]] = Polynomial.add(M[j][par[cur]], M[j][cur]);
            }
        }

//		for(int i = 0;i < n-1;i++){
//			tr(M[i]);
//		}

        FB[][] Z = new FB[n-1][n-1];
        for(int i = 0;i < n-1;i++){
            for(int j = 0;j < n-1;j++){
                Z[i][j] = new FB();
            }
        }
        for(int j = 0;j < n-1;j++){
            long lowest = Long.MAX_VALUE;
            for(int i = 0;i < n-1;i++){
                M[i][j].simplify();
                if(M[i][j].terms.length > 0){
                    lowest = Math.min(lowest, M[i][j].terms[M[i][j].terms.length-1].e);
                }
            }
            for(int i = 0;i < n-1;i++){
                if(M[i][j].terms.length > 0){
                    if(M[i][j].terms[M[i][j].terms.length-1].e == lowest){
                        Z[i][j] = new FB(M[i][j].terms[M[i][j].terms.length-1].co);
                    }
                }
            }
        }

//		tr(Z);
        FB num = det(Z);

        FB ret = num.div(all);
        out.println(ret.num + "/" + ret.den);
    }

    public static FB det(FB[][] M)
    {
        int n = M.length;

        FB[][] lu = LU(M);
        FB ret = new FB(1);
        for(int i = 0;i < n;i++){
            ret.mul(lu[i][i]);
        }
        return ret;
    }

    public static FB[][] LU(FB[][] mat)
    {
        int n = mat.length;

        FB[][] lu = new FB[n][];
        for(int i = 0;i < n;i++){
            lu[i] = Arrays.copyOf(mat[i], n);
        }

        for(int i = 0;i < n;i++){
            int z = i;
            for(;z < n && lu[z][i].num.signum() == 0;z++);
            if(z == n)continue;
            FB[] d = lu[z]; lu[z] = lu[i]; lu[i] = d;

            for(int j = i+1;j < n;j++){
                lu[j][i] = FB.div(lu[j][i], lu[i][i]);
                for(int k = i+1;k < n;k++){
                    lu[j][k] = FB.sub(lu[j][k], FB.mul(lu[j][i], lu[i][k]));
                }
            }
        }

        return lu;
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

    public static class FB implements Comparable<FB> {
        public BigInteger num, den;

        public FB(){ this.num = BigInteger.ZERO; this.den = BigInteger.ONE; }
        public FB(long num) { this.num = BigInteger.valueOf(num); this.den = BigInteger.ONE; }
        public FB(long num, long den) { this.num = BigInteger.valueOf(num); this.den = BigInteger.valueOf(den); reduce(); }
        public FB(BigInteger num) {	this.num = num; this.den = BigInteger.ONE;}
        public FB(BigInteger num, BigInteger den) {	this.num = num;	this.den = den;	reduce();}

        public FB reduce()
        {
            if(den.signum() == 0) {
            }else if(num.signum() == 0) {
                den = BigInteger.ONE;
            }else {
                if(den.signum() < 0) {
                    num = num.negate();
                    den = den.negate();
                }
                BigInteger g = num.gcd(den);
                num = num.divide(g);
                den = den.divide(g);
            }
            return this;
        }

        public static FB add(FB a, FB b){ return new FB(a.num.multiply(b.den).add(a.den.multiply(b.num)), a.den.multiply(b.den)); }
        public static FB sub(FB a, FB b){ return new FB(a.num.multiply(b.den).subtract(a.den.multiply(b.num)), a.den.multiply(b.den)); }
        public static FB mul(FB a, FB b){ return new FB(a.num.multiply(b.num), a.den.multiply(b.den)); }
        public static FB div(FB a, FB b){ return new FB(a.num.multiply(b.den), a.den.multiply(b.num)); }
        public static FB inv(FB a) { return new FB(a.den, a.num); }

        public FB add(FB b){ num = num.multiply(b.den).add(den.multiply(b.num)); den = den.multiply(b.den); return reduce(); }
        public FB sub(FB b){ num = num.multiply(b.den).subtract(den.multiply(b.num)); den = den.multiply(b.den); return reduce(); }
        public FB mul(FB b){ num = num.multiply(b.num); den = den.multiply(b.den); return reduce(); }
        public FB div(FB b){ num = num.multiply(b.den); den = den.multiply(b.num); return reduce(); }
        public FB inv() { BigInteger d = num; num = den; den = d; return reduce(); }

        public String toString() { return num.toString() + "/" + den.toString(); }
        public String toStringSimple() { return den.equals(BigInteger.ONE) ? num.toString() : num.toString() + "/" + den.toString(); }

        public int compareTo(FB f) { return num.multiply(f.den).compareTo(f.num.multiply(den)); }

        public boolean simpleEquals(FB f) {	return num.equals(f.num) && den.equals(f.den); }

        public boolean equals(Object o)
        {
            if(o == null)return false;
            FB f = (FB) o;
            return num.multiply(f.den).equals(f.num.multiply(den));
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
        return new int[][] { par, q, depth };
    }

    public static class DJSet {
        public int[] upper;

        public DJSet(int n) {
            upper = new int[n];
            Arrays.fill(upper, -1);
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

    public static class Polynomial {
        // polynomial = sum co_i^e_i
        public Term[] terms;

        public static class Term
        {
            public long co;
            public int e;

            public Term(long co, int e)
            {
                this.co = co;
                this.e = e;
            }

            public Term clone()
            {
                return new Term(this.co, this.e);
            }
        }

        static Polynomial build(Term... t)
        {
            Polynomial p = new Polynomial();
            p.terms = t;
            return p.simplify();
        }

        // x^0, x^1, ...
        static Polynomial build(int... co)
        {
            Polynomial p = new Polynomial();
            int n = co.length;
            p.terms = new Term[n];
            for(int i = 0;i < n;i++){
                p.terms[i] = new Term(co[i], i);
            }
            return p.simplify();
        }

        long substitute(int v)
        {
            long ret = 0;
            for(Term t : terms){
                long lret = t.co;
                for(int j = 0;j < t.e;j++){
                    lret *= v;
                }
                ret += lret;
            }
            return ret;
        }

        static Polynomial add(Polynomial... a)
        {
            Polynomial p = new Polynomial();
            int len = 0;
            for(Polynomial spm : a)len += spm.terms.length;
            p.terms = new Term[len];
            int q = 0;
            for(Polynomial spm : a){
                for(Term t : spm.terms){
                    p.terms[q++] = t.clone();
                }
            }
            return p.simplify();
        }

        static Polynomial mul(Polynomial a, long v)
        {
            Polynomial p = new Polynomial();
            int m = a.terms.length;
            p.terms = new Term[m];
            for(int i = 0;i < m;i++){
                p.terms[i] = new Term(a.terms[i].co*v, a.terms[i].e);
            }
            return p.simplify();
        }

        Polynomial mul(long v)
        {
            for(Term t : terms)t.co *= v;
            return v == 0 ? simplify() : this;
        }

        static Polynomial mul(Polynomial a, Polynomial b)
        {
            Polynomial p = new Polynomial();
            int m = a.terms.length, n = b.terms.length;
            p.terms = new Term[m*n];
            for(int i = 0;i < m;i++){
                for(int j = 0;j < n;j++){
                    Term t = new Term(
                            a.terms[i].co * b.terms[j].co,
                            a.terms[i].e + b.terms[j].e
                    );
                    p.terms[i*n+j] = t;
                }
            }
            return p.simplify();
        }

        static Polynomial pow(Polynomial a, int n)
        {
            if(a.terms.length == 0)return a;
            Polynomial ret = new Polynomial();
            ret.terms = new Term[]{new Term(1L, 0)};
            int x = 63-Long.numberOfLeadingZeros(n);
            for(;x >= 0;x--){
                ret = mul(ret, ret);
                if(n<<63-x<0)ret = mul(ret, a);
            }
            return ret;
        }

        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for(Term t : terms){
                if(t.co >= 0 && !first)sb.append('+');
                first = false;
                if(t.co == -1 || t.co == 1){
                    if(t.e == 0){
                        sb.append(t.co);
                    }else if(t.co == -1){
                        sb.append('-');
                    }
                }else{
                    sb.append(t.co);
                }
                if(t.e > 0){
                    sb.append('x');
                    if(t.e >= 2){
                        sb.append("^" + t.e);
                    }
                }
            }
            return sb.toString();
        }

        Polynomial simplify()
        {
            if(terms.length == 0)return this;
            Arrays.sort(terms, new Comparator<Term>() {
                public int compare(Term a, Term b) {
                    return -(a.e-b.e);
                }
            });
            int n = terms.length;
            int p = 1;
            for(int i = 1;i < n;i++){
                if(terms[i].e == terms[p-1].e){
                    terms[p-1].co += terms[i].co;
                }else{
                    if(terms[p-1].co == 0L)p--;
                    terms[p++] = terms[i];
                }
            }
            if(terms[p-1].co == 0L)p--;

            if(terms.length != p){
                terms = Arrays.copyOf(terms, p);
            }
            return this;
        }
    }

    public static void main(String[] args) throws Exception
    {
//		int n = 5, m = 100;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(n + " ");
//		sb.append(m + " ");
//		for (int i = 0; i < m; i++){
//			while(true){
//				int f = gen.nextInt(n)+1;
//				int t = gen.nextInt(n)+1;
//				if(f != t){
//					sb.append(f + " " + t + " " + (gen.nextInt(2)+1) + " ");
//					break;
//				}
//			}
//		}
//		INPUT = sb.toString();

//		int n = 14, m = n*(n-1)/2;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(n + " ");
//		sb.append(m + " ");
//		for (int i = 0; i < n; i++){
//			for(int j = i+1;j < n;j++){
//				sb.append((i+1) + " " + (j+1) + " " + (gen.nextInt(2)+1) + " ");
//			}
//		}
//		INPUT = sb.toString();

//		int n = 100, m = 100;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(n + " ");
//		sb.append(m + " ");
//		sb.append((1) + " " + (n) + " " + (gen.nextInt(2)+1) + " ");
//		for (int i = 0; i < n; i++) {
//			sb.append((i+1) + " " + (i+2) + " " + (gen.nextInt(2)+1) + " ");
//		}
//		INPUT = sb.toString();
//
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
