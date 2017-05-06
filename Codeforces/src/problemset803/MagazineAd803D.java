package problemset803;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/5/17
 * Time: 10:10 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

*/

import java.util.*;
import java.io.*;

public class MagazineAd803D {
    static void go() {
        int k = in.nextInt();
        String str = in.nextLine();
        int[] len = new int[1000000];
        int last = 0;
        for (int i = 0; i < str.length(); i++) {
            len[last]++;
            if (str.charAt(i) == '-' || str.charAt(i) == ' ')
                last++;
        }

        int l = 1, r = str.length();
        while (l < r) {
            int mid = (l + r) / 2;
            if (verify(len, last, k, mid))
                r = mid;
            else
                l = mid + 1;
        }
        out.println(l);
    }

    static boolean verify(int[] len, int last, int k, int val) {
        int idx = 0, sum = 0, line = 1;
        while (idx <= last && line <= k) {
            if (len[idx] > val) return false;
            if (sum + len[idx] <= val) {
                sum += len[idx];
            } else {
                sum = len[idx];
                line++;
            }
            idx++;
        }
        return idx > last && line <= k;
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

        public String nextLine() {
            int c = read();
            while (c == '\n' || c == '\r')
                c = read();
            StringBuilder sb = new StringBuilder(1024);
            do {
                sb.append((char) c);
                c = read();
            } while (c != '\n' && c != '\r');
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