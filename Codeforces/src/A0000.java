/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 5/30/13
 * Time: 11:31 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class A0000 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    class FastInputReader {
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

    static void shuffle(int[] a) {
        int n = a.length;
        Random rnd = new Random(System.nanoTime());
        for (int i = 0; i < n; ++i) {
            int v = rnd.nextInt(n - i);
            int tmp = a[i + v];
            a[i + v] = a[i];
            a[i] = tmp;
        }
    }
}
