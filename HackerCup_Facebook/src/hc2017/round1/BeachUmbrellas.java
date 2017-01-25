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
    static long[] H = new long[2001];
    static long[] G = null;

    static {
        F[0] = 1;
        for (int i = 1; i <= 2000; i++) {
            F[i] = (F[i - 1] * i) % P;
        }

        H[0] = 1;
        for (int i = 1; i <= 2000; i++) {
            H[i] = (inverse(i) * H[i - 1]) % P;
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
                //System.out.println(m);
                out.printf("Case #%d: %d\n", t, m);
                continue;
            }

            Arrays.sort(U);
            int min = m - (2 * total - U[0] - U[1]) - n;
            int max = m - (2 * total - U[n - 2] - U[n - 1]) + n - 1;
            if (max < n) {
                //System.out.println(0);
                out.printf("Case #%d: %d\n", t, 0);
                continue;
            }
            min = Math.max(min, 0);
            G = new long[max - min + 1];
            G[0] = min == 0 ? ++min : min++;
            for (int i = 1; i < G.length; i++) {
                G[i] = (G[i - 1] * min++) % P;
            }

            long ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int ava = m - (2 * total - U[i] - U[j]) - 1;
                    if (ava < 0) continue;
                    long c = C(ava + n, n, max);
                    c = (c * F[n - 2]) % P;
                    ans = (ans + 2 * c) % P;
                }
            }
            //System.out.println(ans);
            out.printf("Case #%d: %d\n", t, ans);
        }
    }

    long C(int ava, int sel, int max) {
        int lastIdx = G.length - (max - ava) - 1;
        int firstIdx = lastIdx - sel;
        long ret = G[lastIdx] * inverse(G[firstIdx]) % P;
        ret = (ret * H[sel]) % P;
        return ret;
    }

    static long inverse(long x) {
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

    public void run() {
        try {
            in = new FastScanner(new File("/Users/Helena/Documents/IntelliJ Projects/MyAlg/HackerCup_Facebook/src/hc2017/round1/BeachUmbrellas.in"));
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
