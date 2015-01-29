package hc2015.Qualification;/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/27/15
 * Time: 10:21 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.*;
import java.util.StringTokenizer;

public class NewYearResolution {
    FastScanner in;
    PrintWriter out;
    int g1 = 0, g2 = 0, g3 = 0;

    public void solve() throws IOException {
        int t = in.nextInt();
        for (int x = 1; x <= t; x++) {
            g1 = in.nextInt();
            g2 = in.nextInt();
            g3 = in.nextInt();
            int n = in.nextInt();
            int[][] food = new int[n][3];
            for (int i = 0; i < n; i++) {
                food[i][0] = in.nextInt();
                food[i][1] = in.nextInt();
                food[i][2] = in.nextInt();
            }

            if (check(0, 0, 0, food, 0)) {
                out.println("Case #" + x + ": yes");
                System.out.println("Case #" + x + ": yes");
            } else {
                out.println("Case #" + x + ": no");
                System.out.println("Case #" + x + ": no");
            }
        }
    }

    private boolean check(int t1, int t2, int t3, int[][] food, int index) {
        if (index >= food.length || t1 > g1 || t2 > g2 || t3 > g3) {
            return false;
        }
        int[] f = food[index];
        if (t1 + f[0] == g1 && t2 + f[1] == g2 && t3 + f[2] == g3) {
            return true;
        }

        return check(t1, t2, t3, food, index + 1) || check(t1 + f[0], t2 + f[1], t3 + f[2], food, index + 1);
    }

    public void run() {
        try {
            in = new FastScanner(new File("/home/yuantian/IdeaProjects/MyAlg/HackerCup_Facebook/src/hc2015/Qualification/NewYearResolution.in"));
            out = new PrintWriter(new File("/tmp/test/NewYearResolution.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new NewYearResolution().run();
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
