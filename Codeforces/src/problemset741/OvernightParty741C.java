package problemset741;

/**
 * Created by yuantian on 2/2/17.
 */

/*

*/

import java.util.*;
import java.io.*;

public class OvernightParty741C {
    static void go() {
        int n = in.nextInt() * 2;
        int[][] G = new int[n][2];
        for (int i = 0, d = 1; i < n; i++, d = -d) {
            G[i][0] = i + d;
        }
        int[][] pairs = new int[n / 2][2];
        for (int i = 0; i < n / 2; i++) {
            pairs[i][0] = in.nextInt() - 1;
            pairs[i][1] = in.nextInt() - 1;
            G[pairs[i][0]][1] = pairs[i][1];
            G[pairs[i][1]][1] = pairs[i][0];
        }

        boolean[] visited = new boolean[n];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int cur = i, next;
                int color = 1;
                while (!visited[cur]) {
                    visited[cur] = true;
                    ans[cur] = color;
                    next = G[cur][0];
                    if (visited[next]) next = G[cur][1];
                    color = 3 - color;
                    cur = next;
                }
            }
        }

        for (int[] pair : pairs) {
            out.printf("%d %d\n", ans[pair[0]], ans[pair[1]]);
        }
    }

    static void dfs(List<Integer>[] G, int cur, boolean[] visited, int[] ans, int food) {
        visited[cur] = true;
        ans[cur] = food;
        for (int next : G[cur]) {
            if (!visited[next]) {
                dfs(G, next, visited, ans, 3 - food);
                break;
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