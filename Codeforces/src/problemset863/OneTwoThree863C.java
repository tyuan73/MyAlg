package problemset863;


/*

*/

import java.util.*;
import java.io.*;

public class OneTwoThree863C {
    static void go() {
        long k = in.nextLong();
        int a = in.nextInt() - 1, b = in.nextInt() - 1;
        int[] next = new int[9];
        int[][] alice = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                alice[i][j] = in.nextInt() - 1;
            }
        }
        int[][] bob = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bob[i][j] = in.nextInt() - 1;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                next[i * 3 + j] = alice[i][j] * 3 + bob[i][j];
            }
        }

        //int v = a * 3 + b;
        long[] p = {0, 0};
        dfs(next, a * 3 + b, k, p);

        out.println(p[0] + " " + p[1]);

    }

    static void dfs(int[] next, int s, long k, long[] points) {
        int steps = 0;
        int[] visited = new int[9];
        while (visited[s] == 0 && steps < k) {
            visited[s] = ++steps;
            calPoints(s, points);
            s = next[s];
        }

        if (steps < k) {
            k -= steps;
            int len = steps - visited[s] + 1;
            if (k <= len) {
                dfs(next, s, k, points);
            } else {
                long[] cycle = {0l, 0l};
                dfs(next, s, len, cycle);
                points[0] += (k / len) * cycle[0];
                points[1] += (k / len) * cycle[1];
                dfs(next, s, k % len, points);
            }
        }
    }

    static void calPoints(int v, long[] points) {
        int a = v / 3, b = v % 3;
        if (a == b)
            return;
        if ((a == 0 && b == 2) || a - 1 == b)
            points[0]++;
        else
            points[1]++;
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

/*
Input:
1 3 1
1 3 3
2 3 2
2 1 3
1 3 2
3 3 2
3 1 1

expect:
0 1
 */