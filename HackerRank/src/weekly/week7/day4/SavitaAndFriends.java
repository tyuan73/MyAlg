package weekly.week7.day4;

/**
 * Created by yuantian on 7/17/14.
 */

/*

*/

import sun.misc.Compare;

import java.util.*;
import java.io.*;

public class SavitaAndFriends {
    static List<City>[] g = null;
    static long[][] shortest = null;

    static class Path implements Comparable<Path> {
        long minLen;
        int id;
        public Path(int id, long minLen) {
            this.id = id; this.minLen = minLen;
        }
        public int compareTo(Path p) {
            long d = this.minLen - p.minLen;
            if (d > 0)
                return 1;
            else if (d == 0)
                return 0;
            return -1;
        }
    }

    static class City {
        int to; long len;
        public City(int id, int len) {
            this.to = id; this.len = len;
        }
    }

    static void go() {
        int t = in.nextInt();
        int a = 0, b = 0, l = 0;

        while(t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            g = new List[n];
            for(int i = 0; i < n; i++) {
                g[i] = new ArrayList<City>();
            }
            for(int i = 1; i <= m; i++) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                int c = in.nextInt();
                g[from].add(new City(to, c));
                g[to].add(new City(from, c));
                if (i == k) {
                    a = from; b = to;
                    l = c;
                }
            }

            shortest = new long[2][n];

            dijkstra(a, 0);
            dijkstra(b, 1);

            /* for test */

            for(long i : shortest[0])
                out.print(i + " ");
            out.println();
            for(long i : shortest[1])
                out.print(i + " ");
            out.println();

            double maxa = 0, maxb = 0;
            for(int i = 0; i < n; i++) {
                if (shortest[0][i] < shortest[1][i]) {
                    maxa = Math.max(maxa, shortest[0][i]);
                } else {
                    maxb = Math.max(maxb, shortest[1][i]);
                }
            }
            double ans = (maxb - maxa + l)/2;
            out.println(String.format("%.5f", ans) + " " + String.format("%.5f", ans + maxa));
        }
    }

    static void dijkstra(int start, int idx) {
        long[] path = shortest[idx];
        Arrays.fill(path, Long.MAX_VALUE);
        PriorityQueue<Path> q = new PriorityQueue<Path>();
        q.add(new Path(start, 0));

        while(q.size() > 0) {
            Path x = q.poll();
            if (x.minLen >= path[x.id])
                continue;

            path[x.id] = x.minLen;
            for(City y : g[x.id]) {
                if (path[x.id] + y.len < path[y.to])
                    q.add(new Path(y.to, path[x.id] + y.len));
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