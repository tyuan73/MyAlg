package problemset515;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/10/15
 * Time: 10:42 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Stack;

public class DrazilAndTiles515D {
    static class Cell {
        public int r, c;

        public Cell(int a, int b) {
            this.r = a;
            this.c = b;
        }
    }

    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] map = new char[n + 2][m + 2];
        for (int i = 0; i < n; i++) {
            System.arraycopy(in.nextString().toCharArray(), 0, map[i + 1], 1, m);
        }

        int count = 0;
        Stack<Cell> ones = new Stack<Cell>();

        int[][] degree = new int[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] != '.') continue;
                if (map[i][j + 1] == '.') {
                    degree[i][j]++;
                    degree[i][j + 1]++;
                }
                if (map[i + 1][j] == '.') {
                    degree[i][j]++;
                    degree[i + 1][j]++;
                }
                count++;
                if (degree[i][j] == 1)
                    ones.push(new Cell(i, j));
            }
        }

        while (ones.size() > 0) {
            Cell c = ones.pop();
            if (map[c.r][c.c] != '.') {
                continue;
            }
            if (degree[c.r][c.c] != 1) {
                break;
            }
            if (map[c.r][c.c - 1] == '.') {
                map[c.r][c.c - 1] = '<';
                map[c.r][c.c] = '>';
                decreace(degree, c.r, c.c - 1, ones);
            } else if (map[c.r][c.c + 1] == '.') {
                map[c.r][c.c + 1] = '>';
                map[c.r][c.c] = '<';
                decreace(degree, c.r, c.c + 1, ones);
            } else if (map[c.r + 1][c.c] == '.') {
                map[c.r + 1][c.c] = 'v';
                map[c.r][c.c] = '^';
                decreace(degree, c.r + 1, c.c, ones);
            } else if (map[c.r - 1][c.c] == '.') {
                map[c.r - 1][c.c] = '^';
                map[c.r][c.c] = 'v';
                decreace(degree, c.r - 1, c.c, ones);
            }
            count -= 2;
        }

        if (count != 0) {
            out.println("Not unique");
        } else {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++)
                    out.print(map[i][j]);
                out.println();
            }
        }
    }

    static void decreace(int[][] degree, int r, int c, Stack<Cell> s) {
        degree[r][c] = 0;
        degree[r - 1][c]--;
        if (degree[r - 1][c] == 1) {
            s.push(new Cell(r - 1, c));
        }
        degree[r + 1][c]--;
        if (degree[r + 1][c] == 1) {
            s.push(new Cell(r + 1, c));
        }
        degree[r][c - 1]--;
        if (degree[r][c - 1] == 1) {
            s.push(new Cell(r, c - 1));
        }
        degree[r][c + 1]--;
        if (degree[r][c + 1] == 1) {
            s.push(new Cell(r, c + 1));
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