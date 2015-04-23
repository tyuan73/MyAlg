package p10685;

/**
 * Created by yuantian on 4/23/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    static void go() {
        HashMap<String, Integer> map = new HashMap<>();
        while (true) {
            int c = in.nextInt(), r = in.nextInt();
            if (c == 0) break;

            map.clear();
            int num = 0;
            for (int i = 0; i < c; i++) {
                String name = in.nextString();
                map.put(name, num++);
            }

            int[] ds = new int[c];
            int max = 1;
            Arrays.fill(ds, -1);
            for (int i = 0; i < r; i++) {
                int x = map.get(in.nextString());
                int y = map.get(in.nextString());
                x = root(ds, x);
                y = root(ds, y);
                if (x != y) {
                    max = Math.max(max, join(ds, x, y));
                }
            }
            out.println(max);
        }
    }

    static int join(int[] ds, int a, int b) {
        if (ds[a] < ds[b]) {
            ds[a] += ds[b];
            ds[b] = a;
            return -ds[a];
        } else {
            ds[b] += ds[a];
            ds[a] = b;
            return -ds[b];
        }
    }

    static int root(int[] ds, int a) {
        return ds[a] < 0 ? a : (ds[a] = root(ds, ds[a]));
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