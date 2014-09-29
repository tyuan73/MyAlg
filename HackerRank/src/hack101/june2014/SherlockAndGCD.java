package hack101.june2014;

/**
 * Created by yuantian on 7/2/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class SherlockAndGCD {
    static void go() {
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int pre = in.nextInt();
            for(int i = 1; i < n; i++) {
                int cur = in.nextInt();
                pre = gcd(cur, pre);
            }
            if (pre == 1)
                out.println("YES");
            else
                out.println("NO");
        }
    }

    static int gcd(int a, int b) {
        if (a > b) {
            int x = a;
            a = b;
            b = x;
        }

        while(a != 0) {
            int x = a;
            a = b%a;
            b = x;
        }
        return b;
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