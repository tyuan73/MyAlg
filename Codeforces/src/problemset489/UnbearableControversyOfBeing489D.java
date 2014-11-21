package problemset489;

/**
 * Created by yuantian on 11/21/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class UnbearableControversyOfBeing489D {
    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }

        int[][] graph = packGraph(n, from, to);
        long total = 0;
        long[] count = new long[n];
        for (int root = 0; root < n; root++) {
            Arrays.fill(count, 0);
            for (int second : graph[root]) {
                for (int target : graph[second]) {
                    if (target != root) {
                        count[target]++;
                    }
                }
            }
            for (long c : count) {
                if (c != 0)
                    total += (c - 1) * c / 2;
            }
        }
        out.println(total);
    }

    static int[][] packGraph(int n, int[] from, int[] to) {
        int[][] graph = new int[n][];
        int[] count = new int[n];
        for (int node : from) {
            count[node]++;
        }
        for (int i = 0; i < n; i++) {
            graph[i] = new int[count[i]];
        }
        for (int i = from.length - 1; i >= 0; i--) {
            graph[from[i]][--count[from[i]]] = to[i];
        }
        return graph;
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