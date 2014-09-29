package projecteuler1;

/**
 * Created by yuantian on 9/24/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class GoldbachsOtherconjecture {
    static void go() {
        int t = in.nextInt();

        int[] primes = new int[500000];
        int count = 0;
        for (int i = 2; i < 500000; i++) {
            if (primes[i] == 0) {
                primes[count++] = i;
                for (int j = i + i; j < 500000; j += i) {
                    primes[j] = -1;
                }
            }
        }

        int[] sq = new int[600000];
        for (int i = 0; i < 707; i++) {
            sq[(i + 1) * (i + 1)] = 1;
        }

        while (t-- > 0) {
            int n = in.nextInt();
            int total = 0;
            // start from primes[1] = 3. skip primes[0] = 2
            for (int i = 1; primes[i] < n; i++) {
                int s = (n - primes[i]) / 2;
                if (sq[s] == 1)
                    total++;
            }
            out.println(total);
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