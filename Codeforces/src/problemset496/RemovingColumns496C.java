package problemset496;

/**
 * Created by yuantian on 12/18/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class RemovingColumns496C {
    static int total = 0;
    static int pre = 0;
    static char[][] str = null;
    static int n = 0;
    static int m = 0;

    static void go() {
        n = in.nextInt();
        m = in.nextInt();
        str = new char[n][m + 1];
        for (int i = 0; i < n; i++) {
            char[] line = in.nextString().toCharArray();
            for (int j = 1; j <= m; j++)
                str[i][j] = line[j - 1];
        }
        total = 0;
        pre = 0;
        check(1);
        out.println(total);
    }

    static void check(int index) {
        if (index > m)
            return;
        int start = 0, end = 1;
        while (start < n) {
            while (end < n && str[end][pre] == str[end - 1][pre]) end++;
            for (int i = start + 1; i < end; i++) {
                if (str[i][index] < str[i - 1][index]) {
                    total++;
                    check(index + 1);
                    return;
                }
            }
            start = end;
            end++;
        }
        pre = index;
        check(index + 1);
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