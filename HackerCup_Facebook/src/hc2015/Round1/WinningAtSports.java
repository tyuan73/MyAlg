package hc2015.Round1;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by yuantian on 2/4/15.
 */
public class WinningAtSports {
    FastScanner in;
    PrintWriter out;

    static final long P = (long) 1e9 + 7;

    public void solve() throws IOException {
        int T = in.nextInt();

        long[] f = new long[4001];
        f[0] = 1;
        for (int i = 1; i <= 4000; i++) {
            f[i] = (f[i - 1] * i) % P;
        }

        long[] invF = new long[4001];
        invserseArray(4000, invF, P);
        for (int i = 2; i <= 4000; i++) {
            invF[i] = (invF[i - 1] * invF[i]) % P;
        }

        /**
         * stress-free (note: winner is restrictly larger than the loser at every step.
         *   hook length = (n+m-1)! * (n-m) / (m! * n!)
         * stressfull:
         *   hook length = (2*m)! / (m! * (m+1)!)
         *
         * hook length:
         *      http://en.wikipedia.org/wiki/Young_tableau
         * and:
         *      http://en.wikipedia.org/wiki/Hook_length_formula
         */
        for (int t = 1; t <= T; t++) {
            String str = in.next();
            int pos = str.indexOf('-');
            int n = Integer.parseInt(str.substring(0, pos));
            int m = Integer.parseInt(str.substring(pos + 1));

            long ans1 = (((f[n + m - 1] * (n - m)) % P) * ((invF[m] * invF[n]) % P)) % P;
            long ans2 = (((f[2 * m] * invF[m]) % P) * invF[m + 1]) % P;
            out.println("Case #" + t + ": " + ans1 + " " + ans2);
            System.out.println("Case #" + t + ": " + ans1 + " " + ans2);
        }
    }

    void invserseArray(int n, long[] a, long p) {
        a[0] = 1;
        a[1] = 1;
        for (int i = 2; i <= n; i++) {
            a[i] = (-(p / i) * a[(int) p % i]) % p + p;
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("/home/yuantian/IdeaProjects/MyAlg/HackerCup_Facebook/src/hc2015/Round1/WinningAtSports.in"));
            out = new PrintWriter(new File("/tmp/test/WinningAtSports.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new WinningAtSports().run();
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


/*
Case #1: 1 1
Case #2: 2 1
Case #3: 2 2
Case #4: 1001 42
Case #5: 70047606 591137401
 */