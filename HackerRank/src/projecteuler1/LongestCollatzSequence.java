package projecteuler1;

/**
 * Created by yuantian on 7/21/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class LongestCollatzSequence {
    static int[] seq = null;

    static void go() {
        seq = new int[5000001];
        seq[1] = 1;
        for(int i = 2; i < 5000001; i++)
            seq[i] = collatz(i);

        int max = 0, idx = 0;
        for(int i = 2; i < 5000001; i++) {
            if (seq[i] >= max) {
                max = seq[i];
                idx = i;
                seq[i] = i;
            } else
                seq[i] = idx;
        }

        int t = in.nextInt();
        while(t-- > 0) {
            out.println(seq[in.nextInt()]);
        }
    }

    static int collatz(long i) {
        if (i <= 5000000 && seq[(int)i] > 0)
            return seq[(int)i];
        if ((i & 1) == 1) {
            int c = collatz(i * 3 + 1)+1;
            if (i <= 5000000)
                seq[(int)i] = c;
            return c;
        }
        else {
            int c = collatz(i / 2)+1;
            if (i <= 5000000)
                seq[(int)i] = c;
            return c;
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