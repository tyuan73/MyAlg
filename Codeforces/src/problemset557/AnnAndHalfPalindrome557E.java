package problemset557;

/**
 * Created by yuantian on 8/21/15.
 */

import java.util.*;
import java.io.*;

public class AnnAndHalfPalindrome557E {
    static int count = 0;
    static char[] ans = null;
    static int k = 0;
    static int latest = 0;

    static class Node {
        Node[] children = new Node[2];
        int count = 0;
    }

    static Node add(Node p, int id) {
        if (p.children[id] == null)
            p.children[id] = new Node();
        return p.children[id];
    }

    static void go() {
        String str = in.nextString();
        k = in.nextInt();
        int n = str.length();
        boolean[][] dp = new boolean[n][];
        for (int i = 0; i < n; i++)
            dp[i] = new boolean[n - i];

        int[] last = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < dp[i].length; j++)
                if (str.charAt(i) == str.charAt(i + j)) {
                    if (j <= 4)
                        dp[i][j] = true;
                    else
                        dp[i][j] = dp[i + 2][j - 4];
                    if (dp[i][j])
                        last[i] = j;
                }
        }
        Node root = new Node();
        for (int i = 0; i < n; i++) {
            Node p = root;
            for (int j = 0; j <= last[i]; j++) {
                p = add(p, str.charAt(i + j) - 'a');
                if (dp[i][j])
                    p.count++;
            }
        }

        count = 0;
        ans = new char[n];

        dfs(root, 0);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= latest; i++)
            sb.append(ans[i]);
        out.println(sb);
    }

    static void dfs(Node r, int index) {
        if (count < k && r.children[0] != null) {
            count += r.children[0].count;
            ans[index] = 'a';
            if (count < k)
                dfs(r.children[0], index + 1);
            else
                latest = index;
        }
        if (count < k && r.children[1] != null) {
            count += r.children[1].count;
            ans[index] = 'b';
            if (count < k)
                dfs(r.children[1], index + 1);
            else
                latest = index;
        }
    }

    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);

        go();

        out.close();
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int[] nextIntArray(int len) {
            int[] ret = new int[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextInt();
            return ret;
        }

        public long[] nextLongArray(int len) {
            long[] ret = new long[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextLong();
            return ret;
        }


        public int nextInt() {
            return (int) nextLong();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String nextLine() {
            StringBuilder sb = new StringBuilder(1024);
            int c = read();
            while (!(c == '\n' || c == '\r' || c == -1)) {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder sb = new StringBuilder(1024);
            do {
                sb.append((char) c);
                c = read();
            } while (!isSpaceChar(c));
            return sb.toString();
        }

        public char[] nextCharArray(int n) {
            char[] ca = new char[n];
            for (int i = 0; i < n; i++) {
                int c = read();
                while (isSpaceChar(c))
                    c = read();
                ca[i] = (char) c;
            }
            return ca;
        }

        public static boolean isSpaceChar(int c) {
            switch (c) {
                case -1:
                case ' ':
                case '\n':
                case '\r':
                case '\t':
                    return true;
                default:
                    return false;
            }
        }
    }
}