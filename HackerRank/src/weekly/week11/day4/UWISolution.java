package weekly.week11.day4;

/**
 * Created by yuantian on 10/13/14.
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class UWISolution {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";

    static void solve()
    {
        int n = ni(), t = ni();
        int[] c = na(n);
        int[][] as = new int[t][];
        int[][] bs = new int[t][];
        for(int i = 0;i < t;i++){
            int u = ni();
            as[i] = na(u);
            u = ni();
            bs[i] = na(u);
        }

        int F = 50;

        int src = n+2*F*t, sink = src+1;
        List<Edge> es = new ArrayList<Edge>();
        for(int i = 0;i < n;i++){
            es.add(new Edge(src, i, c[i]));
            es.add(new Edge(i, sink, 1));
        }
        for(int i = 0;i < t;i++){
            for(int j = 0;j < as[i].length;j++){
                for(int k = 0;k < bs[i].length;k++){
                    es.add(new Edge(n+2*F*i+j, n+2*F*i+F+k, 1));
                }
            }
            for(int j = 0;j < as[i].length;j++){
                es.add(new Edge(as[i][j]-1, n+2*F*i+j, 1));
            }
            for(int j = 0;j < bs[i].length;j++){
                es.add(new Edge(n+2*F*i+F+j, bs[i][j]-1, 1));
            }
        }
        out.println(maximumFlowDinicNoRec(compileWD(sink+1, es), src, sink));
    }

    public static class Edge
    {
        public int from, to;
        public int capacity;
        public int flow;
        public Edge complement;
        // public int iniflow;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }
    }

    public static Edge[][] compileWD(int n, List<Edge> edges)
    {
        Edge[][] g = new Edge[n][];
        // cloning
        for(int i = edges.size()-1;i >= 0;i--){
            Edge origin = edges.get(i);
            Edge clone = new Edge(origin.to, origin.from, origin.capacity);
            clone.flow = origin.capacity;
            clone.complement = origin;
            origin.complement = clone;
            edges.add(clone);
        }

        int[] p = new int[n];
        for(Edge e : edges)p[e.from]++;
        for(int i = 0;i < n;i++)g[i] = new Edge[p[i]];
        for(Edge e : edges)g[e.from][--p[e.from]] = e;
        return g;
    }

    public static Edge[][] compileWU(int n, List<Edge> edges)
    {
        Edge[][] g = new Edge[n][];
        // cloning
        for(int i = edges.size()-1;i >= 0;i--){
            Edge origin = edges.get(i);
            Edge clone = new Edge(origin.to, origin.from, origin.capacity*2);
            origin.flow = origin.capacity;
            clone.flow = origin.capacity;
            clone.complement = origin;
            origin.complement = clone;
            origin.capacity *= 2;
            edges.add(clone);
        }

        int[] p = new int[n];
        for(Edge e : edges)p[e.from]++;
        for(int i = 0;i < n;i++)g[i] = new Edge[p[i]];
        for(Edge e : edges)g[e.from][--p[e.from]] = e;
        return g;
    }

    public static long maximumFlowDinic(Edge[][] g, int source, int sink)
    {
        int n = g.length;
        int[] d = new int[n];
        int[] q = new int[n];
        long ret = 0;
        while(true){
            Arrays.fill(d, -1);
            q[0] = source;
            int r = 1;
            d[source] = 0;

            for(int v = 0;v < r;v++){
                int cur = q[v];
                for(Edge ne : g[cur]){
                    if(d[ne.to] == -1 && ne.capacity - ne.flow > 0) {
                        q[r++] = ne.to;
                        d[ne.to] = d[cur] + 1;
                    }
                }
            }
            if(d[sink] == -1)break;
            int[] sp = new int[n];
            for(int i = 0;i < n;i++)sp[i] = g[i].length - 1;
            ret += dfsDinic(d, g, sp, source, sink, Long.MAX_VALUE);
        }

        return ret;
    }

    private static long dfsDinic(int[] d, Edge[][] g, int[] sp, int cur, int sink, long min)
    {
        if(cur == sink)return min;
        long left = min;
        for(int i = sp[cur];i >= 0;i--){
            Edge ne = g[cur][i];
            if(d[ne.to] == d[cur]+1 && ne.capacity - ne.flow > 0){
                long fl = dfsDinic(d, g, sp, ne.to, sink, Math.min(left, ne.capacity - ne.flow));
                if(fl > 0){
                    left -= fl;
                    ne.flow += fl;
                    ne.complement.flow -= fl;
                    if(left == 0){
                        sp[cur] = i;
                        return min;
                    }
                }
            }
        }
        sp[cur] = -1;
        return min-left;
    }

    public static long maximumFlowDinicNoRec(Edge[][] g, int source, int sink)
    {
        int n = g.length;
        int[] d = new int[n]; // distance
        int[] q = new int[n]; // queue for BFS
        long ret = 0;
        int[] stack = new int[n];
        long[] lefts = new long[n]; // left to flow
        long[] parflow = new long[n];
        int[] probe = new int[n]; // search pointer
        while(true){
            // BFS
            Arrays.fill(d, -1);
            q[0] = source;
            int r = 1;
            d[source] = 0;
            for(int v = 0;v < r;v++){
                int cur = q[v];
                for(Edge ne : g[cur]){
                    if(d[ne.to] == -1 && ne.capacity - ne.flow > 0) {
                        q[r++] = ne.to;
                        d[ne.to] = d[cur] + 1;
                    }
                }
            }
            if(d[sink] == -1)break;

            // DFS
            for(int i = 0;i < n;i++)probe[i] = g[i].length;
            int sp = 0;
            stack[sp] = source;
            lefts[sp] = Long.MAX_VALUE;
            parflow[sp] = 0;
            sp++;
            long delta = 0;
            boolean down = true;
            while(sp > 0){
                int cur = stack[sp-1];
                long fl = lefts[sp-1];
                if(cur == sink){
                    delta += fl;
                    parflow[sp-1] = fl;
                    sp--;
                    down = false;
                    continue;
                }
                if(!down && parflow[sp] > 0){
                    lefts[sp-1] -= parflow[sp];
                    fl = lefts[sp-1];
                    Edge ne = g[cur][probe[cur]];
                    ne.flow += parflow[sp];
                    ne.complement.flow -= parflow[sp];
                    parflow[sp-1] += parflow[sp];
                }
                if(fl > 0 && probe[cur] > 0){
                    down = true;
                    Edge ne = g[cur][--probe[cur]];
                    if(d[ne.to] == d[cur]+1 && ne.capacity - ne.flow > 0){
                        lefts[sp] = Math.min(lefts[sp-1], ne.capacity - ne.flow);
                        stack[sp] = ne.to;
                        parflow[sp] = 0;
                        sp++;
                    }
                }else{
                    down = false;
                    sp--;
                }
            }
            ret += delta;
        }
        return ret;
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
