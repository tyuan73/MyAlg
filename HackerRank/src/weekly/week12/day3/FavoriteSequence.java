package weekly.week12.day3;

/**
 * Created by yuantian on 11/18/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class FavoriteSequence {
    static void go() {
        int n = in.nextInt();
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] g = new ArrayList[1000001];
        int[] inDeg = new int[1000001];
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            int a = in.nextInt();
            if (g[a] == null) {
                g[a] = new ArrayList<>();
            }
            for (int j = 1; j < k; j++) {
                int b = in.nextInt();
                g[a].add(b);
                inDeg[b]++;
                if (g[b] == null)
                    g[b] = new ArrayList<>();
                a = b;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= 1000000; i++) {
            if (g[i] != null && inDeg[i] == 0)
                pq.add(i);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (pq.size() > 0) {
            int x = pq.poll();
            ans.add(x);
            for (int next : g[x]) {
                inDeg[next]--;
                if (inDeg[next] == 0) {
                    pq.add(next);
                }
            }
        }
        for (int x : ans) {
            out.print(x + " ");
        }
        out.println();
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