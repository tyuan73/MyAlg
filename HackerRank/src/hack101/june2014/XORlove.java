package hack101.june2014;

/**
 * Created by yuantian on 7/2/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class XORlove {
    static void go() {
        int n = in.nextInt();
        int[][] a = new int[n+1][20];
        for(int i = 1; i <= n; i++) {
            int next = in.nextInt();
            for(int s = 0; s < 20; s++) {
                if ((next & (1 << s)) > 0 ) {
                    a[i][s] = a[i-1][s] + 1;
                } else {
                    a[i][s] = a[i-1][s];
                }
            }
        }

        int q = in.nextInt();
        while(q-- > 0) {
            int k = in.nextInt();
            int p = in.nextInt();
            int r = in.nextInt();

            long ans = 0;
            long num;
            for(int s = 0; s < 20; s++) {
                long count1 = a[r][s] - a[p-1][s];
                long count0 = r-p+1-count1;
                if ((k & (1 << s)) > 0) {
                    num = (count0 * (count0-1) + count1 * (count1-1))/2;
                } else {
                    num = count1*count0;
                }
                ans += num * (1<<s);
            }
            out.println(ans % 1000000007);
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