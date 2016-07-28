package problemset650;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/21/16
 * Time: 9:34 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class TableCompression650C {
    static class Ele implements Comparable<Ele> {
        int val, col, row;

        Ele(int v, int r, int c) {
            this.val = v;
            this.row = r;
            this.col = c;
        }

        public int compareTo(Ele e) {
            return this.val - e.val;
        }
    }

    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        Ele[] table = new Ele[n * m];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int v = in.nextInt();
                table[idx++] = new Ele(v, i, j);
            }
        }

        Arrays.sort(table);
        int[][] ans = new int[n][m];
        int[] lastRow = new int[n], lastCol = new int[m];
        int from = 0, to = 1;
        while (from < n * m) {
            while (to < n * m && table[to].val == table[from].val) to++;
            int value = 0;
            for (int i = from; i < to; i++) {
                int max = Math.max(lastRow[table[i].row], lastCol[table[i].col]);
                value = Math.max(value, max + 1);
            }
            for (int i = from; i < to; i++) {
                ans[table[i].row][table[i].col] = value;
                lastRow[table[i].row] = value;
                lastCol[table[i].col] = value;
            }
            from = to;
            to++;
        }

        for (int[] r : ans) {
            for (int x : r)
                out.print(x + " ");
            out.println();
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