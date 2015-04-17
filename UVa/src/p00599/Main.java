package p00599;

/**
 * Created by yuantian on 4/17/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static boolean[] visited = new boolean[26];
    static boolean[] exists = new boolean[26];
    static List<Integer>[] g = new ArrayList[26];
    static int[] group = new int[26];


    static void go() {
        int t = in.nextInt();
        while (t-- > 0) {
            for (int i = 0; i < 26; i++)
                g[i] = new ArrayList<>();

            String line;
            while ((line = in.nextLine()).charAt(0) != '*') {
                int from = line.charAt(1) - 'A';
                int to = line.charAt(3) - 'A';
                g[from].add(to);
                g[to].add(from);
            }

            Arrays.fill(exists, false);
            line = in.nextLine();
            for (int i = 0; i < line.length(); i += 2) {
                int idx = line.charAt(i) - 'A';
                exists[idx] = true;
            }

            Arrays.fill(visited, false);
            Arrays.fill(group, 0);
            int grp = 0;
            for (int i = 0; i < 26; i++) {
                if (exists[i] && !visited[i]) {
                    dfs(i, grp);
                    grp++;
                }
            }
            int c1 = 0, c2 = 0;
            for (int x : group) {
                if (x == 1)
                    c1++;
                else if (x > 1)
                    c2++;
            }
            out.printf("There are %d tree(s) and %d acorn(s).\n", c2, c1);
        }
    }

    static void dfs(int idx, int grp) {
        group[grp]++;
        visited[idx] = true;
        for (int next : g[idx]) {
            if (exists[next] && !visited[next]) {
                dfs(next, grp);
            }
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