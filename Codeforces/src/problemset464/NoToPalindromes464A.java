package problemset464;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 11/9/14
 * Time: 9:32 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class NoToPalindromes464A {
    static void go() {
        int n = in.nextInt();
        int p = in.nextInt();
        String str = in.nextString();
        int[] s = new int[n + 3];
        for (int i = 0; i < n; i++)
            s[i + 2] = str.charAt(i) - 'a';
        s[0] = s[1] = -1;

        boolean valid = false;
        for (int i = s.length - 2; i >= 2; i--) {
            for (int ch = s[i] + 1; ch < p; ch++) {
                if (s[i - 1] != ch && s[i - 2] != ch) {
                    s[i] = ch;
                    if (nextSuffix(s, i + 1, p)) {
                        printStr(s);
                        return;
                    }
                }
            }
        }
        out.println("NO");
    }

    static boolean nextSuffix(int[] s, int index, int max) {
        for (int i = index; i < s.length - 1; i++) {
            boolean valid = false;
            for (int ch = 0; ch < max; ch++) {
                if (s[i - 1] != ch && s[i - 2] != ch) {
                    s[i] = ch;
                    valid = true;
                    break;
                }
            }
            if (!valid)
                return false;
        }
        return true;
    }

    static void printStr(int[] s) {
        char[] ch = new char[s.length - 3];
        for (int i = 0; i < ch.length; i++)
            ch[i] = (char) ('a' + s[i + 2]);
        out.println(ch);
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