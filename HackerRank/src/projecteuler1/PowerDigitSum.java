package projecteuler1;

/**
 * Created by yuantian on 7/24/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class PowerDigitSum {
    static void go() {

        String str = "1";
        int[] sum = new int[10001];
        sum[0] = 1;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= 10000; i++) {
            int c = 0;
            sb.setLength(0);
            for(int j = 0; j < str.length(); j++) {
                int num = 2 * (str.charAt(j) -'0') + c;
                sb.append(num % 10);
                sum[i] += num % 10;
                c = num / 10;
            }
            sb.append(c);
            sum[i] += c;
            str = sb.toString();
        }

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            out.println(sum[n]);
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