package edu.round38;


/*

 */

import java.util.*;
import java.io.*;

public class D {
    static class Road {
        int d;
        long w;

        Road(int d, long w) {
            this.d = d;
            this.w = w;
        }
    }

    static void go() {
        int n = in.nextInt(), m = in.nextInt();
        //long[][] city = new long[n][2];
        List<Road>[] r = new List[n];
        for (int i = 0; i < n; i++)
            r[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            long w = in.nextLong();
            r[from].add(new Road(to, w));
            r[to].add(new Road(from, w));
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> (a[0] > b[0] ? 1 : a[0] == b[0] ? 0 : -1));
        /*
        PriorityQueue<long[]> pq = new PriorityQueue<>(
                new Comparator<long[]>() {
                    public int compare(long[] a, long[] b) {
                        if (a[0] == b[0]) return 0;
                        return a[0] < b[0] ? -1 : 1;
                    }
                }
        );
        */
        for (int i = 0; i < n; i++) {
            long[] city = {in.nextLong(), i};
            pq.add(city);
        }

        long[] ans = new long[n];
        Arrays.fill(ans, Long.MAX_VALUE);
        while (pq.size() > 0) {
            long[] city = pq.poll();
            if (city[0] < ans[(int) city[1]]) {
                int id = (int) city[1];
                ans[id] = city[0];
                for (Road next : r[id]) {
                    long[] r1 = {ans[id] + 2 * next.w, next.d};
                    pq.add(r1);
                }
            }
        }
        for (long x : ans)
            out.print(x + " ");
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

        public String nextLine() {
            StringBuilder sb = new StringBuilder(1024);
            int c = read();
            while (!(c == '\n' || c == '\r' || c == -1)) {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        public char nextChar() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            return (char) c;
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