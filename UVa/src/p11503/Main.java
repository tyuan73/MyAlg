package p11503;

/**
 * Created by yuantian on 4/21/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {
        int t = in.nextInt();
        HashMap<String, Integer> map = new HashMap<>();
        while (t-- > 0) {
            int n = in.nextInt();
            int[][] q = new int[n][2];
            map.clear();
            int index = 0;
            for (int i = 0; i < n; i++) {
                String a = in.nextString();
                String b = in.nextString();
                if (map.containsKey(a)) {
                    q[i][0] = map.get(a);
                } else {
                    q[i][0] = index;
                    map.put(a, index++);
                }
                if (map.containsKey(b)) {
                    q[i][1] = map.get(b);
                } else {
                    q[i][1] = index;
                    map.put(b, index++);
                }
            }

            DisjointSet ds = new DisjointSet(index);
            for (int[] x : q) {
                int a = ds.root(x[0]);
                int b = ds.root(x[1]);
                if (a != b) {
                    ds.merge(a, b);
                }
                a = ds.root(x[0]);
                out.println(ds.count(a));
            }
        }
    }

    static class DisjointSet {
        int[] dset = null;

        public DisjointSet(int n) {
            assert n > 0;
            dset = new int[n];
            Arrays.fill(dset, -1);
        }

        public boolean merge(int a, int b) {
            assert dset[a] < 0 && dset[b] < 0;
            if (dset[a] > dset[b]) {
                dset[b] += dset[a];
                dset[a] = b;
                return true;
            } else {
                dset[a] += dset[b];
                dset[b] = a;
            }
            return false;
        }

        public int count(int a) {
            assert dset[a] < 0;
            return -dset[a];
        }

        public int root(int a) {
            return dset[a] < 0 ? a : (dset[a] = root(dset[a]));
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
            while (!(c == '\n' || c == '\r')) {
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