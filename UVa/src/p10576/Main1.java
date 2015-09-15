package p10576;

/**
 * Created by yuantian on 9/15/15.
 */

import java.util.*;
import java.io.*;

public class Main1 {
    static void go() {
        try {
            while (true) {
                int s = in.nextInt();
                int t = in.nextInt();
                int[] amt = new int[12];
                int max = -1;
                for (int mask = (1 << 12); mask > 0; mask--) {
                    for (int i = 0, bit = 1; i < 12; i++, bit <<= 1) {
                        if ((mask & bit) > 0)
                            amt[i] = s;
                        else
                            amt[i] = -t;
                    }
                    if (isValid(amt)) {
                        int sum = 0;
                        for (int x : amt) {
                            sum += x;
                        }
                        max = Math.max(max, sum);
                    }
                }
                if (max >= 0)
                    out.println(max);
                else
                    out.println("Deficit");
            }

        } catch (Exception e) {
        }
    }

    static boolean isValid(int[] amt) {
        int total = 0;
        for (int i = 0; i < 5; i++) {
            total += amt[i];
        }
        if (total > 0) return false;
        for (int i = 5; i < 12; i++) {
            total += amt[i];
            total -= amt[i - 5];
            if (total > 0) return false;
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