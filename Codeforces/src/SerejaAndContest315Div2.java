/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 6/8/13
 * Time: 11:35 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SerejaAndContest315Div2 {
    public static void main(String[] args) {
        FastInputReader in = new FastInputReader(System.in);

        int n = in.nextInt();
        int k = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();

        int count = n, index = 0;
        long total = 0;
        for (int i = 1; i < n; i++) {
            long d = total + a[i - 1] * index - a[i] * (index + 1) * (count - index - 2);
            if (d < k) {
                System.out.println(i + 1);
                count--;
            } else {
                total += a[i - 1] * index;
                index++;
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
