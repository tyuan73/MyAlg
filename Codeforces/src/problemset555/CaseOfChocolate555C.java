package problemset555;

/**
 * Created by yuantian on 9/1/15.
 */

import java.util.*;
import java.io.*;

public class CaseOfChocolate555C {
    static Random rd = new Random();
    static int n;

    static class Node {
        int from, to, up, left;
        Node lchild, rchild;

        public Node(int a, int b, int c, int d) {
            this.from = a;
            this.to = b;
            this.up = c;
            this.left = d;
            this.lchild = null;
            this.rchild = null;
        }
    }

    static Node find(Node root, int t) {
        if (root == null) return null;
        if (root.from <= t && root.to >= t) return root;
        if (root.from > t) return find(root.lchild, t);
        return find(root.rchild, t);
    }

    static Node insert(Node root, Node node) {
        if (root == null) return node;
        if (root.from > node.to) {
            if (rd.nextInt(2) == 0) {
                node.rchild = root;
                return node;
            }
            root.lchild = insert(root.lchild, node);
        } else {
            if (rd.nextInt(2) == 0) {
                node.lchild = root;
                return node;
            }
            root.rchild = insert(root.rchild, node);
        }
        return root;
    }

    static void split(Node root, int mid, char ch) {
        if (root.from == root.to) {
            root.up = n - mid + 2;
            root.left = root.from + 1;
        } else {
            if (root.from == mid) {
                root.from++;
                if (ch == 'U')
                    root.left = mid + 1;
            } else if (root.to == mid) {
                root.to--;
                if (ch == 'L')
                    root.up = n - mid + 2;
            } else {
                if (root.to - mid > mid - root.from) {
                    if (ch == 'U') {
                        Node node = new Node(root.from, mid - 1, root.up, root.left);
                        root.from = mid + 1;
                        root.left = mid + 1;
                        root.lchild = insert(root.lchild, node);
                    } else {
                        Node node = new Node(root.from, mid - 1, n - mid + 2, root.left);
                        root.from = mid + 1;
                        root.lchild = insert(root.lchild, node);
                    }
                } else {
                    if (ch == 'U') {
                        Node node = new Node(mid + 1, root.to, root.up, mid + 1);
                        root.to = mid - 1;
                        root.rchild = insert(root.rchild, node);
                    } else {
                        Node node = new Node(mid + 1, root.to, root.up, root.left);
                        root.to = mid - 1;
                        root.up = n - mid + 2;
                        root.rchild = insert(root.rchild, node);
                    }
                }
            }
        }
    }

    static void go() {
        n = in.nextInt();
        int q = in.nextInt();

        Node tree = new Node(1, n, 1, 1);

        for (int i = 0; i < q; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            char c = in.nextCharArray(1)[0];
            Node node = find(tree, a);
            if (node == null) {
                out.println(0);
            } else {
                if (c == 'U')
                    out.println(b - node.up + 1);
                else
                    out.println(a - node.left + 1);
                split(node, a, c);
            }
        }
    }

    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);

        go();

        out.close();
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int[] nextIntArray(int len) {
            int[] ret = new int[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextInt();
            return ret;
        }

        public long[] nextLongArray(int len) {
            long[] ret = new long[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextLong();
            return ret;
        }


        public int nextInt() {
            return (int) nextLong();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String nextLine() {
            StringBuilder sb = new StringBuilder(1024);
            int c = read();
            while (!(c == '\n' || c == '\r' || c == -1)) {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder sb = new StringBuilder(1024);
            do {
                sb.append((char) c);
                c = read();
            } while (!isSpaceChar(c));
            return sb.toString();
        }

        public char[] nextCharArray(int n) {
            char[] ca = new char[n];
            for (int i = 0; i < n; i++) {
                int c = read();
                while (isSpaceChar(c))
                    c = read();
                ca[i] = (char) c;
            }
            return ca;
        }

        public static boolean isSpaceChar(int c) {
            switch (c) {
                case -1:
                case ' ':
                case '\n':
                case '\r':
                case '\t':
                    return true;
                default:
                    return false;
            }
        }
    }
}