/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 6/8/13
 * Time: 11:05 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SerejaAndArray315Div2 {
    public static void main(String[] args) {
        FastInputReader in = new FastInputReader(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();

        int add = 0;
        while (m-- > 0) {
            int op = in.nextInt();
            if (op == 1) {
                int index = in.nextInt() - 1;
                int v = in.nextInt();
                a[index] = -add + v;
            } else if (op == 2) {
                int inc = in.nextInt();
                add += inc;
            } else {
                int index = in.nextInt() - 1;
                System.out.println(a[index] + add);
            }
        }
    }

    static class FastInputReader {
        BufferedReader br;
        StringTokenizer st = null;

        public FastInputReader(InputStream str) {
            br = new BufferedReader(new InputStreamReader(str));
        }

        private String getToken() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException exp) {
                    throw new RuntimeException();
                }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(getToken());
        }

        public double nextDouble() {
            return Double.parseDouble(getToken());
        }

        public String nextString() {
            return getToken();
        }
    }
}
