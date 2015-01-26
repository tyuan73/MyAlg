package hack101.dec2014;

/**
 * Created by yuantian on 1/5/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class PlayWithWords {
    static void go() {
        String str = in.nextString();
        int n = str.length();

        int[] lmax = new int[n];
        int[] rmax = new int[n];
        for (int i = 0; i < n; i++) {
            int l, r;
            for (l = i, r = i; l >= 0 && r < n; l--, r++) {
                if (str.charAt(l) == str.charAt(r)) {
                    lmax[l] = Math.max(lmax[l], r - l + 1);
                    rmax[r] = Math.max(rmax[r], r - l + 1);
                } else {
                    break;
                }
            }
            for (l = i, r = i + 1; l >= 0 && r < n; l--, r++) {
                if (str.charAt(l) == str.charAt(r)) {
                    lmax[l] = Math.max(lmax[l], r - l + 1);
                    rmax[r] = Math.max(rmax[r], r - l + 1);
                } else {
                    break;
                }
            }
        }

        for(int i : rmax)
            out.print(i);
        out.println();
        for(int i : lmax)
            out.print(i);
        out.println();

        for (int i = 1; i < n; i++)
            rmax[i] = Math.max(rmax[i - 1], rmax[i]);
        for (int i = n - 2; i >= 0; i--)
            lmax[i] = Math.max(lmax[i + 1], lmax[i]);

        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, rmax[i] * lmax[i + 1]);
        }
        out.println(max);

        for(int i : rmax)
            out.print(i + " ");
        out.println();
        for(int i : lmax)
            out.print(i + " ");
        out.println();
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