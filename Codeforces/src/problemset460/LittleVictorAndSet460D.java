package problemset460;

/**
 * Created by yuantian on 10/30/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class LittleVictorAndSet460D {
    static void go() {
        long l = in.nextLong();
        long r = in.nextLong();
        int k = in.nextInt();

        long min = l;
        int count = 1;
        long from = l;
        for(int len = 2; len <= Math.min(4, k); len++) {
            for(long i = l; i <= Math.min(l+100, r - len+1); i++) {
                if ((i-1) % 4 == 0 && len == 3)
                    out.println("yes: " + i);
                long xor = 0;
                for(int j = 0; j < len; j++) {
                    xor ^= i+j;
                }
                if (xor < min) {
                    min = xor;
                    count = len;
                    from = i;
                }
            }
        }
        out.println(min);
        out.println(count);
        while(count-- > 0) {
            out.print(from++ + " ");
        }
        out.println();

        out.println(262145 ^ 262146 ^ 262147);
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