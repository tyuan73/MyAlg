package projecteuler1;

/**
 * Created by yuantian on 7/8/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class EvenFibonaccinumbers {
    static void go() {
        long[] fa = new long[90];
        long[] fae = new long[90];
        fa[1] = 1;
        long sum = 0;
        for(int i = 2; i< 90; i++) {
            fa[i] = fa[i-1] + fa[i-2];
            if (i % 3 == 0)
                sum += fa[i];
            fae[i] = sum;
        }
        int t = in.nextInt();
        while(t-- > 0) {
            long n = in.nextLong();
            int l = 0, r = 89;
            while(l < r) {
                int mid = (l+r+1)/2;
                if (fa[mid] > n)
                    r = mid - 1;
                else
                    l = mid;
            }
            out.println(fae[l]);
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