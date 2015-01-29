package hc2015.Qualification;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/27/15
 * Time: 10:20 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.*;
import java.util.StringTokenizer;

public class CookingTheBooks {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {
        int t = in.nextInt();

        for (int k = 1; k <= t; k++) {
            String str = in.next();
            int n = str.length();
            char[] ans = str.toCharArray();

            /* first, find the smallest */
            /**
             * Check if the first (most significant) digit can be swapped.
             * The digit it can change to must satisfy these conditions:
             *  1. greater than 0
             *  2. the smallest in whole string
             *  3. if not unique, the last one is selected
             *
             * If the digit is the same as the first, don't swap.
             */
            int to = 0;
            char x = ans[0];
            for (int i = 1; i < n; i++) {
                if (ans[i] > '0' && ans[i] <= x) {
                    x = ans[i];
                    to = i;
                }
            }
            StringBuilder sb = new StringBuilder();

            if (ans[0] > ans[to]) { /* yes, the first digit can be swap with a smaller one */
                swap(ans, 0, to);
            } else {
                int[] min = new int[n];
                min[n - 1] = n - 1;
                for (int i = n - 2; i > 0; i--) {
                    if (ans[i] >= ans[min[i + 1]])
                        min[i] = min[i + 1];
                    else
                        min[i] = i;
                }
                for (int i = 1; i < n - 1; i++) {
                    if (ans[i] > ans[min[i]]) {
                        swap(ans, i, min[i]);
                        break;
                    }
                }
            }
            sb.append(ans).append(" ");

            /* now, find the largest */
            ans = str.toCharArray();
            int[] max = new int[n];
            max[n - 1] = n - 1;
            for (int i = n - 2; i >= 0; i--) {
                if (ans[i] <= ans[max[i + 1]])
                    max[i] = max[i + 1];
                else
                    max[i] = i;
            }
            for (int i = 0; i < n - 1; i++) {
                if (ans[i] < ans[max[i]]) {
                    swap(ans, i, max[i]);
                    break;
                }
            }
            sb.append(ans);

            /* output */
            out.println("Case #" + k + ": " + sb.toString());
            System.out.println("Case #" + k + ": " + sb.toString());
        }
    }

    private void swap(char[] c, int a, int b) {
        char temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }

    public void run() {
        try {
            in = new FastScanner(new File("/Users/yuantian/MyAlg/HackerCup_Facebook/src/hc2015/Qualification/CookingTheBooks.in"));
            out = new PrintWriter(new File("/tmp/test/CookingTheBooks.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new CookingTheBooks().run();
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
