package hc2015.Qualification;/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/27/15
 * Time: 10:21 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.*;
import java.util.*;

public class NewYearResolution {
    FastScanner in;
    PrintWriter out;

    public void solve() throws IOException {

    }

    public void run() {
        try {
            in = new FastScanner(new File("/Users/yuantian/MyAlg/HackerCup_Facebook/src/hc2015/Qualification/NewYearResolution.in"));
            out = new PrintWriter(new File("/tmp/test/NewYearResolution.out"));

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
