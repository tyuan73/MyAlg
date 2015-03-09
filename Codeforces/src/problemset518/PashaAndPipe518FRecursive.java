package problemset518;

/**
 * Created by yuantian on 3/4/15.
 */

/*
  This is recursive solution which exceeds time limit (4 seconds).
*/

import java.util.*;
import java.io.*;

public class PashaAndPipe518FRecursive {
    static long count = 0;
    static int[][] map = null;
    static int n = 0;
    static int m = 0;

    static void go() {
        n = in.nextInt();
        m = in.nextInt();
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = in.nextString();
            for (int j = 0; j < m; j++) {
                if (line.charAt(j) == '.')
                    map[i][j] = 2;
                else
                    map[i][j] = 0;
            }
        }

        map[0][0] = map[0][m - 1] = map[n - 1][0] = map[n - 1][m - 1] = 0;

        count = 0;
        // dir : 0 = down, 1 = up, 2 = left, 3 = right
        for (int i = 1; i < m - 1; i++) {
            if (map[0][i] != 0) {
                map[0][i] = 1;
                dfs(0, 1, i, 0);
                map[0][i] = 2;
            }
            if (map[n - 1][i] != 0) {
                map[n - 1][i] = 1;
                dfs(1, n - 2, i, 0);
                map[n - 1][i] = 2;
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (map[i][0] != 0) {
                map[i][0] = 1;
                dfs(3, i, 1, 0);
                map[i][0] = 2;
            }
            if (map[i][m - 1] != 0) {
                map[i][m - 1] = 1;
                dfs(2, i, m - 2, 0);
                map[i][m - 1] = 2;
            }
        }

        out.println(count / 2);
    }

    static void dfs(int dir, int r, int c, int turn) {

        if (r < 0 || r >= n || c < 0 || c >= m || map[r][c] == 0)
            return;
        if (dir <= 1 && (map[r][c - 1] == 1 || map[r][c + 1] == 1)) {
            // from up or down
            return;
        }
        if (dir >= 2 && (map[r - 1][c] == 1 || map[r + 1][c] == 1)) {
            // from left or right
            return;
        }
        if (r == 0 || r == n - 1 || c == 0 || c == m - 1) {
            count++;
            return;
        }
        map[r][c] = 1;
        switch (dir) {
            case 0:
                dfs(0, r + 1, c, turn);
                if (turn <= 1) {
                    dfs(2, r, c - 1, turn + 1);
                    dfs(3, r, c + 1, turn + 1);
                }
                break;
            case 1:
                dfs(1, r - 1, c, turn);
                if (turn <= 1) {
                    dfs(2, r, c - 1, turn + 1);
                    dfs(3, r, c + 1, turn + 1);
                }
                break;
            case 2:
                dfs(2, r, c - 1, turn);
                if (turn <= 1) {
                    dfs(0, r + 1, c, turn + 1);
                    dfs(1, r - 1, c, turn + 1);
                }
                break;
            case 3:
                dfs(3, r, c + 1, turn);
                if (turn <= 1) {
                    dfs(0, r + 1, c, turn + 1);
                    dfs(1, r - 1, c, turn + 1);
                }
                break;
        }
        map[r][c] = 2;
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