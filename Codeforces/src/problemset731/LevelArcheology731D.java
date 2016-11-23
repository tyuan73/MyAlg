package problemset731;

/**
 * Created by yuantian on 11/23/16.
 */

/*

*/

import java.util.*;
import java.io.*;

public class LevelArcheology731D {
    static int[] mask = null;
    static int c = 0;

    static void go() {
        int n = in.nextInt();
        c = in.nextInt();

        int[] first = new int[500004];
        int[] second = new int[500004];
        int l1 = in.nextInt();
        for (int i = 0; i < l1; i++) {
            first[i] = in.nextInt() - 1;
        }
        mask = new int[c + 1];
        for (int i = 0; i < n - 1; i++) {
            int l2 = in.nextInt();

            for (int j = 0; j < l2; j++)
                second[j] = in.nextInt() - 1;

            if (!check(first, l1, second, l2)) {
                out.println(-1);
                return;
            }
            int[] temp = first;
            first = second;
            second = temp;
            l1 = l2;
        }

        int total = 0;
        for (int i = 0; i < c; i++) {
            total += mask[i];
            if (total == n - 1) {
                out.println(i);
                return;
            }
        }
        out.println(-1);
    }

    static boolean check(int[] first, int l1, int[] second, int l2) {
        for (int i = 0; i < Math.min(l1, l2); i++) {
            if (first[i] == second[i]) continue;
            if (first[i] < second[i])
                mask[0]++;
            mask[c - second[i]]--;
            mask[c - first[i]]++;
            return true;
        }
        if (l1 <= l2) {
            mask[0]++;
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