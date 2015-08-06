package problemset557;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 8/5/15
 * Time: 10:51 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

public class VitalyAndCycle557D {
    static List<Integer>[] tree = null;
    static boolean[] visited = null;
    static int[] parent = null;
    static int[] dist = null;
    static boolean hasOddCircle = false;

    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] dg = new int[n];
        int maxd = 0;
        tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            dg[from]++;
            maxd = Math.max(maxd, dg[from]);
            dg[to]++;
            maxd = Math.max(maxd, dg[to]);
            tree[from].add(to);
            tree[to].add(from);
        }
        if (maxd == 0) {
            long t = n;
            out.println("3 " + t * (t - 1) * (t - 2) / 6);
            return;
        }
        if (maxd == 1) {
            long t = m;
            out.println("2 " + t * (n - 2));
            return;
        }

        visited = new boolean[n];
        parent = new int[n];
        dist = new int[n];
        Arrays.fill(parent, -1);
        hasOddCircle = false;
        for(int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, 0);
                if (hasOddCircle) {
                    out.println("0 1");
                    return;
                }
            }
        }
    }

    static void dfs(int idx, int d) {
        visited[idx] = true;
        dist[idx] = d;
        for(int next : tree[idx]) {
            if (visited[next]) {
                if (((dist[idx] + dist[next]) & 1) == 0)    {
                    hasOddCircle = true;
                    return;
                }
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