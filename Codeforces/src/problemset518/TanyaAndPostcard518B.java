package problemset518;

/**
 * Created by yuantian on 2/27/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class TanyaAndPostcard518B {
    static void go() {
        String s = in.nextString();
        String t = in.nextString();
        int[] upper = new int[26];
        int[] lower = new int[26];
        count(t, upper, lower);

        int y = 0, w = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                lower[ch - 'a']--;
                if (lower[ch - 'a'] >= 0)
                    y++;
            } else if (ch >= 'A' && ch <= 'Z') {
                upper[ch - 'A']--;
                if (upper[ch - 'A'] >= 0)
                    y++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (lower[i] * (long) upper[i] < 0) {
                w += Math.min(Math.abs(lower[i]), Math.abs(upper[i]));
            }
        }
        out.println(y + " " + w);
    }

    static void count(String s, int[] u, int[] l) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z')
                l[ch - 'a']++;
            else if (ch >= 'A' && ch <= 'Z')
                u[ch - 'A']++;
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