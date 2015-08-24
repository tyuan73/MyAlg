package problemset570;

/**
 * Created by yuantian on 8/24/15.
 */

import java.util.*;
import java.io.*;

public class TreeRequests570D {
    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();

        List<Integer>[] tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList();
        }

        for (int i = 2; i <= n; i++) {
            int from = in.nextInt();
            tree[from].add(i);
        }

        char[] seq = in.nextCharArray(n);

        int[][] node = new int[n + 1][3];
        int[] map = new int[n + 1]; // original to new
        int[] rmap = new int[n + 1]; // new to original
        int idx = 2;

        int start = 1, end = 1;
        map[1] = 1;
        rmap[1] = 1;
        node[1][0] = 1;
        int layer = 2;
        int[][] bit = new int[26][n + 1];
        while (start <= end) {
            for (int i = start; i <= end; i++) {
                for (int next : tree[rmap[i]]) {
                    rmap[idx] = next;
                    map[next] = idx;
                    node[idx][0] = layer;
                    if (node[i][1] == 0) {
                        node[i][1] = idx;
                    }
                    node[i][2] = idx;

                    add(bit[seq[next - 1] - 'a'], idx);

                    idx++;
                }
            }
            layer++;
            start = end + 1;
            end = idx - 1;
        }

        for(int[] nx : node) {
            out.println(nx[0] + " " + nx[1] + " " + nx[2]);
        }

        for (int i = 0; i < m; i++) {
            int nidx = map[in.nextInt()];
            int h = in.nextInt();
            if (h <= node[nidx][0]) {
                out.println("Yes");
                continue;
            }
            int[] range = getRange(node, nidx, h);
            System.out.println("range = " + range[0] + " - " + range[1]);
            int oddcount = 0;
            for (int c = 0; c < 26; c++) {
                int count = get(bit[c], range[0] - 1, range[1]);
                if ((count & 1) > 0)
                    oddcount++;
            }
            if (oddcount > 1) {
                out.println("No");
            } else {
                out.println("Yes");
            }
        }
    }

    static int[] getRange(int[][] node, int idx, int h) {
        int left = node[idx][1], right = node[idx][2];
        int layer = node[left][0];
        while (layer < h) {
            left = node[left][1];
            right = node[right][2];
            layer++;
        }
        int[] ret = {left, right};
        return ret;
    }

    static void add(int[] a, int i) {
        for (; i < a.length; i += (i & -i)) {
            a[i]++;
        }
    }

    static int get(int[] a, int l, int r) {
        int ret = 0;
        while (r != l) {
            if (r > l) {
                ret += a[r];
                r -= (r & -r);
            } else {
                ret -= a[l];
                l -= (l & -l);
            }
        }
        return ret;
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

/**
 input:

 5 6
 1 2 2 1
 baabb
 1 1
 1 2
 5 1
 4 1
 4 2
 3 2

 output:

 Yes
 No
 Yes
 Yes
 Yes
 Yes


 input:

 5 9
 1 1 1 2
 edbcb
 1 3
 2 1
 1 3
 2 1
 2 2
 2 2
 1 1
 1 3
 2 1

 output:

 Yes
 Yes
 Yes
 Yes
 Yes
 Yes
 Yes
 Yes
 Yes


 5 1
 1 1 1 2
 edbcb
 1 3

 */