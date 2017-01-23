package hc2017.round1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by yuantian on 1/23/17.
 */
public class BeachUmbrellas {
    FastScanner in;
    PrintWriter out;
    static long P = 1000000007;
    static long[] F = new long[2001];

    static {
        F[0] = 1;
        for (int i = 1; i <= 2000; i++) {
            F[i] = (F[i - 1] * i) % P;
        }
    }

    public void solve() throws IOException {
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int total = 0;
            int[] U = new int[n];
            for (int i = 0; i < n; i++) {
                U[i] = in.nextInt();
                total += U[i];
            }

            if (n == 1) {
                System.out.println(m);
                continue;
            }

            long ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int col = m - (2 * total - U[i] - U[j]);
                    if (col < 0) continue;
                    long c = calculate(n, col);
                    c = (c * F[n - 2]) % P;
                    ans = (ans + 2 * c) % P;
                }
            }
            System.out.println(ans);
        }
    }

    long calculate(int row, int col) {
        long[] dp = new long[col + 1];
        Arrays.fill(dp, 1L);
        for (int i = 0; i < row; i++) {
            dp[0] = 0;
            for (int j = 1; j <= col; j++) {
                dp[j] += dp[j - 1];
                dp[j] %= P;
            }
        }
        return dp[col];
    }

    /*
    long inverse(long x) {
        long ret = 1;
        long pow = P - 2;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                ret = (ret * x) % P;
            }
            pow >>= 1;
            x = (x * x) % P;
        }
        return ret;
    }
    */

    public void run() {
        try {
            in = new FastScanner(new File("/home/yuantian/IdeaProjects/MyAlg/HackerCup_Facebook/src/hc2017/round1/BeachUmbrellas_small.in"));
            out = new PrintWriter(new File("/tmp/test/BeachUmbrellas.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new BeachUmbrellas().run();
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
