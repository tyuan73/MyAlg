package weekly.week5.day3;

/**
 * Created by yuantian on 6/20/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class KunduAndTree2 {
    static List<Integer>[] tree = null;
    static int[] group = null;
    static boolean[] visited = null;
    static long P = 1000000007;

    static void go() {
        int n = in.nextInt();

        tree = new List[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < n-1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            if (in.nextString().charAt(0) == 'b') {
                tree[from].add(to);
                tree[to].add(from);
            }
        }

        group = new int[n];
        visited = new boolean[n];
        int gid = 0;
        long c1 = 0, c2 = 0, c3 = 0;
        for(int i = 0; i < n; i++) {
            if (!visited[i]) {
                checkGroup(i, gid);
                c3 += group[gid] * c2;
                c2 += group[gid] * c1;
                c1 += group[gid];
                gid++;
            }
        }
        out.println(c3 % P);
    }

    static void checkGroup(int from, int gid) {
        visited[from] = true;
        group[gid]++;
        for(int next : tree[from]) {
            if (!visited[next]) {
                checkGroup(next, gid);
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