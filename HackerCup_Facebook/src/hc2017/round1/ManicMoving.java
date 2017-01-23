package hc2017.round1;


import java.io.*;
import java.util.*;

/**
 * Created by yuantian on 1/23/17.
 */
public class ManicMoving {
    FastScanner in;
    PrintWriter out;
    static long[][] G = new long[101][101];

    public void solve() throws IOException {
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            for (int i = 0; i < 101; i++) {
                Arrays.fill(G[i], Integer.MAX_VALUE);
            }
            for (int i = 0; i < 101; i++)
                G[i][i] = 0;

            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();

            // Floyd–Warshall algorithm
            for (int i = 0; i < m; i++) {
                int x = in.nextInt(), y = in.nextInt(), g = in.nextInt();
                if (g < G[x][y]) {
                    G[x][y] = g;
                    G[y][x] = g;
                }
            }

            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    for (int h = 1; h <= n; h++)
                        if (G[j][h] > G[j][i] + G[i][h])
                            G[j][h] = G[j][i] + G[i][h];

            // DP to find the answer
            boolean yes = true;
            int[][] D = new int[k][2];
            for (int i = 0; i < k; i++) {
                int x = in.nextInt(), y = in.nextInt();
                D[i][0] = x;
                D[i][1] = y;
                if (G[1][x] == Integer.MAX_VALUE || G[x][y] == Integer.MAX_VALUE)
                    yes = false;
            }
            if (!yes) {
                //System.out.println(-1);
                out.println("Case #" + t + ": -1");
                continue;
            }

            if (k == 1) {
                long ans = G[1][D[0][0]] + G[D[0][0]][D[0][1]];
                //System.out.println(ans);
                out.println("Case #" + t + ": " + ans);
                continue;
            }

            long[][] dp = new long[2][k + 1];
            dp[0][0] = G[1][D[0][0]] + G[D[0][0]][D[0][1]];
            dp[1][0] = G[1][D[0][0]] + G[D[0][0]][D[1][0]] + G[D[1][0]][D[0][1]];
            for (int i = 1; i < k; i++) {
                int from = D[i][0], to = D[i][1];
                int pre = D[i - 1][1];
                int next = i == k - 1 ? 0 : D[i + 1][0];

                dp[0][i] = Math.min(dp[0][i - 1] + G[pre][from] + G[from][to], dp[1][i - 1] + G[pre][to]);
                if (i < k - 1)
                    dp[1][i] = Math.min(dp[0][i - 1] + G[pre][from] + G[from][next] + G[next][to], dp[1][i - 1] + G[pre][next] + G[next][to]);
            }
            //System.out.println(dp[0][k - 1]);
            out.println("Case #" + t + ": " + dp[0][k - 1]);
        }
    }


    public void run() {
        try {
            in = new FastScanner(new File("/home/yuantian/IdeaProjects/MyAlg/HackerCup_Facebook/src/hc2017/round1/ManicMoving.in"));
            out = new PrintWriter(new File("/tmp/test/ManicMoving.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new ManicMoving().run();
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File file) throws IOException {
            br = new BufferedReader(new FileReader(file));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                    return "";
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
