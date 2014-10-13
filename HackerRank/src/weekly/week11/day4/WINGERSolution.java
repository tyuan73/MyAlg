package weekly.week11.day4;

/**
 * Created by yuantian on 10/13/14.
 */

import java.io.*;
import java.util.Arrays;

public class WINGERSolution {

    public static void solve(Input in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int gn = n + m + 2;
        int s = n + m;
        int t = n + m + 1;
        int[][] g = new int[gn][gn];
        for (int i = 0; i < n; ++i) {
            g[s][i] = in.nextInt();
            g[i][t] = 1;
        }
        for (int it = 0; it < m; ++it) {
            int as = in.nextInt();
            for (int i = 0; i < as; ++i) {
                int a = in.nextInt() - 1;
                g[a][n + it] = 1;
            }
            int bs = in.nextInt();
            for (int i = 0; i < bs; ++i) {
                int b = in.nextInt() - 1;
                g[n + it][b] = 1;
            }
        }
        boolean[] col = new boolean[gn];
        int ans = 0;
        while (dfs(s, t, g, col)) {
            ans++;
            Arrays.fill(col, false);
        }
        out.println(ans);
    }

    private static boolean dfs(int s, int t, int[][] g, boolean[] col) {
        if (s == t) {
            return true;
        }
        if (col[s]) {
            return false;
        }
        col[s] = true;
        for (int j = 0; j < g.length; ++j) {
            if (g[s][j] > 0 && dfs(j, t, g, col)) {
                g[s][j]--;
                g[j][s]++;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        solve(new Input(new BufferedReader(new InputStreamReader(System.in))), out);
        out.close();
    }

    static class Input {
        BufferedReader in;
        StringBuilder sb = new StringBuilder();

        public Input(BufferedReader in) {
            this.in = in;
        }

        public Input(String s) {
            this.in = new BufferedReader(new StringReader(s));
        }

        public String next() throws IOException {
            sb.setLength(0);
            while (true) {
                int c = in.read();
                if (c == -1) {
                    return null;
                }
                if (" \n\r\t".indexOf(c) == -1) {
                    sb.append((char) c);
                    break;
                }
            }
            while (true) {
                int c = in.read();
                if (c == -1 || " \n\r\t".indexOf(c) != -1) {
                    break;
                }
                sb.append((char) c);
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}
