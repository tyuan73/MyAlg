package problemset555;

/**
 * Created by yuantian on 8/28/15.
 */

import java.util.*;
import java.io.*;

public class CaseOfFugitive555B {
    static class Range implements Comparable<Range> {
        long min, max;
        int idx;

        Range(long a, long b, int i) {
            this.min = a;
            this.max = b;
            this.idx = i;
        }

        public int compareTo(Range r) {
            return Long.compare(this.min, r.min);
        }
    }

    static class Bridge implements Comparable<Bridge> {
        long len;
        int idx;

        Bridge(long a, int i) {
            this.len = a;
            this.idx = i;
        }

        public int compareTo(Bridge b) {
            return Long.compare(this.len, b.len);
        }
    }

    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();

        if (m < n - 1) {
            out.println("No");
            return;
        }

        Range[] range = new Range[n - 1];
        Bridge[] bridge = new Bridge[m];
        long preL = in.nextLong(), preR = in.nextLong();
        for (int i = 0; i < n - 1; i++) {
            long l = in.nextLong(), r = in.nextLong();
            range[i] = new Range(l - preR, r - preL, i);
            preL = l;
            preR = r;
        }

        for (int i = 0; i < m; i++) {
            bridge[i] = new Bridge(in.nextLong(), i + 1);
        }

        Arrays.sort(range);
        Arrays.sort(bridge);
        PriorityQueue<Range> pq = new PriorityQueue<>(2 * n, new Comparator<Range>() {
            @Override
            public int compare(Range r1, Range r2) {
                return Long.compare(r1.max, r2.max);
            }
        });

        int[] ans = new int[n - 1];
        int index = 0;
        int matched = 0;
        for (Bridge b : bridge) {
            while (index < n - 1 && b.len >= range[index].min) {
                pq.add(range[index++]);
            }
            if (!pq.isEmpty()) {
                Range rg = pq.poll();
                if (b.len <= rg.max) {
                    matched++;
                    ans[rg.idx] = b.idx;
                } else {
                    out.println("No");
                    return;
                }
            }
        }

        if (matched == n - 1) {
            out.println("Yes");
            for (int a : ans) {
                out.print(a + " ");
            }
            out.println();
        } else {
            out.println("No");
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