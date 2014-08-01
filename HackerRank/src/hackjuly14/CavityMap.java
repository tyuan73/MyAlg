package hackjuly14;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 7/26/14
 * Time: 10:52 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

*/

import java.util.*;
import java.io.*;

public class CavityMap {
    static void go() {
        int n = in.nextInt();

        char[][] map = new char[n][n];
        for(int i = 0; i < n; i++) {
            map[i] = in.nextString().toCharArray();
        }

        int[][] adj = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        for(int i = 1; i <= n-2; i++)
            for(int j = 1; j <= n-2; j++) {
                boolean isCave = true;
                for(int[] a : adj) {
                    // 'X' > '0' - '9'
                    if (map[i+a[0]][j+a[1]] >= map[i][j]) {
                        isCave = false;
                        break;
                    }
                }
                if (isCave)
                    map[i][j] = 'X';
            }

        for(char[] x: map)
            out.println(new String(x));
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