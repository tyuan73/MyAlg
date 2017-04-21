package problemset797;

/**
 * Created by yuantian on 4/20/17.
 */

/*

*/

import java.util.*;
import java.io.*;

public class BrokenBST_797D {
    //static int fail = 0;
    static HashMap<Integer, Integer> map = null;

    static void go() {
        int n = in.nextInt();
        boolean[] ref = new boolean[n];
        int[][] tree = new int[n][3];
        for (int i = 0; i < n; i++) {
            tree[i][0] = in.nextInt();
            int left = in.nextInt() - 1, right = in.nextInt() - 1;
            tree[i][1] = left;
            tree[i][2] = right;
            if (left != -2)
                ref[left] = true;
            if (right != -2)
                ref[right] = true;
        }

        int root = 0;
        while (ref[root]) root++;

        //fail = 0;
        map = new HashMap<>();
        validate(tree, root, Integer.MIN_VALUE, Integer.MAX_VALUE);

        int fail = 0;
        for (int key : map.keySet()) {
            if (map.get(key) > 0)
                fail += map.get(key);
        }
        out.print(fail);
    }

    static void validate(int[][] tree, int node, int min, int max) {
        if (node == -2) return;
        if (tree[node][0] > max) {
            map.put(tree[node][0], map.getOrDefault(tree[node][0], 0) + 1);
            mark(tree, tree[node][2]);
            validate(tree, tree[node][1], min, max);
        } else if (tree[node][0] < min) {
            map.put(tree[node][0], map.getOrDefault(tree[node][0], 0) + 1);
            mark(tree, tree[node][1]);
            validate(tree, tree[node][2], min, max);
        } else {
            map.put(tree[node][0], -1000000);
            validate(tree, tree[node][1], min, tree[node][0]);
            validate(tree, tree[node][2], tree[node][0], max);
        }
    }

    static void mark(int[][] tree, int node) {
        if (node == -2) return;
        map.put(tree[node][0], map.getOrDefault(tree[node][0], 0) + 1);
        mark(tree, tree[node][1]);
        mark(tree, tree[node][2]);
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

        public char nextChar() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            return (char) c;
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

/**
 * Test case:
 * <p>
 * 3
 * 2 -1 -1
 * 1 1 3
 * 2 -1 -1
 * <p>
 * Expect:
 * 0
 */