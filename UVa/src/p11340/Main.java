package p11340;

/**
 * Created by yuantian on 4/1/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            int[] value = new int[n];
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String line = in.nextLine();
                map.put(line.charAt(0), i);
                value[i] = Integer.parseInt(line.substring(2));
            }

            int m = in.nextInt();
            int[] count = new int[n];
            for (int i = 0; i < m; i++) {
                String line = in.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    if (map.containsKey(line.charAt(j)))
                        count[map.get(line.charAt(j))]++;
                }
            }

            double ans = 0;
            for (int i = 0; i < n; i++) {
                ans += value[i] * (long) count[i];
            }

            out.printf("%.2f$\n", ans / 100);
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

        public String nextLine() {
            int c = read();
            StringBuilder sb = new StringBuilder(1024);
            do {
                sb.append((char) c);
                c = read();
            } while (!isNewLine(c));
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

        public static boolean isNewLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
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