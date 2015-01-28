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
            char[] str = in.next().toCharArray();
            int to = 0;
            char x = str[0];
            for (int i = 1; i < str.length; i++) {
                if (str[i] > '0' && str[i] <= x) {
                    x = str[i];
                    to = i;
                }
            }
            StringBuilder sb = new StringBuilder();
            char[] ans = str.clone();
            char temp = ans[0];
            ans[0] = ans[to];
            ans[to] = temp;
            sb.append(ans).append(" ");

            for (int i = 1; i < str.length; i++) {
                if (str[i] >= x) {
                    x = str[i];
                    to = i;
                }
            }
            ans = str.clone();
            temp = ans[0];
            ans[0] = ans[to];
            ans[to] = temp;
            sb.append(ans);
            out.println("Case #" + k + ": " + sb.toString());
            System.out.println("Case #" + k + ": " + sb.toString());
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("/Users/yuantian/MyAlg/HackerCup_Facebook/src/hc2015/Qualification/CookingTheBooks.in"));
            out = new PrintWriter(new File("CookingTheBooks.out"));

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
