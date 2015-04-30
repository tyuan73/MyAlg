package hc2015.Round1;/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/3/15
 * Time: 10:51 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.*;
import java.util.*;

public class CorporateGifting {
    FastScanner in;
    PrintWriter out;
    int[] count = null;

    public void solve() throws IOException {
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int n = in.nextInt();
            int[] p = new int[n];
            for(int i = 0; i < n; i++)
                p[i] = in.nextInt();

            int[] layer = new int[n];
            Arrays.fill(layer, -1);
            layer[0] = 0;
            count = new int[2];
            count[0] = 1;
            for(int i = 1; i < n; i++) {
                if (layer[i] == -1) {
                    find(layer, p, i);
                }
            }

            int ans = 0;
            if (count[0] > count[1])
                ans = 2 * count[1] + count[0];
            else
                ans = 2 * count[0] + count[1];

            out.println("Case #" + t + ": " + ans);
            System.out.println("Case #" + t + ": " + ans);
        }
    }

    private int find(int[] layer, int[] p, int index) {
        if (layer[index] != -1)
            return layer[index];

        int x = 1-find(layer, p, p[index]-1);
        layer[index] = x;
        count[x]++;
        return x;
    }

    public void run() {
        try {
            in = new FastScanner(new File("/Users/yuantian/MyAlg/HackerCup_Facebook/src/hc2015/Round1/CorporateGifting.in"));
            out = new PrintWriter(new File("/tmp/test/CorporateGifting.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new CorporateGifting().run();
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
