package weekly.week12.day4;

/**
 * Created by yuantian on 11/18/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class WhiteFalconAndTree {
    static List<Integer>[] tree = null;
    static boolean[] visited = null;
    static int[] next = null;
    static long P = (long) 1e9 + 7;

    static void go() {
        int n = in.nextInt();
        int[][] ln = new int[n][2];
        for (int i = 0; i < n; i++) {
            ln[i][0] = in.nextInt();
            ln[i][1] = in.nextInt();
        }
        tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<Integer>();

        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            tree[from].add(to);
            tree[to].add(from);
        }

        visited = new boolean[n];
        next = new int[n];
        int t = in.nextInt();
        while (t-- > 0) {
            Arrays.fill(visited, false);
            Arrays.fill(next, -1);

            int q = in.nextInt();
            if (q == 1) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                int a = in.nextInt();
                int b = in.nextInt();
                dfs(from, to);
                while (next[from] != -1) {
                    ln[from][0] = a;
                    ln[from][1] = b;
                    from = next[from];
                }
                ln[from][0] = a;
                ln[from][1] = b;
            } else {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                long x = in.nextInt();
                dfs(from, to);
                while (next[from] != -1) {
                    x = (ln[from][0] * x + ln[from][1]) % P;
                    from = next[from];
                }
                x = (ln[from][0] * x + ln[from][1]) % P;
                out.println(x);
            }
        }
    }

    static boolean dfs(int from, int to) {
        visited[from] = true;
        if (from == to)
            return true;
        for (int step : tree[from]) {
            if (!visited[step]) {
                if (dfs(step, to)) {
                    next[from] = step;
                    return true;
                }
            }
        }
        return false;
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