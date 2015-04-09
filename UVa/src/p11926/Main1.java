package p11926;

/**
 * Created by yuantian on 4/7/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main1 {
    static class Seg {
        int start, end;
        Seg left, right;

        Seg(int s, int e) {
            this.start = s;
            this.end = e;
            this.left = null;
            this.right = null;
        }

        boolean checkAndInsert(Seg f) {
            if (f.end > this.start && this.end > f.start) {
                return false;
            }

            if (f.start < this.start) {
                if (this.left != null)
                    return this.left.checkAndInsert(f);
                else {
                    this.left = f;
                    return true;
                }
            } else {
                if (this.right != null)
                    return this.right.checkAndInsert(f);
                else {
                    this.right = f;
                    return true;
                }
            }

            //return false;
        }
    }

    static void go() {
        while (true) {
            int n = in.nextInt();
            int m = in.nextInt();
            if ( n == 0 && m == 0)
                break;
            int[][] one = new int[n][];
            for (int i = 0; i < n; i++)
                one[i] = in.nextIntArray(2);
            int[][] mul = new int[m][];
            for (int i = 0; i < m; i++)
                mul[i] = in.nextIntArray(3);

            Seg root = null;
            boolean ok = true;
            for (int[] pair : one) {
                Seg f = new Seg(pair[0], pair[1]);
                if (root == null) {
                    root = f;
                } else if (!root.checkAndInsert(f)) {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                out.println("CONFLICT");
                continue;
            }

            for (int[] tri : mul) {
                for (int s = tri[0], e = tri[1]; s < 1000000; s += tri[2], e += tri[2]) {
                    Seg f = new Seg(s, e);
                    if (root == null) {
                        root = f;
                    } else if (!root.checkAndInsert(f)) {
                        ok = false;
                        break;
                    }
                }
            }
            if (!ok) {
                out.println("CONFLICT");
            } else {
                out.println("NO CONFLICT");
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