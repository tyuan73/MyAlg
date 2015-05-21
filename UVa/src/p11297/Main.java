package p11297;

/**
 * Created by yuantian on 5/18/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    static int N = 512;
    static int[][] reg = new int[2][N * N * 7];

    static void update(int idx, int sx, int sy, int bx, int by, int x, int y, int val) {
        if (x < sx || x > bx || y < sy || y > by)
            return;

        if (sx == bx && sy == by) {
            reg[0][idx] = val;
            reg[1][idx] = val;
            return;
        }

        int child = idx << 2, midx = (sx + bx) / 2, midy = (sy + by) / 2;
        update(child + 1, sx,       sy,       midx, midy, x, y, val);
        update(child + 2, sx,       midy + 1, midx, by,   x, y, val);
        update(child + 3, midx + 1, sy,       bx,   midy, x, y, val);
        update(child + 4, midx + 1, midy + 1, bx,   by,   x, y, val);

        reg[0][idx] = Math.max(Math.max(reg[0][child + 1], reg[0][child + 2]), Math.max(reg[0][child + 3], reg[0][child + 4]));
        reg[1][idx] = Math.min(Math.min(reg[1][child + 1], reg[1][child + 2]), Math.min(reg[1][child + 3], reg[1][child + 4]));
    }

    static int[] query(int idx, int sx, int sy, int bx, int by, int rsx, int rsy, int rbx, int rby) {
        //out.printf("rsx = %d, rsy = %d, rbx = %d, rby = %d\n", rsx, rsy, rbx, rby);
        //out.printf("sx = %d, sy = %d, bx = %d, by = %d\n", sx, sy, bx, by);
        //out.printf("idx = %d\n", idx);

        int[] ret = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        if (rbx < sx || rby < sy || bx < rsx || by < rsy) {
            return ret;
        }

        if (rsx <= sx && bx <= rbx && rsy <= sy && by <= rby) {
            //out.println("hit!!!");
            ret[0] = reg[0][idx];
            ret[1] = reg[1][idx];
            return ret;
        }

        int child = idx << 2, midx = (sx + bx) / 2, midy = (sy + by) / 2;
        int[] c1 = query(child + 1, sx,       sy,         midx,   midy,   rsx, rsy, rbx, rby);
        int[] c2 = query(child + 2, sx,       midy + 1,   midx,   by,     rsx, rsy, rbx, rby);
        int[] c3 = query(child + 3, midx + 1, sy,         bx,     midy,   rsx, rsy, rbx, rby);
        int[] c4 = query(child + 4, midx + 1, midy + 1,   bx,     by,     rsx, rsy, rbx, rby);
        ret[0] = Math.max(Math.max(c1[0], c2[0]), Math.max(c3[0], c4[0]));
        ret[1] = Math.min(Math.min(c1[1], c2[1]), Math.min(c3[1], c4[1]));
        return ret;
    }

    static void go() {
        int n = in.nextInt();
        n = in.nextInt();
        Arrays.fill(reg[0], Integer.MIN_VALUE);
        Arrays.fill(reg[1], Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int p = in.nextInt();
                update(0, 0, 0, N - 1, N - 1, i, j, p);
            }
        }

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            String op = in.nextString();
            if (op.charAt(0) == 'q') {
                int sx = in.nextInt() - 1, sy = in.nextInt() - 1;
                int bx = in.nextInt() - 1, by = in.nextInt() - 1;
                int[] ans = query(0, 0, 0, N - 1, N - 1, sx, sy, bx, by);
                out.println(ans[0] + " " + ans[1]);
            } else {
                int x = in.nextInt() - 1, y = in.nextInt() - 1, val = in.nextInt();
                update(0, 0, 0, N - 1, N - 1, x, y, val);
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