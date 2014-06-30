package weekly.week6.day1;

/**
 * Created by yuantian on 6/30/14.
 */

/*

*/

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class ACMICPCTeam {
    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        BigInteger[] b = new BigInteger[n];

        /*** for test ***
        long start = System.currentTimeMillis();
        char[] test = new char[m];
        Arrays.fill(test, '1');
        for(int i = 0; i < n; i++)
            b[i] = new BigInteger(new String(test), 2);
        ******/
        for(int i = 0; i < n; i++)
            b[i] = new BigInteger(in.nextString(), 2);

        int max = 0;
        int count = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                BigInteger x = b[i].or(b[j]);
                int c = x.bitCount();
                if (c > max) {
                    max = c;
                    count = 1;
                } else if (c == max) {
                    count++;
                }
            }
        }
        out.println(max);
        out.println(count);
        //out.println(System.currentTimeMillis() - start);
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