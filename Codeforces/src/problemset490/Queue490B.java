package problemset490;

/**
 * Created by yuantian on 12/4/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Queue490B {
    static int[] order = null;
    static int index = 0;
    static int[] a = null;

    static void go() {
        int n = in.nextInt();

        a = new int[1000001];
        Arrays.fill(a, -2);
        for (int i = 0; i < n; i++) {
            a[in.nextInt()] = in.nextInt();
        }

        order = new int[n];
        int i = 0;
        index = 1;
        while (a[i] > 0) {
            order[index] = a[i];
            a[i] = -1;  // mark as visited
            i = order[index];
            index += 2;
        }
        a[i] = -1; // mark as visited

        index = order[n - 1] == 0 ? n - 1 : n - 2;
        for (i = 1; i < 1000001; i++) {
            if (a[i] >= 0) {
                dfs(i);
            }
        }

        for (int x : order)
            out.print(x + " ");
        out.println();
    }

    static void dfs(int i) {
        int next = a[i];
        a[i] = -1; // mark as visited
        if (next >= 0) {
            dfs(next);
        }

        if (next != -1) {
            order[index] = i;
            index -= 2;
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

input:
5
0 7
92 31
7 141
31 5
141 0

output:
92 7 31 141 5

input:
4
92 31
0 7
31 0
7 141

output:
92 7 31 141

 */