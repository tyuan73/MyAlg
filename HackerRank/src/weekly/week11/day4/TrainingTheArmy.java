package weekly.week11.day4;

/**
 * Created by yuantian on 10/14/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class TrainingTheArmy {
    static void go() {
        int n = in.nextInt();
        int t = in.nextInt();
        int[][] g = new int[n + t + 2][n + t + 2];
        for (int i = 1; i <= n; i++) {
            g[0][i] = in.nextInt();
            g[i][n + t + 1] = 1;
        }

        for (int i = n + 1; i <= n + t; i++) {
            int a = in.nextInt();
            for (int j = 0; j < a; j++) {
                int from = in.nextInt();
                g[from][i] = 1;
            }

            int b = in.nextInt();
            for (int j = 0; j < b; j++) {
                int to = in.nextInt();
                g[i][to] = 1;
            }
        }

        int count = 0;
        boolean[] visited = new boolean[n + t + 2];
        while (dfs(0, g, visited)) {
            count++;
            Arrays.fill(visited, false);
        }

        out.println(count);
    }

    static boolean dfs(int from, int[][] g, boolean[] visited) {
        if (from == g.length - 1)
            return true;

        visited[from] = true;
        for (int i = 0; i < g.length; i++) {
            if (visited[i] || g[from][i] == 0)
                continue;
            if (dfs(i, g, visited)) {
                g[from][i]--;
                g[i][from]++;
                return true;
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