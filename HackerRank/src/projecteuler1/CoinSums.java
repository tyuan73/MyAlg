package projecteuler1;

/**
 * Created by yuantian on 9/5/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class CoinSums {
    static void go() {
        int t = in.nextInt();

        long[] dp = new long[100001];
        dp[0] = 1;
        int[] coin = {1, 2, 5, 10, 20, 50, 100, 200, 1000000};
        for(int i = 1; i <= 100000; i++) {
            for(int j = 0; coin[j] <= i; j++) {
                dp[i] += dp[i-coin[j]];
                dp[i] %= 1000000007;
            }
        }

        long[] dp2 = new long[100001];
        dp2[0] = 1;
        for(int i = 0; i < coin.length; i++) {
            for(int j = coin[i]; j <= 100000; j++) {
                dp2[j] += dp2[j-coin[i]];
                dp2[j] %= 1000000007;
            }
        }

        while(t-- > 0) {
            int n = in.nextInt();
            out.println(dp[n]);
            out.println(dp2[n]);
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