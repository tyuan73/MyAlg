package weekly.week6.day5;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 7/4/14
 * Time: 7:24 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

*/

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class DynamicSummation {
    static List<Integer>[] tree = null;
    static boolean[] visited = null;
    static BigInteger[] values = null;

    static void go() {
        int n = in.nextInt();
        tree = new List[n];
        for(int i = 0; i < n; i++)
            tree[i] = new ArrayList<Integer>();

        values = new BigInteger[n];
        for(int i = 0; i < n; i++)
            values[i] = BigInteger.ZERO;

        for(int i = 0; i < n-1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            tree[from].add(to);
            tree[to].add(from);

        }

        visited = new boolean[n];
        int q = in.nextInt();
        while(q-- > 0) {
            String op = in.nextString();
            int r = in.nextInt()-1;
            int t = in.nextInt()-1;

            Arrays.fill(visited, false);
            dfs(r, t);
            if (op.charAt(0) == 'U') {
                Long a = in.nextLong();
                Long b = in.nextLong();
                BigInteger inc = calc(a, b).add(calc(a+1, b)).add(calc(b+1,a));
                increase(t, inc);
            } else {
                BigInteger m = new BigInteger(in.nextString());
                BigInteger sum = sumup(t);
                //out.println("sum = " + sum.toString());
                out.println(sum.mod(m));
            }
        }
    }

    static BigInteger sumup(int t) {
        BigInteger ret = values[t];
        visited[t] = true;
        for(int next : tree[t]) {
            if (!visited[next])
                ret = ret.add(sumup(next));
        }
        return ret;
    }

    static void increase(int t, BigInteger inc) {
        visited[t] = true;
        values[t] = values[t].add(inc);
        for(int next : tree[t]) {
            if (!visited[next])
                increase(next, inc);
        }
    }

    static BigInteger calc(Long l1, Long l2) {
        BigInteger b = BigInteger.ONE;
        BigInteger x = new BigInteger(l1.toString());
        long l = l2.longValue();
        while(l > 0) {
            if ((l & 1) > 0)
                b = b.multiply(x);
            x = x.multiply(x);
            l >>= 1;
        }
        return b;
    }

    static boolean dfs(int from, int to) {
        visited[from] = true;
        if (from == to) {
            return true;
        }
        for(int next : tree[from]) {
            if (!visited[next])
                if (dfs(next, to))
                    return true;
        }
        return false;
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