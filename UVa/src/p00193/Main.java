package p00193;

/**
 * Created by yuantian on 9/21/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    static final int UNKNOWN = 0;
    static final int WHITE = 1;
    static final int BLACK = 2;
    static List<Integer>[] map = null;
    static int n = 0;
    static int[] color = null;
    static int max = 0;
    static int[] ans = null;
    static int cur = 0;

    static void go() {
        int m = in.nextInt();
        while (m-- > 0) {
            n = in.nextInt();
            int k = in.nextInt();
            map = new List[n];
            for (int i = 0; i < n; i++)
                map[i] = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                map[from].add(to);
                map[to].add(from);
            }

            color = new int[n];
            max = 0;
            ans = new int[n];
            cur = 0;
            color(0);

            out.println(max);
            out.print(ans[0]);
            for (int i = 1; i < max; i++) {
                out.print(" " + ans[i]);
            }
            out.println();
        }
    }

    static void color(int idx) {
        if (idx == n) {
            if (cur > max) {
                max = cur;
                int j = 0;
                for (int i = 0; i < n; i++) {
                    if (color[i] == BLACK)
                        ans[j++] = i + 1;
                }
            }
            return;
        }

        switch (check(idx)) {
            case 0:  // has black;
                color[idx] = WHITE;
                color(idx + 1);
                break;
            case 1:  // all white;
                color[idx] = BLACK;
                cur++;
                color(idx + 1);
                cur--;
                break;
            case 2:
                color[idx] = WHITE;
                color(idx + 1);
                color[idx] = BLACK;
                cur++;
                color(idx + 1);
                cur--;
                break;
        }
        color[idx] = UNKNOWN;
    }

    static int check(int idx) {
        boolean hasBlack = false;
        boolean allWhite = true;

        for (int x : map[idx]) {
            if (color[x] == BLACK) {
                hasBlack = true;
                break;
            }
            if (color[x] == UNKNOWN) {
                allWhite = false;
            }
        }

        if (hasBlack)
            return 0;

        if (allWhite)
            return 1;

        return 2;
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