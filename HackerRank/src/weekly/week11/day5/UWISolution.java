package weekly.week11.day5;

/**
 * Created by yuantian on 10/13/14.
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;

public class UWISolution {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";

    static void solve()
    {
        int n = ni(), Q = ni();
        Node[] root = new Node[2];
        {
            int[] a = na(n);
            int[] b = na(n);
            for(int i = n-1;i >= 0;i--){
                root[0] = insert(root[0], 0, new Node(a[i]));
                root[1] = insert(root[1], 0, new Node(b[i]));
            }
        }

        for(int z = 0;z < Q;z++){
            int t = ni();
            if(t == 1){
                // rev
                int id = ni();
                int l = ni()-1, r = ni()-1;
                root[id] = reverse(root[id], l, r+1);
            }else if(t == 2){
                int id = ni();
                int l1 = ni()-1, r1 = ni()-1;
                int l2 = ni()-1, r2 = ni()-1;
                Node[] abcd_e = split(root[id], r2+1);
                Node[] abc_d = split(abcd_e[0], l2);
                Node[] ab_c = split(abc_d[0], r1+1);
                Node[] a_b = split(ab_c[0], l1);

                // adcbe
                root[id] = merge(merge(merge(merge(a_b[0], abc_d[1]), ab_c[1]), a_b[1]), abcd_e[1]);
            }else if(t == 3){
                int l = ni()-1, r = ni()-1;
                Node[] ab_c0 = split(root[0], r+1);
                Node[] a_b0 = split(ab_c0[0], l);
                Node[] ab_c1 = split(root[1], r+1);
                Node[] a_b1 = split(ab_c1[0], l);
                root[0] = merge(merge(a_b0[0], a_b1[1]), ab_c0[1]);
                root[1] = merge(merge(a_b1[0], a_b0[1]), ab_c1[1]);
            }else if(t == 4){
                int l = ni()-1, r = ni()-1;
                double[][] co = new double[r-l+1][];
                for(int i = l;i <= r;i++){
                    co[i-l] = new double[]{get(root[0], i).v, get(root[1], i).v};
                }
                out.printf("%.2f\n", smallestEnclosingCircle(co));
            }else{
                throw new RuntimeException();
            }
        }
    }

    public static double smallestEnclosingCircle(double[][] s)
    {
//		if(s.length == 1)return 0;
//		if(s.length == 2)return Point2D.distance(s[0][0], s[0][1], s[1][0], s[1][1])/2;
        return Math.sqrt(smallestEnclosingCircle(shuffle(s, gen), 0, new int[3], 0)[2]);
    }

    public static <T> T[] shuffle(T[] a, Random gen){ for(int i = 0, n = a.length;i < n;i++){ int ind = gen.nextInt(n-i)+i; T d = a[i]; a[i] = a[ind]; a[ind] = d; } return a; }

    public static double[] diamcircle(double ax, double ay, double bx, double by)
    {
        double rab = (ax-bx)*(ax-bx)+(ay-by)*(ay-by);
        return new double[]{(ax+bx)/2, (ay+by)/2, rab/4};
    }

    public static double[] circumcircle(double ax, double ay, double bx, double by, double cx, double cy)
    {
        if(Math.abs((bx-ax)*(cy-ay)-(by-ay)*(cx-ax)) < 1E-8){
            double rab = (ax-bx)*(ax-bx)+(ay-by)*(ay-by);
            double rac = (ax-cx)*(ax-cx)+(ay-cy)*(ay-cy);
            double rbc = (cx-bx)*(cx-bx)+(cy-by)*(cy-by);
            if(rab > rac && rab > rbc){
                return new double[]{(ax+bx)/2, (ay+by)/2, Math.sqrt(rab)};
            }else if(rac > rab && rac > rbc){
                return new double[]{(ax+cx)/2, (ay+cy)/2, Math.sqrt(rac)};
            }else{
                return new double[]{(bx+cx)/2, (by+cy)/2, Math.sqrt(rbc)};
            }
        }
        double D = 2*(ax*(by-cy)+bx*(cy-ay)+cx*(ay-by));
        double X = ((ax*ax+ay*ay)*(by-cy)+(bx*bx+by*by)*(cy-ay)+(cx*cx+cy*cy)*(ay-by))/D;
        double Y = ((ax*ax+ay*ay)*(cx-bx)+(bx*bx+by*by)*(ax-cx)+(cx*cx+cy*cy)*(bx-ax))/D;
        double R2 = (X-ax)*(X-ax)+(Y-ay)*(Y-ay);
        return new double[]{X, Y, R2};
    }

    public static double[] smallestEnclosingCircle(double[][] P, int pp, int[] R, int rp)
    {
        if(pp == P.length || rp == 3){
            if(rp == 3){
                return circumcircle(
                        P[R[0]][0], P[R[0]][1],
                        P[R[1]][0], P[R[1]][1],
                        P[R[2]][0], P[R[2]][1]
                );
            }else if(rp == 2){
                return diamcircle(
                        P[R[0]][0], P[R[0]][1],
                        P[R[1]][0], P[R[1]][1]
                );
            }else if(rp == 1){
                return new double[]{P[R[0]][0], P[R[0]][1], 0};
            }else{
                return null;
            }
        }

        double[] minc = smallestEnclosingCircle(P, pp+1, R, rp);
        if(minc == null){
            R[rp] = pp;
            minc = smallestEnclosingCircle(P, pp+1, R, rp+1);
        }else{
            double x = P[pp][0], y = P[pp][1];
            if((x-minc[0])*(x-minc[0])+(y-minc[1])*(y-minc[1]) > minc[2] + 1E-9){
                R[rp] = pp;
                minc = smallestEnclosingCircle(P, pp+1, R, rp+1);
            }
        }
        return minc;
    }

    public static Random gen = new Random();

    static public class Node
    {
        public int v; // value
        public long priority;
        public Node left, right;

        public int count;

        public boolean rev;

        public Node(int v)
        {
            this.v = v;
            priority = gen.nextLong();
            update(this);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Node [v=");
            builder.append(v);
            builder.append(", count=");
            builder.append(count);
            builder.append(", rev=");
            builder.append(rev);
            builder.append("]");
            return builder.toString();
        }
    }

    public static Node update(Node a)
    {
        if(a == null)return null;
        a.count = 1;
        if(a.left != null)a.count += a.left.count;
        if(a.right != null)a.count += a.right.count;
        return a;
    }

    public static Node reverse(Node a, int L, int R)
    {
        Node[] MR = split(a, R);
        Node[] LM = split(MR[0], L);
        if(LM[1] != null)LM[1].rev ^= true;
        return merge(merge(LM[0], LM[1]), MR[1]);
    }

    public static void fall(Node a)
    {
        if(a == null)return;
        if(a.rev){
            Node n = a.left; a.left = a.right; a.right = n;
            if(a.left != null)a.left.rev ^= true;
            if(a.right != null)a.right.rev ^= true;
            a.rev = false;
        }

        if(a.left != null){
            update(a.left);
        }
        if(a.right != null){
            update(a.right);
        }
        update(a);
    }

    public static Node disconnect(Node a)
    {
        if(a == null)return null;
        a.left = a.right = null;
        return update(a);
    }

    public static Node merge(Node a, Node b)
    {
        if(b == null)return a;
        if(a == null)return b;
        if(a.priority > b.priority){
            fall(a);
            a.right = merge(a.right, b);
            return update(a);
        }else{
            fall(b);
            b.left = merge(a, b.left);
            return update(b);
        }
    }

    public static int count(Node a)
    {
        return a == null ? 0 : a.count;
    }

    // [0,K),[K,N)
    public static Node[] split(Node a, int K)
    {
        if(a == null)return new Node[]{null, null};
        fall(a);
        if(K <= count(a.left)){
            Node[] s = split(a.left, K);
            a.left = s[1];
            s[1] = update(a);
            return s;
        }else{
            Node[] s = split(a.right, K-count(a.left)-1);
            a.right = s[0];
            s[0] = update(a);
            return s;
        }
    }

    public static Node insert(Node a, int K, Node b)
    {
        if(a == null)return b;
        fall(a);
        if(b.priority < a.priority){
            if(K <= count(a.left)){
                a.left = insert(a.left, K, b);
            }else{
                a.right = insert(a.right, K-count(a.left)-1, b);
            }
            return update(a);
        }else{
            Node[] ch = split(a, K);
            b.left = ch[0]; b.right = ch[1];
            return update(b);
        }
    }

    // delete K-th
    public static Node erase(Node a, int K)
    {
        if(a == null)return null;
        fall(a);
        if(K < count(a.left)){
            a.left = erase(a.left, K);
            return update(a);
        }else if(K == count(a.left)){
            Node aa = merge(a.left, a.right);
            disconnect(a);
            return aa;
        }else{
            a.right = erase(a.right, K-count(a.left)-1);
            return update(a);
        }
    }

    public static Node get(Node a, int K)
    {
        while(a != null){
            fall(a);
            if(K < count(a.left)){
                a = a.left;
            }else if(K == count(a.left)){
                break;
            }else{
                K = K - count(a.left)-1;
                a = a.right;
            }
        }
        return a;
    }

    public static String toString(Node a, String indent)
    {
        if(a == null)return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString(a.left, indent + "  "));
        sb.append(indent).append(a).append("\n");
        sb.append(toString(a.right, indent + "  "));
        return sb.toString();
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
