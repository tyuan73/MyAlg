package weekly.week7.day5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

public class HelixUWISolution {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";

    static void solve() {
        int n = ni(), Q = ni();
        Node root = null;
        Node[] nodes = new Node[n];
        for (int i = n; i >= 1; i--) {
            nodes[i - 1] = new Node(i);
            root = insert(root, 0, nodes[i - 1]);
        }

        for (int q = 0; q < Q; q++) {
            int type = ni();
            if (type == 1) {
                int a = ni() - 1, b = ni() - 1;
                if (a <= b) {
                    root = reverse(root, a, b + 1);
                }/* else {
                    Node[] sp = split(root, b + 1);
                    root = merge(sp[1], sp[0]);
                    root = reverse(root, n - (b + 1 + n - a), n);
                    sp = split(root, n - b - 1);
                    root = merge(sp[1], sp[0]);
                }  */
            } else if (type == 2) {
                int a = ni() - 1;
                int x = index(nodes[a]);
                out.println("element " + (a + 1) + " is at position " + (x + 1));
            } else {
                int a = ni() - 1;
                int x = get(root, a).v;
                out.println("element at position " + (a + 1) + " is " + x);
            }
        }
    }

    public static Random gen = new Random();

    static public class Node {
        public int v; // value
        public long priority;
        public Node left, right, parent;

        public int count;

        public boolean rev;

        public Node(int v) {
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
            builder.append(", parent=");
            builder.append(parent != null ? parent.v : "null");
            builder.append(", rev=");
            builder.append(rev);
            builder.append("]");
            return builder.toString();
        }
    }

    public static Node update(Node a) {
        if (a == null) return null;
        a.count = 1;
        if (a.left != null) a.count += a.left.count;
        if (a.right != null) a.count += a.right.count;
        return a;
    }

    public static Node reverse(Node a, int L, int R) {
        Node[] MR = split(a, R);
        Node[] LM = split(MR[0], L);
        if (LM[1] != null) LM[1].rev ^= true;
        return merge(merge(LM[0], LM[1]), MR[1]);
    }

    public static void fall(Node a) {
        if (a == null) return;
        if (a.rev) {
            Node n = a.left;
            a.left = a.right;
            a.right = n;
            if (a.left != null) a.left.rev ^= true;
            if (a.right != null) a.right.rev ^= true;
            a.rev = false;
        }

        /*
        if (a.left != null) {
            update(a.left);
        }
        if (a.right != null) {
            update(a.right);
        }
        update(a);
        */
    }

    public static Node disconnect(Node a) {
        if (a == null) return null;
        a.left = a.right = a.parent = null;
        return update(a);
    }

    public static Node merge(Node a, Node b) {
        if (b == null) return a;
        if (a == null) return b;
        if (a.priority > b.priority) {
            fall(a);
            setParent(a.right, null);
            setParent(b, null);
            a.right = merge(a.right, b);
            setParent(a.right, a);
            return update(a);
        } else {
            fall(b);
            setParent(a, null);
            setParent(b.left, null);
            b.left = merge(a, b.left);
            setParent(b.left, b);
            return update(b);
        }
    }

    public static int count(Node a) {
        return a == null ? 0 : a.count;
    }

    public static void setParent(Node a, Node par) {
        if (a != null) a.parent = par;
    }

    // [0,K),[K,N)
    public static Node[] split(Node a, int K) {
        if (a == null) return new Node[]{null, null};
        fall(a);
        if (K <= count(a.left)) {
            setParent(a.left, null);
            Node[] s = split(a.left, K);
            a.left = s[1];
            setParent(a.left, a);
            s[1] = update(a);
            return s;
        } else {
            setParent(a.right, null);
            Node[] s = split(a.right, K - count(a.left) - 1);
            a.right = s[0];
            setParent(a.right, a);
            s[0] = update(a);
            return s;
        }
    }

    public static Node insert(Node a, int K, Node b) {
        if (a == null) return b;
        fall(a);
        if (b.priority < a.priority) {
            if (K <= count(a.left)) {
                a.left = insert(a.left, K, b);
                setParent(a.left, a);
            } else {
                a.right = insert(a.right, K - count(a.left) - 1, b);
                setParent(a.right, a);
            }
            return update(a);
        } else {
            Node[] ch = split(a, K);
            b.left = ch[0];
            b.right = ch[1];
            setParent(b.left, b);
            setParent(b.right, b);
            return update(b);
        }
    }

    // delete K-th
    public static Node erase(Node a, int K) {
        if (a == null) return null;
        fall(a);
        if (K < count(a.left)) {
            a.left = erase(a.left, K);
            setParent(a.left, a);
            return update(a);
        } else if (K == count(a.left)) {
            setParent(a.left, null);
            setParent(a.right, null);
            Node aa = merge(a.left, a.right);
            disconnect(a);
            return aa;
        } else {
            a.right = erase(a.right, K - count(a.left) - 1);
            setParent(a.right, a);
            return update(a);
        }
    }

    public static Node get(Node a, int K) {
        while (a != null) {
            fall(a);
            if (K < count(a.left)) {
                a = a.left;
            } else if (K == count(a.left)) {
                break;
            } else {
                K = K - count(a.left) - 1;
                a = a.right;
            }
        }
        return a;
    }

    public static int index(Node a) {
        if (a == null) return -1;
        List<Node> nodes = new ArrayList<Node>();
        for (Node x = a; x != null; x = x.parent) nodes.add(x);
        for (int i = nodes.size() - 1; i >= 0; i--) {
            fall(nodes.get(i));
        }

        int ind = count(a.left);
        while (a != null) {
            Node par = a.parent;
            if (par != null && par.right == a) {
                ind += count(par.left) + 1;
            }
            a = par;
        }
        return ind;
    }

    public static Node[] nodes(Node a) {
        return nodes(a, new Node[a.count], 0, a.count);
    }

    public static Node[] nodes(Node a, Node[] ns, int L, int R) {
        if (a == null) return ns;
        nodes(a.left, ns, L, L + count(a.left));
        ns[L + count(a.left)] = a;
        nodes(a.right, ns, R - count(a.right), R);
        return ns;
    }

    public static String toString(Node a, String indent) {
        if (a == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString(a.left, indent + "  "));
        sb.append(indent).append(a).append("\n");
        sb.append(toString(a.right, indent + "  "));
        return sb.toString();
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