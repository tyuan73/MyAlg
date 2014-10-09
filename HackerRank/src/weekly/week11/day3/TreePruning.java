package weekly.week11.day3;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 10/8/14
 * Time: 12:50 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

*/

import java.util.*;
import java.io.*;

public class TreePruning {
    static List<Integer>[] tree = null;
    static boolean[] visited = null;
    static int[] parent = null;
    static int[] order = null;
    static int index = 0;
    static int[][] subtree = null;
    static int[] pri = null;

    static class Ele implements Comparable<Ele> {
        int index = 0;
        int pri = 0;
        long weight = 0;

        public Ele(int i, int pri, long w) {
            this.index = i;
            this.pri = pri;
            this.weight = w;

        }

        public int compareTo(Ele e) {
            if (this.weight > e.weight)
                return 1;
            else if (this.weight < e.weight)
                return -1;
            else
                return e.pri - this.pri;
        }
    }

    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();

        tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<Integer>();

        long[] w = new long[n];
        for (int i = 0; i < n; i++) {
            w[i] = in.nextLong();
        }

        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            tree[from].add(to);
            tree[to].add(from);
        }

        visited = new boolean[n];
        order = new int[n];
        index = 0;
        parent = new int[n];
        parent[0] = -1;
        subtree = new int[n][2];
        pri = new int[n];

        dfs(0);

        PriorityQueue<Ele> pq = new PriorityQueue<Ele>();

        for (int i = n - 1; i > 0; i--) {
            int v = order[i];
            w[parent[v]] += w[v];
            if (w[v] < 0)
                pq.add(new Ele(v, i, w[v]));
        }
        //if (w[0] < 0)
        //    pq.add(new Ele(0, 0, w[0]));

        while (k > 0 && !pq.isEmpty()) {
            Ele e = pq.poll();
            if (e.weight != w[e.index])
                continue;

            for (int j = subtree[e.index][0]; j <= subtree[e.index][1]; j++) {
                w[order[j]] = 0;
            }

            int idx = e.index;
            while (parent[idx] != -1) {
                w[parent[idx]] -= e.weight;
                if (w[parent[idx]] < 0 && parent[idx] != 0)
                    pq.add(new Ele(parent[idx], pri[parent[idx]], w[parent[idx]]));
                idx = parent[idx];
            }
            k--;
        }

        out.println(Math.max(0, w[0]));
    }

    static void dfs(int v) {
        visited[v] = true;
        subtree[v][0] = index;
        pri[v] = index;
        order[index++] = v;
        for (int next : tree[v]) {
            if (!visited[next]) {
                parent[next] = v;
                dfs(next);
            }
        }
        subtree[v][1] = index - 1;
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