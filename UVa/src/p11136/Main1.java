package p11136;

/**
 * Created by yuantian on 4/15/15.
 */

/*
    This one does not work. See Main.java
*/

import java.util.*;
import java.io.*;

class Main1 {
    static class DS {
        int[] ele;
        int count;

        DS(int n) {
            this.count = n;
            ele = new int[n + 1];
            init();
        }

        void init() {
            for (int i = 0; i <= count; i++)
                ele[i] = i;
        }

        int get(int i) {
            int p = ele[i];
            if (ele[p] != p) {
                p = get(p);
                ele[i] = p;
            }
            return p;
        }

        void join(int a, int b) {
            if (a > b) {
                join(b, a);
            } else {
                ele[a] = get(b);
            }
        }
    }

    static class Bill implements Comparable<Bill> {
        int val, day;

        Bill(int v, int d) {
            this.val = v;
            this.day = d;
        }

        public int compareTo(Bill b) {
            return this.val - b.val;
        }
    }

    static void go() {
        int n;
        ArrayList<Bill> all = new ArrayList<>();
        while ((n = in.nextInt()) != 0) {
            all.clear();
            for (int i = 0; i < n; i++) {
                int m = in.nextInt();
                while (m-- > 0)
                    all.add(new Bill(in.nextInt(), i));
            }

            Collections.sort(all);

            int[][] prom = new int[n][2];
            DS ds = new DS(n);
            int idx = all.size() - 1, count = 0;
            while (count < n) {
                Bill b = all.get(idx);
                int i = ds.get(b.day);
                if (i < n) {
                    count++;
                    prom[i][0] = b.val;
                    ds.join(i, i + 1);
                }
                idx--;
            }

            ds.init();
            idx = 0;
            count = 0;
            while (count < n) {
                Bill b = all.get(idx);
                int i = ds.get(b.day);
                if (i < n) {
                    count++;
                    prom[i][1] = b.val;
                    ds.join(i, i + 1);
                }
                idx++;
            }

            System.out.println("--------");
            for(int[] x : prom) {
                System.out.println(x[0] + " - " + x[1]);
            }
            System.out.println("--------");

            long total = 0;
            for (int[] pair : prom) {
                total += pair[0] - pair[1];
            }
            out.println(total);
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
                    new InputMismatchException();
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
            while (!(c == '\n' || c == '\r')) {
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

/*
input:
2
2 1 1
2 2 5
0

output:
3
 */