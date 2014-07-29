package weekly.week7.day5;

/**
 * Created by yuantian on 7/28/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class HelixWithSplayTree {
    static class Node {
        Node left, right, parent;
        int size, id;
        boolean rev;

        public Node(int id) {
            this.id = id;
            size = 1;
            left = right = parent = null;
            rev = false;
        }

        void recount() {
            size = 1 +
                    (left == null ? 0 : left.size) +
                    (right == null ? 0 : right.size);
        }

        void release() {
            if (rev) {
                Node temp = left;
                left = right;
                right = temp;
                if (left != null) left.rev ^= true;
                if (right != null) right.rev ^= true;
                rev = false;
            }
        }
    }

    static Node insert(Node root, int id) {
        //out.println("insert " + id);
        if (root == null) {
            return new Node(id);
        }
        if (id == root.id)
            return root;
        if (id < root.id) {
            if (root.left == null) {
                root.left = new Node(id);
                root.left.parent = root;
            } else {
                insert(root.left, id);
            }
        } else {
            if (root.right == null) {
                root.right = new Node(id);
                root.right.parent = root;
            } else {
                insert(root.right, id);
            }
        }
        root.size++;
        return root;
    }

    static Node generateTree(int n) {
        int[] ran = new int[n];
        for(int i = 0; i < n; i++) {
            ran[i] = i+1;
        }

        Random r = new Random();
        Node root = null;
        for(int i = n-1; i >= 0; i--) {
            int x = r.nextInt(i+1);
            root = insert(root, ran[x]);
            ran[x] = ran[i];
        }
        return root;
    }

    static void go() {
        int n = in.nextInt();
        generateTree(n);
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