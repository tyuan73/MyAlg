package p11959;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 6/1/15
 * Time: 10:26 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

public class Main1 {
    static int[][][] C = {
            {{5, 2}, {2, 3}, {3, 4}, {4, 5}},
            {{4, 3}, {5, 4}, {2, 5}, {3, 2}},
            {{1, 3}, {3, 0}, {0, 5}, {5, 1}},
            {{0, 2}, {2, 1}, {1, 4}, {4, 0}},
            {{5, 0}, {1, 5}, {3, 1}, {0, 3}},
            {{4, 1}, {0, 4}, {2, 0}, {1, 2}}
    };

    static Comp comp = new Comp();

    static class Comp implements Comparator<char[]> {
        public int compare(char[] o1, char[] o2) {
            return o1[0] - o2[0];
        }
    }

    static void go() {
        int t = in.nextInt();


        while (t-- > 0) {
            char[] d1 = in.nextString().toCharArray();
            char[] d2 = in.nextString().toCharArray();

            if (!check1(d1, d2)) {
                out.println("Not Equal");
                continue;
            }
            if (!check2(d1, d2)) {
                out.println("Not Equal");
                continue;
            }

            if (check3(d1, d2)) {
                out.println("Equal");
                continue;
            }

            out.println("Not Equal");
        }
    }

    static boolean check3(char[] d1, char[] d2) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (d2[i] == d1[0] && d2[C[i][j][0]] == d1[5] && d2[C[i][j][1]] == d1[2])
                    return true;
            }
        }
        return false;
    }

    static boolean check2(char[] d1, char[] d2) {
        char[][] dig1 = {{d1[0], d1[1]}, {d1[2], d1[4]}, {d1[3], d1[5]}};
        char[][] dig2 = {{d2[0], d2[1]}, {d2[2], d2[4]}, {d2[3], d2[5]}};
        for (int i = 0; i < 3; i++) {
            if (dig1[i][0] > dig1[i][1]) {
                char ch = dig1[i][0];
                dig1[i][0] = dig1[i][1];
                dig1[i][1] = ch;
            }
            if (dig2[i][0] > dig2[i][1]) {
                char ch = dig2[i][0];
                dig2[i][0] = dig2[i][1];
                dig2[i][1] = ch;
            }
        }
        Arrays.sort(dig1, comp);
        Arrays.sort(dig2, comp);
        for (int i = 0; i < 3; i++) {
            if (dig1[i][0] != dig2[i][0] || dig1[i][1] != dig2[i][1])
                return false;
        }
        return true;
    }

    static boolean check1(char[] d1, char[] d2) {
        int[] dig1 = new int[10];
        int[] dig2 = new int[10];
        for (char c : d1) {
            dig1[c - '0']++;
        }
        for (char c : d2) {
            dig2[c - '0']++;
        }
        for (int i = 0; i < 10; i++) {
            if (dig1[i] != dig2[i])
                return false;
        }
        return true;
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