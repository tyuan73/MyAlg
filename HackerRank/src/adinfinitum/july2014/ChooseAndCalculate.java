package adinfinitum.july2014;

/**
 * Created by yuantian on 7/14/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class ChooseAndCalculate {
    static final long P = 1000000007;
    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] ball = new int[n];
        for(int i = 0; i < n; i++)
            ball[i] = in.nextInt();

        Arrays.sort(ball);
        if (k == 1) {
            out.println(0);
            return;
        }

        long[] m = new long[n];
        int k1 = k - 2;
        m[k1] = 1;
        long[] modInverse = new long[n];
        modInverse[1] = 1;
        for(int i = 2; i < n; i++) {
            modInverse[i] = (-(P/i) * modInverse[(int)P%i]) % P + P;
        }

        for(int i = k1 + 1; i < n; i++) {
            m[i] = (m[i-1]*i) % P;
            m[i] = (m[i] * modInverse[i-k1]) % P;
        }

        long ans  = 0;
        for(int l = 0; l <= n-k; l++) {
            long x = ball[l];
            for(int r = l+k-1; r < n; r++) {
                ans += m[r-l-1] * (ball[r] - x);
                ans %= P;
            }
        }
        out.println(ans);
    }

    /*
    static long pow(long x, long p) {
        long ret = 1;
        long base = x;
        while(p > 0) {
            if ((p&1) == 1) {
                ret = (ret * base) % P;
            }
            p >>= 1;
            base = (base * base) % P;
        }
        return ret;
    }
    */

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