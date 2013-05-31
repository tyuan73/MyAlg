/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/30/13
 * Time: 11:07 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class IlyaAndMatrix313C {

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

        public long nextLong() {
            return Long.parseLong(getToken());
        }
    }

    public static void main(String[] args) {
        FastInputReader in = new FastInputReader(System.in);

        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();

        shuffle(a);
        Arrays.sort(a);
        for (int i = n - 2; i >= 0; i--)
            a[i] = a[i] + a[i + 1];

        long total = 0;
        for (int i = n; i > 0; i >>= 2) {
            total = total + a[n - i];
        }
        System.out.println(total);
    }

    static void shuffle(long[] a) {
        int n = a.length;
        Random rnd = new Random(System.nanoTime());
        for (int i = 0; i < n; ++i) {
            int v = rnd.nextInt(n - i);
            long tmp = a[i + v];
            a[i + v] = a[i];
            a[i] = tmp;
        }
    }
}
