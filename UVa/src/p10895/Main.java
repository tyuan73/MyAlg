package p10895;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/17/15
 * Time: 10:48 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;

class Main {
    static void go() {
        int m, n;

        while(true) {
            try {
                m = in.nextInt();
                n = in.nextInt();
            } catch (Exception e) {
                break;
            }
            ArrayList<Integer>[] rows = new ArrayList[n];
            ArrayList<Integer>[] val = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                rows[i] = new ArrayList<>();
                val[i] = new ArrayList<>();
            }

            for (int i = 1; i <= m; i++) {
                int r = in.nextInt();
                if (r == 0) {
                    in.nextLine();
                    continue;
                }
                int[][] data = new int[r][2];
                for (int j = 0; j < r; j++) {
                    data[j][0] = in.nextInt() - 1;
                }
                for (int j = 0; j < r; j++) {
                    data[j][1] = in.nextInt();
                }
                for (int j = 0; j < r; j++) {
                    rows[data[j][0]].add(i);
                    val[data[j][0]].add(data[j][1]);
                }
            }

            // output
            out.println(n + " " + m);
            for (int i = 0; i < n; i++) {
                out.print(rows[i].size());
                for (int j = 0; j < rows[i].size(); j++)
                    out.print(" " + rows[i].get(j));
                out.println();
                for (int j = 0; j < val[i].size(); j++)
                    if (j == 0)
                        out.print(val[i].get(j));
                    else
                        out.print(" " + val[i].get(j));
                out.println();
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