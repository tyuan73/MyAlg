package p12532;

/**
 * Created by yuantian on 5/6/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class MainFenwickTree {

    final static int MAXN = 100005;
    static int[] ZEROS = new int[MAXN];
    static int[] NEGS = new int[MAXN];
    static int[] SEQ = new int[MAXN];

    static void update(int i, int top, int[] fwt, int val) {
        for (; i <= top; i += i & -i) {
            fwt[i] += val;
        }
    }

    static int sum(int i, int j, int[] fwt) {
        int ret = 0;
        while (i != j) {
            if (j > i) {
                ret += fwt[j];
                j -= j & -j;
            } else {
                ret -= fwt[i];
                i -= i & -i;
            }
        }
        return ret;
    }

    static int process(int x) {
        return x == 0 ? 0 : x < 0 ? -1 : 1;
    }

    static void go() {
        int n, k;
        while (true) {
            try {
                n = in.nextInt();
            } catch (Exception e) {
                break;
            }

            k = in.nextInt();

            Arrays.fill(ZEROS, 0);
            Arrays.fill(NEGS, 0);

            for (int i = 1; i <= n; i++) {
                SEQ[i] = process(in.nextInt());
                if (SEQ[i] == 0) {
                    update(i, n, ZEROS, 1);
                } else if (SEQ[i] == -1) {
                    update(i, n, NEGS, 1);
                }
            }

            while (k-- > 0) {
                String op = in.nextString();
                int a = in.nextInt(), b = in.nextInt();
                if (op.charAt(0) == 'C') {
                    b = process(b);
                    if (b != SEQ[a]) {
                        if (SEQ[a] == 0) {
                            update(a, n, ZEROS, -1);
                        } else if (SEQ[a] == -1) {
                            update(a, n, NEGS, -1);
                        }
                        if (b == 0) {
                            update(a, n, ZEROS, 1);
                        } else if (b == -1) {
                            update(a, n, NEGS, 1);
                        }
                        SEQ[a] = b;
                    }
                } else {
                    a--;
                    int ret = sum(a, b, ZEROS);
                    if (ret > 0)
                        out.print('0');
                    else {
                        ret = sum(a, b, NEGS);
                        out.print((ret & 1) > 0 ? '-' : '+');
                    }
                }

            }
            out.println();
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