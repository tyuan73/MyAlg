package problemset862;


/*

*/

import java.util.*;
import java.io.*;

public class MahmoudandEhabandthebipartiteness862B_UnionFind_disjointset {
    /**
     * Union-Find, or Disjoint set. Much faster than DFS or BFS.
     * Use "other" to remember the target/tag for the other group.
     * When given a pair (a, b), just join "other[a]" with "b"; then join "a" with "other[b]".
     */
    static void go() {
        int n = in.nextInt();
        int[] ds = new int[n + 1], other = new int[n + 1];
        int a = 0, b = 0;
        for (int i = 0; i < n - 1; i++) {
            a = in.nextInt();
            b = in.nextInt();
            join(ds, other, a, b);
        }
        a = find(ds, a);
        int ca = 0, cb = 0;
        for (int i = 1; i <= n; i++)
            if (find(ds, i) == a) ca++;
            else cb++;

        out.println(ca * (long) cb - n + 1);
    }

    static void join(int[] ds, int[] other, int a, int b) {
        if (other[a] == 0) {
            other[a] = b;
        } else {
            int a1 = find(ds, other[a]);
            int b1 = find(ds, b);
            if (a1 != b1) {
                ds[a1] = b1;
                other[a] = b1;
            }
        }
        if (other[b] == 0) {
            other[b] = a;
        } else {
            int a1 = find(ds, a);
            int b1 = find(ds, other[b]);
            if (a1 != b1) {
                ds[a1] = b1;
                other[b] = b1;
            }
        }
    }

    static int find(int[] ds, int i) {
        return ds[i] == 0 ? i : (ds[i] = find(ds, ds[i]));
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