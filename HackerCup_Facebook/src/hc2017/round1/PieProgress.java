package hc2017.round1;

import java.io.*;
import java.util.*;

/**
 * Created by yuantian on 1/23/17.
 */
public class PieProgress {
    FastScanner in;
    PrintWriter out;
    static int[] sq = new int[302];

    static {
        for (int i = 1; i <= 301; i++) {
            sq[i - 1] = 2 * i - 1;
        }
        //for (int i = 0; i < 10; i++)
        //    System.out.print(sq[i] + " ");
    }

    public void solve() throws IOException {
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] pie = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    pie[i][j] = in.nextInt();
                    //pie[i][j] += sq[i];
                }
                Arrays.sort(pie[i]);
                for (int j = 0; j < m; j++)
                    pie[i][j] += sq[j];
            }
            int ans = 0;
            int[] idx = new int[n];
            for (int i = 0; i < n; i++) {
                int min = Integer.MAX_VALUE;
                int minIdx = 0;
                for (int j = 0; j <= i; j++) {
                    if (idx[j] < m && pie[j][idx[j]] < min) {
                        min = pie[j][idx[j]];
                        minIdx = j;
                    }
                }
                ans += min;
                idx[minIdx]++;
            }
            out.println("Case #" + t + ": " + ans);
            System.out.println(ans);
        }

    }

    public void run() {
        try {
            in = new FastScanner(new File("/home/yuantian/IdeaProjects/MyAlg/HackerCup_Facebook/src/hc2017/round1/PieProgress.in"));
            out = new PrintWriter(new File("/tmp/test/PieProgress.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new PieProgress().run();
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