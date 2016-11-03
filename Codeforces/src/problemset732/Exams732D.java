package problemset732;

/**
 * Created by yuantian on 11/2/16.
 */

/*
Input
10 3
0 0 1 2 2 0 2 0 1 3
1 1 4
Participant's output
9
Jury's answer
10
Checker comment
wrong answer expected '10', found '9'
*/

import java.util.*;
import java.io.*;

public class Exams732D {
    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        int d[] = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = in.nextInt() - 1;
        }
        int a[] = in.nextIntArray(m);
        int start = -1;
        int count = 0;
        int last[] = new int[m + 1];
        int next[] = new int[n];
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            if (d[i] != -1) {
                next[i] = n;
                if (last[d[i]] == -1) {
                    count++;
                } else {
                    next[last[d[i]]] = i;
                }
                last[d[i]] = i;
            }
            if (count == m && start == -1) {
                start = i;
            }
        }
        if (start == -1) {
            out.println(-1);
            return;
        }
        int l = start, r = n;
        while (l < r) {
            int mid = (l + r) / 2;
            if (check(a, d, next, mid))
                r = mid;
            else
                l = mid + 1;
        }

        if (l < n)
            out.println(l + 1);
        else
            out.println(-1);
    }

    static boolean check(int[] a, int[] d, int[] next, int idx) {
        long need = 0;
        for (int i = idx; i >= 0; i--) {
            if (d[i] != -1 && next[i] > idx) {
                need += a[d[i]];
            } else {
                need = Math.max(0, need - 1);
            }
        }
        return need == 0;
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