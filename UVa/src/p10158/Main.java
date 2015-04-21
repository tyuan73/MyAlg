package p10158;

/**
 * Created by yuantian on 4/21/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {
        int n = in.nextInt();
        int[] enemies = new int[n];
        Arrays.fill(enemies, -1);
        DisjointSet ds = new DisjointSet(n);
        int op, c1, c2;
        while (true) {
            op = in.nextInt();
            c1 = in.nextInt();
            c2 = in.nextInt();
            if (op == 0) {
                break;
            }

            int a = ds.root(c1);
            int b = ds.root(c2);
            switch (op) {
                case 1: // setFriends
                    if (enemies[a] != b) {
                        join(ds, a, b, enemies);
                    } else {
                        out.println(-1);
                    }
                    break;
                case 2: // setEnemies
                    if (a == b)
                        out.println(-1);
                    else {
                        if (enemies[a] != -1 && enemies[a] != b) {
                            join(ds, enemies[a], b, enemies);
                        }
                        if (enemies[b] != -1 && enemies[b] != a) {
                            join(ds, enemies[b], a, enemies);
                        }
                        enemies[a] = ds.root(b);
                        enemies[b] = ds.root(a);
                    }
                    break;
                case 3: // are friends?
                    out.println(a == b ? "1" : "0");
                    break;
                case 4: // are enemies?
                    out.println(enemies[a] == b ? "1" : "0");
                    break;
            }
        }
    }

    static void join(DisjointSet ds, int a, int b, int[] enemies) {
        a = ds.root(a);
        b = ds.root(b);
        if (a != b) {
            ds.merge(a, b);
            if (enemies[a] != -1 && enemies[b] != -1) {
                join(ds, enemies[a], enemies[b], enemies);
                int r1 = ds.root(a);
                int r2 = ds.root(enemies[a]);
                enemies[r1] = r2;
                enemies[r2] = r1;
            } else if (enemies[a] != -1 || enemies[b] != -1) {
                int e = enemies[a] == -1 ? enemies[b] : enemies[a];
                enemies[ds.root(a)] = e;
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
                dset[a] = b;
                return true;
            } else if (dset[a] == dset[b]) {
                dset[b] = a;
                dset[a]--;
            } else {
                dset[b] = a;
            }
            return false;
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