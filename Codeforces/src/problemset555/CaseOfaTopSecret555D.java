package problemset555;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 9/2/15
 * Time: 9:59 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

public class CaseOfaTopSecret555D {
    static class Pair {
        int len, idx;

        Pair(int a, int b) {
            this.len = a;
            this.idx = b;
        }
    }

    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] pegs = in.nextIntArray(n);
        ArrayList<Pair> list = new ArrayList<>();
        //list.add(new Pair(0, 1));
        for (int i = 1; i < n; i++) {
            //list.add(new Pair(pegs[i] - 1, i));
            int len = pegs[i] - pegs[i - 1];
            for (int j = i - 2; j >= 0; j--) {
                int len1 = pegs[i - 1] - pegs[j];
                if (len1 >= len) break;
                list.add(new Pair(2 * pegs[i - 1] - pegs[j] - 1, j + 2));
            }

        }
        int last = pegs[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            //list.add(new Pair(2 * last - pegs[i] - 1, i + 2));
            int len = pegs[i + 1] - pegs[i];
            for (int j = i + 2; j < n; j++) {
                int len1 = pegs[j] - pegs[i + 1];
                if (len1 >= len) break;
                list.add(new Pair(2 * pegs[j] - pegs[i + 1] - 1, j + 2));
            }
        }

        for(Pair p : list) {
            out.println(p.len + " " + p.idx);
        }

        for (int i = 0; i < m; i++) {
            int idx = in.nextInt();
            int len = in.nextInt();
            int total = pegs[idx-1] + len;

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