package problemset510;

/**
 * Created by yuantian on 1/31/17.
 */

/*

*/

import java.util.*;
import java.io.*;

public class FoxAndDinner510E {
    static class Ele {
        int val, idx;

        Ele(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    static void go() {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        // calculate primes
        boolean[] primes = new boolean[20001];
        Arrays.fill(primes, true);
        primes[0] = false;
        for (int i = 2; i < 10001; i++) {
            if (primes[i]) {
                for (int j = i * i; j <= 20000; j += i)
                    primes[j] = false;
            }
        }

        List<Ele> even = new ArrayList<>();
        List<Ele> odd = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = a[i];
            if ((x & 1) == 1)
                odd.add(new Ele(x, i + 1));
            else
                even.add(new Ele(x, i + 1));
        }

        if (even.size() != odd.size()) {
            out.println("Impossible");
            return;
        }

        // build max flow graph
        int[][] G = new int[n + 2][n + 2];
        int m = n / 2;
        for (int i = 0; i < even.size(); i++) {
            for (int j = 0; j < odd.size(); j++) {
                if (primes[even.get(i).val + odd.get(j).val]) {
                    G[i + 1][m + j + 1] = 1;
                    //G[m + j + 1][i + 1] = 1;
                }
            }
        }
        // source
        for (int i = 1; i <= m; i++) {
            G[0][i] = 2;
        }
        // end/sink
        for (int i = m + 1; i < n + 1; i++)
            G[i][n + 1] = 2;

        // max flow
        int mf = 0;
        LinkedList<Integer> list = new LinkedList<>();
        int[] pre = new int[n + 2];
        pre[0] = -1;
        boolean found = false;
        boolean[] visited = new boolean[n + 2];
        do {
            found = false;
            list.clear();
            list.add(0); // add source
            Arrays.fill(visited, false);
            visited[0] = true;
            // BFS - breadth first search --- DFS will just work in this case
            // since every augmenting path will just have a flow of 1.
            while (!list.isEmpty()) {
                int cur = list.removeFirst();
                for (int i = 0; i <= n + 1; i++) {
                    if (G[cur][i] > 0 && !visited[i]) {
                        list.addLast(i);
                        visited[i] = true;
                        pre[i] = cur;
                        if (i == n + 1) {
                            found = true;
                            break;
                        }
                    }
                }
                if (found) break;
            }
            if (found) {
                mf++;
                // adjust residual network
                int cur = n + 1;
                while (pre[cur] != -1) {
                    G[pre[cur]][cur]--;
                    G[cur][pre[cur]]++;
                    cur = pre[cur];
                }
            }
        } while (found);

        /* for debug
        for (int i = 0; i <= n + 1; i++) {
            for (int j = 0; j <= n + 1; j++)
                out.print(G[i][j] + " ");
            out.println();
        }
        */


        if (mf != n)
            out.println("Impossible");
        else {
            // DFS to find the table arrangement
            //out.println("OK");
            Arrays.fill(visited, false);
            visited[0] = true;
            visited[n + 1] = true;
            List<List<Integer>> ans = new ArrayList<>();

            // re-struct the path
            for (int i = m + 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    G[j][i] = G[i][j];
                }
            }

            // dfs
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    List<Integer> path = new ArrayList<>();
                    dfs(G, i, visited, path);
                    ans.add(path);
                }
            }

            // the following just print out the result
            out.println(ans.size());
            for (List<Integer> path : ans) {
                out.print(path.size());
                for (int x : path) {
                    if (x > m)
                        out.print(" " + odd.get(x - m - 1).idx);
                    else
                        out.print(" " + even.get(x - 1).idx);
                }
                out.println();
            }
        }
    }

    static void dfs(int[][] G, int cur, boolean[] visited, List<Integer> path) {
        visited[cur] = true;
        path.add(cur);
        for (int i = 1; i < G.length - 1; i++) {
            if (!visited[i] && G[i][cur] == 1) {
                dfs(G, i, visited, path);
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