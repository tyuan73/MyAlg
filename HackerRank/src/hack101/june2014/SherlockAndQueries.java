package hack101.june2014;

/**
 * Created by yuantian on 6/30/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class SherlockAndQueries {
    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        long[] a = new long[n];
        int[] b = new int[m];
        long[] c = new long[m];
        long[] d = new long[n+1];
        final long P = 1000000007;

        for(int i = 0; i < n; i++)
            a[i] = in.nextLong();
        for(int i = 0; i < m; i++)
            b[i] = in.nextInt();
        for(int i = 0; i < m; i++)
            c[i] = in.nextLong();

        Arrays.fill(d, 1);
        for(int i = 0; i < m; i++) {
            d[b[i]] = (d[b[i]] * c[i]) % P;
        }

        for(int i = 1; i <= n; i++) {
            int to = (int)Math.sqrt((double)i);
            for(int j = 1; j <= to; j++) {
                if (i % j == 0) {
                    a[i-1] = (a[i-1] * d[j]) % P;
                    if (j != i/j) {
                        a[i-1] = (a[i-1] * d[i/j]) % P;
                    }
                }
            }
        }

        for(int i = 0; i < n; i++)
            out.print(a[i] + " ");
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