package problemset518;

/**
 * Created by yuantian on 3/3/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class ArthurAndQuestions518E {
    static private long NOTFOUND = 10000000000L;

    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = (n + k - 1) / k;
        long[][] ans = new long[k][m + 2];
        for (int i = 0; i < n; i++) {
            String str = in.nextString();
            if (str.equals("?")) {
                ans[i % k][i / k + 1] = NOTFOUND;
            } else {
                ans[i % k][i / k + 1] = Long.parseLong(str);
            }
        }
        for (int i = 0; i < k; i++) {
            ans[i][0] = -NOTFOUND;
            ans[i][m + 1] = NOTFOUND + 1000000L;
        }

        for (int i = n; i < (m * k); i++) {
            ans[i % k][m] = NOTFOUND + 1;
        }

        for (int i = 0; i < k; i++) {
            int idx = 0;
            for (int j = 1; j <= m + 1; j++) {
                if (ans[i][j] != NOTFOUND) {
                    if (ans[i][j] - ans[i][idx] >= j - idx) {
                        fill(ans[i], idx, j);
                        idx = j;
                    } else {
                        out.println("Incorrect sequence");
                        return;
                    }
                }
            }
        }

        for (int j = 1; j <= m + 1; j++) {
            for (int i = 0; i < k; i++) {
                if (ans[i][j] < NOTFOUND)
                    out.print(ans[i][j] + " ");
            }
        }
        out.println();
    }

    static void fill(long[] ans, int from, int to) {
        if (to - from == 1)
            return;
        if (ans[to] <= 0) {
            long x = ans[to] - 1;
            for (int i = to - 1; i > from; i--)
                ans[i] = x--;
        } else if (ans[from] >= 0) {
            long x = ans[from] + 1;
            for (int i = from + 1; i < to; i++)
                ans[i] = x++;
        } else {
            int count = to - from - 1;
            if (ans[from] >= -count / 2) {
                long x = ans[from] + 1;
                for (int i = from + 1; i < to; i++)
                    ans[i] = x++;
            } else if (ans[to] <= count / 2) {
                long x = ans[to] - 1;
                for (int i = to - 1; i > from; i--)
                    ans[i] = x--;
            } else {
                long x = -count / 2;
                for (int i = from + 1; i < to; i++)
                    ans[i] = x++;
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