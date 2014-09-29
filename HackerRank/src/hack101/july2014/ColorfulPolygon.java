package hack101.july2014;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 7/27/14
 * Time: 10:26 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

*/

import java.util.*;
import java.io.*;

public class ColorfulPolygon {
    static long P = 1000000007;
    static void go() {
        int n = in.nextInt();
        long[] fb = new long[100001];
        fb[0] = 1;
        fb[1] = 2;
        for(int i = 2; i <= 100000; i++) {
            fb[i] = fb[i - 2] + fb[i - 1];
            if (fb[i] > P)
                fb[i] -= P;
        }

        int[] a = new int[n];
        for(int i = 0; i < n; i++)
            a[i] = in.nextInt();

        ArrayList<Integer> group = new ArrayList<Integer>();
        int preCol = a[0], preCount = 1;
        for(int i = 1; i < n; i++) {
            if (a[i] != preCol) {
                group.add(preCount);
                preCol = a[i];
                preCount = 1;
            } else {
                preCount++;
            }
        }
        group.add(preCount);

        if (a[n-1] == a[0] && group.size() > 1) {
            group.set(0, group.get(0) + group.get(group.size() - 1));
            group.remove(group.size()-1);
        }

        long ans = 1;
        for(int x : group) {
            ans *= fb[x];
            ans %= P;
        }

        if (group.size() == n) {
            ans -= n+1;
        } else if (group.size() == n-1) {
            ans -= 2;
        } else if (group.size() == 1) {
            int x = group.get(0);
            ans = fb[x] - fb[x-2] + fb[x-3];
        }
        if (ans < 0)
            ans += P;
        out.println(ans);
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

/*
case #1
input:
5
4 5 3 2 5

output:
25

Case #2
input:
5
3 2 1 5 3

output:
22

case #3
input:
5
4 2 1 5 3

output:
26
 */