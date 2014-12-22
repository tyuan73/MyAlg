package weekly.week12.day3;

/**
 * Created by yuantian on 12/22/14.
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

    static void solve() {
        int n = ni();
        int[][] as = new int[n][];
        int[] map = new int[1000001];
        int[] imap = new int[1000001];
        Arrays.fill(map, -1);
        Arrays.fill(imap, -1);
        int lsum = 0;
        for (int i = 0; i < n; i++) {
            int L = ni();
            as[i] = na(L);
            for (int v : as[i]) {
                map[v] = 0;
            }
            lsum += L;
        }

        int p = 0;
        for (int i = 0; i < 1000001; i++) {
            if (map[i] == 0) {
                map[i] = p;
                imap[p] = i;
                p++;
            }
        }

        int[] from = new int[lsum - n];
        int[] to = new int[lsum - n];
        int q = 0;
        for (int[] a : as) {
            for (int i = 0; i < a.length - 1; i++) {
                from[q] = map[a[i]];
                to[q] = map[a[i + 1]];
                q++;
            }
        }
        int[][] g = packD(p, from, to);
        int[] ord = sortTopologically(g);
        for (int i = 0; i < p; i++) {
            if (i > 0) out.print(" ");
            out.print(imap[ord[i]]);
        }
        out.println();
    }

    public static int[] sortTopologically(int[][] g) {
        int n = g.length;
        int[] ec = new int[n];
        for (int i = 0; i < n; i++) {
            for (int to : g[i]) ec[to]++;
        }
        int[] ret = new int[n];
        SimpleMinHeap h = new SimpleMinHeap(n + 1);

        // sources
        for (int i = 0; i < n; i++) {
            if (ec[i] == 0) {
                h.add(i);
            }
        }

        for (int p = 0; p < n; p++) {
            int cur = h.poll();
            ret[p] = cur;
            for (int to : g[cur]) {
                if (--ec[to] == 0) {
                    h.add(to);
                }
            }
        }
        // loop
        for (int i = 0; i < n; i++) {
            if (ec[i] > 0) return null;
        }
        return ret;
    }

    public static class SimpleMinHeap {
        public int[] a;
        public int n;
        public int pos;
        public static final int INF = Integer.MAX_VALUE;

        public SimpleMinHeap(int m) {
            n = m + 1;
            a = new int[n];
            Arrays.fill(a, INF);
            pos = 1;
        }

        public SimpleMinHeap(int[] in) {
            n = in.length + 1;
            if ((n & 1) == 1) n++;
            a = new int[n];
            pos = 1 + in.length;
//			Arrays.fill(a, INF);
            a[0] = a[n - 1] = a[n - 2] = INF;
            System.arraycopy(in, 0, a, 1, in.length);
            for (int t = pos / 2 - 1; t >= 1; t--) {
                for (int c = t; 2 * c < pos; ) {
                    int smaller = a[2 * c] < a[2 * c + 1] ? 2 * c : 2 * c + 1;
                    if (a[smaller] < a[c]) {
                        int d = a[c];
                        a[c] = a[smaller];
                        a[smaller] = d;
                        c = smaller;
                    } else {
                        break;
                    }
                }
            }
        }

        public void add(int x) {
            a[pos++] = x;
            for (int c = pos - 1, p = c >>> 1; p >= 1 && a[c] < a[p]; c >>>= 1, p >>>= 1) {
                int d = a[p];
                a[p] = a[c];
                a[c] = d;
            }
        }

        public int poll() {
            if (pos == 1) return INF;
            pos--;
            int ret = a[1];
            a[1] = a[pos];
            a[pos] = INF;
            for (int c = 1; 2 * c < pos; ) {
                int smaller = a[2 * c] < a[2 * c + 1] ? 2 * c : 2 * c + 1;
                if (a[smaller] < a[c]) {
                    int d = a[c];
                    a[c] = a[smaller];
                    a[smaller] = d;
                    c = smaller;
                } else {
                    break;
                }
            }
            return ret;
        }

        public int min() {
            return a[1];
        }

        public int size() {
            return pos - 1;
        }
    }

    static int[][] packD(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
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

