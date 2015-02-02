package hc2015.Round1;

import java.io.*;
import java.util.*;

/**
 * Created by yuantian on 2/2/15.
 */
public class Autocomplete {
    FastScanner in;
    PrintWriter out;

    class TrieNode {
        TrieNode[] next = null;

        public TrieNode() {
            next = new TrieNode[26];
        }
    }

    public void solve() throws IOException {
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int n = in.nextInt();
            TrieNode root = new TrieNode();
            long ans = 0;
            for (int i = 0; i < n; i++) {
                String s = in.next();
                TrieNode p = root;
                int prefix = 1;
                for (int j = 0; j < s.length(); j++) {
                    int ch = s.charAt(j) - 'a';
                    if (p.next[ch] != null) {
                        prefix++;
                    } else {
                        p.next[ch] = new TrieNode();
                    }
                    p = p.next[ch];
                }
                ans += Math.min(prefix, s.length());
                //System.out.println("ans = " + ans);
            }

            out.println("Case #" + t + ": " + ans);
            System.out.println("Case #" + t + ": " + ans);
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("/tmp/test/autocomplete.full.in"));
            out = new PrintWriter(new File("/tmp/test/Autocomplete.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new Autocomplete().run();
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
