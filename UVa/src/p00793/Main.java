package p00793;

/**
 * Created by yuantian on 4/20/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
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

        public int get(int a) {
            return dset[a] < 0 ? a : (dset[a] = get(dset[a]));
        }
    }

    static void go() {
        int t = in.nextInt();
        in.nextLine();
        while (t-- > 0) {
            int n = in.nextInt();
            DisjointSet ds = new DisjointSet(n);
            String op = null;
            int c1 = 0, c2 = 0;
            while (true) {
                try {
                    op = in.nextLine();
                } catch (Exception e) {
                    break;
                }
                if (op.length() == 0)
                    break;
                String[] split = op.split(" ");
                int a = Integer.parseInt(split[1]) - 1, b = Integer.parseInt(split[2]) - 1;
                if (op.charAt(0) == 'c') {
                    int x = ds.get(a), y = ds.get(b);
                    if (x != y) ds.merge(x, y);
                } else {
                    if (ds.get(a) == ds.get(b)) {
                        c1++;
                    } else {
                        c2++;
                    }
                }
            }
            out.println(c1 + "," + c2);
            if (t != 0) out.println();
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