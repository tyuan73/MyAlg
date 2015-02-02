package hc2015.Round1;/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/1/15
 * Time: 11:12 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.*;
import java.util.StringTokenizer;

public class Homework {
    FastScanner in;
    PrintWriter out;
    int MAX = 10000000;

    public void solve() throws IOException {
        int t = in.nextInt();
        int[] num = new int[MAX + 1];
        int[] count = new int[9];
        int[][] q = new int[9][4000000];
        for (int i = 2; i <= MAX; i++) {
            if (num[i] == 0) {
                num[i] = 1;
                for (int j = i * 2; j <= MAX; j += i) {
                    num[j]++;
                }
            }
            q[num[i]][++count[num[i]]] = i;
        }

        for (int x = 1; x <= t; x++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int k = in.nextInt();

            if (k < 1 || k > 8) {
                out.println("Case #" + x + ": 0");
                System.out.println("Case #" + x + ": 0");
            } else {
                int l1 = 0, r1 = count[k];
                while (l1 < r1) {
                    int mid = (l1 + r1) / 2;
                    if (q[k][mid] < a) {
                        l1 = mid + 1;
                    } else {
                        r1 = mid;
                    }
                }

                int l2 = 0, r2 = count[k];
                while (l2 < r2) {
                    int mid = (l2 + r2 + 1) / 2;
                    if (q[k][mid] > b) {
                        r2 = mid - 1;
                    } else {
                        l2 = mid;
                    }
                }

                out.println("Case #" + x + ": " + (l2 - l1 + 1));
                System.out.println("Case #" + x + ": " + (l2 - l1 + 1));
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("/Users/yuantian/MyAlg/HackerCup_Facebook/src/hc2015/Round1/Homework.in"));
            out = new PrintWriter(new File("/tmp/test/Homework.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new Homework().run();
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
