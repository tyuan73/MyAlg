package weekly.week11.day5;

/**
 * Created by yuantian on 10/14/14.
 */

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LewinSolution {
    private static Reader in;
    private static PrintWriter out;

    private static int[][] arr;

    public static void genRandom() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(new File("twoarray.txt")));
        int N = 100000, M = 100000;
        out.println(N + " " + M);

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < N; i++) {
                out.print((int) (Math.random() * 1000000) + " ");
            }
            out.println();
        }
        while (M-- > 0) {
            int cmd = (int) (Math.random() * 3) + 1;
            if (M % 10000 == 0) cmd = 4;
            int id = (int) (Math.random() * 2);
            int l1 = (int) (Math.random() * N) + 1;
            int r1 = (int) (Math.random() * N) + 1;
            int l2 = (int) (Math.random() * N) + 1;
            int r2 = (int) (Math.random() * N) + 1;
            while (l1 > r1 || l2 > r2 || r1 >= l2) {
                l1 = (int) (Math.random() * N) + 1;
                r1 = (int) (Math.random() * N) + 1;
                l2 = (int) (Math.random() * N) + 1;
                r2 = (int) (Math.random() * N) + 1;
            }
            switch (cmd) {
                case 1:
                    out.println(cmd + " " + id + " " + l1 + " " + r1);
                    break;
                case 2:
                    out.println(cmd + " " + id + " " + l1 + " " + r1 + " " + l2 + " " + r2);
                    break;
                case 3:
                    out.println(cmd + " " + l1 + " " + r1);
                    break;
                case 4:
                    out.println(cmd + " " + l1 + " " + r1);
                    break;
            }
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
//    genRandom();
        in = new Reader();
        out = new PrintWriter(System.out, true);
        int N = in.nextInt(), M = in.nextInt();
        arr = new int[2][N + 1];
        for (int j = 0; j < 2; j++)
            for (int i = 1; i <= N; i++) {
                arr[j][i] = in.nextInt();
            }

        initTrees(N);
        while (M-- > 0) {
            int cmd = in.nextInt();
            int id, l1, r1, l2, r2;
            switch (cmd) {
                case 1:
                    id = in.nextInt();
                    l1 = in.nextInt();
                    r1 = in.nextInt();
                    flip(id, l1, r1);
                    break;
                case 2:
                    id = in.nextInt();
                    l1 = in.nextInt();
                    r1 = in.nextInt();
                    l2 = in.nextInt();
                    r2 = in.nextInt();

                    swap1(id, l1, r1, l2, r2);
                    break;
                case 3:
                    l1 = in.nextInt();
                    r1 = in.nextInt();
                    swap2(l1, r1);
                    break;
                case 4:
                    l1 = in.nextInt();
                    r1 = in.nextInt();
                    ArrayList<Point> r = new ArrayList<Point>();
                    for (int i = l1; i <= r1; i++) {
                        r.add(new Point(getElement(0, i), getElement(1, i)));
                    }
                    out.printf("%.2f\n", makeCircle(r));
                    break;
            }
//      for (int j = 0; j < 2; j++) {
//        for (int i = 1; i <= N; i++) {
//          System.out.print(getElement(j, i) + " ");
//        }
//        System.out.println();
//      }
//      System.out.println();
        }

        out.close();
        System.exit(0);
    }

    static class Node {
        int id, size;
        Node left;
        Node right;
        Node parent;
        boolean flip;

        public Node(int id) {
            this.id = id;
            this.size = 1;
            left = null;
            right = null;
            parent = null;
        }

        public String toString() {
            return id + " " + size;
        }
    }

    static void push(Node x) {
        if (!x.flip)
            return;
        x.flip = false;
        Node t = x.left;
        x.left = x.right;
        x.right = t;
        if (x.left != null)
            x.left.flip = !x.left.flip;
        if (x.right != null)
            x.right.flip = !x.right.flip;
        join(x);
    }

    // Whether x is a root of a splay tree
    static boolean isRoot(Node x) {
        return x.parent == null;
    }

    static void connect(Node ch, Node p, boolean leftChild) {
        if (leftChild)
            p.left = ch;
        else
            p.right = ch;
        join(p);
        if (ch != null) {
            ch.parent = p;
        }
    }

    // rotate edge (x, x.parent)
    static void rotate(Node x) {
        Node p = x.parent;
        Node g = p.parent;
        boolean isRootP = isRoot(p);
        boolean leftChildX = (x == p.left);

        Node next = leftChildX ? x.right : x.left;
        connect(next, p, leftChildX);
        connect(p, x, !leftChildX);

        if (!isRootP)
            connect(x, g, p == g.left);
        else
            x.parent = g;
    }

    static void splay(int id, Node x) {
        if (x == null) return;
        while (!isRoot(x)) {
            Node p = x.parent;
            Node g = p.parent;
            if (!isRoot(p))
                push(g);
            push(p);
            push(x);
            if (!isRoot(p))
                rotate((x == p.left) == (p == g.left) ? p : x);
            rotate(x);
        }
        push(x);
        root[id] = x;
    }

    static Node cutLeft(Node x) {
        Node ret = x.left;
        if (ret != null) {
            x.left.parent = null;
            x.left = null;
            join(x);
        }
        return ret;
    }

    static Node cutRight(Node x) {
        Node ret = x.right;
        if (ret != null) {
            x.right.parent = null;
            x.right = null;
            join(x);
        }
        return ret;
    }

    static void join(Node x) {
        x.size = (x.left == null ? 0 : x.left.size) + (x.right == null ? 0 : x.right.size) + 1;
    }

    private static Node getElementAtPosition(int id, Node start, int a) {
        if (a > start.size) {
            System.exit(1);
        }
        Node cur = start;
        while (a > 0) {
            push(cur);
            int sz = (cur.left == null ? 0 : cur.left.size);
            if (a <= sz) {
                cur = cur.left;
                continue;
            }
            a -= sz + 1;
            if (a == 0) break;
            cur = cur.right;
        }
        splay(id, cur);
        return cur;
    }

    private static int getElement(int id, int a) {
        return getElementAtPosition(id, root[id], a).id;
    }

    private static void flip(int id, int a, int b) {
        if (a == b) return;
        Node right = getElementAtPosition(id, root[id], b);
        Node ra = cutRight(right);
        Node left = getElementAtPosition(id, root[id], a);
        Node la = cutLeft(left);
        splay(id, left);
        left.flip = !left.flip;
        push(left);
        connect(ra, left, false);
        splay(id, right);
        connect(la, right, true);
    }

    private static void swap1(int id, int l1, int r1, int l2, int r2) {
        Node right2 = getElementAtPosition(id, root[id], r2);
        Node ra2 = cutRight(right2);
        Node left2 = getElementAtPosition(id, root[id], l2);
        Node la2 = cutLeft(left2);

        splay(id, la2);
        Node right1 = getElementAtPosition(id, root[id], r1);
        Node ra1 = cutRight(right1);
        la2 = ra1;
        Node left1 = getElementAtPosition(id, root[id], l1);
        Node la1 = cutLeft(left1);

        if (r1 + 1 < l2) {
            splay(id, la2);
            splay(id, left1);
            connect(la2, left1, true);
            splay(id, ra2);
            splay(id, right1);
            connect(ra2, right1, false);
            splay(id, la1);
            splay(id, left2);
            connect(la1, left2, true);
            splay(id, ra1);
            splay(id, right2);
            connect(ra1, right2, false);
        } else {
            splay(id, ra2);
            splay(id, right1);
            connect(ra2, right1, false);
            splay(id, la1);
            splay(id, left2);
            connect(la1, left2, true);
            splay(id, right2);
            splay(id, left1);
            connect(right2, left1, true);
        }
    }

    private static void swap2(int l, int r) {
        Node right2 = getElementAtPosition(1, root[1], r);
        Node ra2 = cutRight(right2);
        Node left2 = getElementAtPosition(1, root[1], l);
        Node la2 = cutLeft(left2);

        Node right1 = getElementAtPosition(0, root[0], r);
        Node ra1 = cutRight(right1);
        Node left1 = getElementAtPosition(0, root[0], l);
        Node la1 = cutLeft(left1);

        splay(1, ra2);
        splay(1, right1);
        connect(ra2, right1, false);
        splay(1, la2);
        splay(1, left1);
        connect(la2, left1, true);
        splay(0, ra1);
        splay(0, right2);
        connect(ra1, right2, false);
        splay(0, la1);
        splay(0, left2);
        connect(la1, left2, true);
    }

    private static void initTrees(int n) {
        nodes = new Node[2][n + 1];
        root = new Node[2];
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i <= n; i++)
                nodes[j][i] = new Node(arr[j][i]);
            root[j] = initRec(j, 1, n);
        }
    }

    private static Node initRec(int id, int start, int end) {
        if (start == end) {
            return nodes[id][start];
        }
        int mid = (start + end) >> 1;
        Node x = nodes[id][mid];
        if (start <= mid - 1) connect(initRec(id, start, mid - 1), x, true);
        if (mid + 1 <= end) connect(initRec(id, mid + 1, end), x, false);
        return x;
    }

    private static Node[][] nodes;
    private static Node[] root;


    /*
     * Returns the smallest circle that encloses all the given points. Runs in expected O(n) time,
     * randomized. Note: If 0 points are given, null is returned. If 1 point is given, a circle of
     * radius 0 is returned.
     */
    // Initially: No boundary points known
    public static double makeCircle(List<Point> points) {
        // Clone list to preserve the caller's data, randomize order
        List<Point> shuffled = new ArrayList<Point>(points);
        Collections.shuffle(shuffled, new Random());

        // Progressively add points to circle or recompute circle
        Circle c = null;
        for (int i = 0; i < shuffled.size(); i++) {
            Point p = shuffled.get(i);
            if (c == null || !c.contains(p))
                c = makeCircleOnePoint(shuffled.subList(0, i + 1), p);
        }
        return c.r;
    }


    // One boundary point known
    private static Circle makeCircleOnePoint(List<Point> points, Point p) {
        Circle c = new Circle(p, 0);
        for (int i = 0; i < points.size(); i++) {
            Point q = points.get(i);
            if (!c.contains(q)) {
                if (c.r == 0)
                    c = makeDiameter(p, q);
                else
                    c = makeCircleTwoPoints(points.subList(0, i + 1), p, q);
            }
        }
        return c;
    }


    // Two boundary points known
    private static Circle makeCircleTwoPoints(List<Point> points, Point p, Point q) {
        Circle temp = makeDiameter(p, q);
        if (temp.contains(points))
            return temp;

        Circle left = null;
        Circle right = null;
        for (Point r : points) { // Form a circumcircle with each point
            Point pq = q.subtract(p);
            double cross = pq.cross(r.subtract(p));
            Circle c = makeCircumcircle(p, q, r);
            if (c == null)
                continue;
            else if (cross > 0
                    && (left == null || pq.cross(c.c.subtract(p)) > pq.cross(left.c.subtract(p))))
                left = c;
            else if (cross < 0
                    && (right == null || pq.cross(c.c.subtract(p)) < pq.cross(right.c.subtract(p))))
                right = c;
        }
        return right == null || left != null && left.r <= right.r ? left : right;
    }


    static Circle makeDiameter(Point a, Point b) {
        return new Circle(new Point((a.x + b.x) / 2., (a.y + b.y) / 2.), a.distance(b) / 2.);
    }


    static Circle makeCircumcircle(Point a, Point b, Point c) {
        // Mathematical algorithm from Wikipedia: Circumscribed circle
        double d = (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) * 2;
        if (d == 0)
            return null;
        double x = (a.norm() * (b.y - c.y) + b.norm() * (c.y - a.y) + c.norm() * (a.y - b.y)) / d;
        double y = (a.norm() * (c.x - b.x) + b.norm() * (a.x - c.x) + c.norm() * (b.x - a.x)) / d;
        Point p = new Point(x, y);
        return new Circle(p, p.distance(a));
    }


    static class Circle {

        private static double EPSILON = 1e-12;


        public final Point c; // Center
        public final double r; // Radius


        public Circle(Point c, double r) {
            this.c = c;
            this.r = r;
        }


        public boolean contains(Point p) {
            return c.distance(p) <= r + EPSILON;
        }


        public boolean contains(Collection<Point> ps) {
            for (Point p : ps) {
                if (!contains(p))
                    return false;
            }
            return true;
        }


        public String toString() {
            return String.format("Circle(x=%g, y=%g, r=%g)", c.x, c.y, r);
        }

    }


    static class Point {

        public final double x;
        public final double y;


        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }


        public Point subtract(Point p) {
            return new Point(x - p.x, y - p.y);
        }


        public double distance(Point p) {
            return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
        }


        // Signed area / determinant thing
        public double cross(Point p) {
            return x * p.y - y * p.x;
        }


        // Magnitude squared
        public double norm() {
            return x * x + y * y;
        }


        public String toString() {
            return String.format("Point(%g, %g)", x, y);
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[1024];
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (c == '.')
                while ((c = read()) >= '0' && c <= '9')
                    ret += (c - '0') / (div *= 10);
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

}
