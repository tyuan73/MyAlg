package problemset488;

/**
 * Created by yuantian on 11/21/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class CandyBoxes488B {
    static void go() {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        Arrays.sort(a);

        if (n == 0) {
            out.println("YES");
            out.println("1\n1\n3\n3");
            return;
        }
        if (n == 1) {
            out.println("YES");
            //out.println(a[0]);
            out.println(2 * a[0] - 1);
            out.println(2 * a[0] + 1);
            out.println(3 * a[0]);
            return;
        }
        if (n == 2) {
            if (a[1] > 3 * a[0]) {
                out.println("NO");
            } else {
                out.println("YES");
                //out.println(a[0]);
                out.println(4 * a[0] - a[1]);
                out.println(3 * a[0]);
            }
            return;
        }
        if (n == 3) {
            if (a[2] > 3 * a[0]) {
                out.println("NO");
            } else if (a[2] == 3 * a[0]) {
                out.println("YES");
                out.println(a[2] + a[0] - a[1]);
            } else if (a[1] + a[2] == 4 * a[0]) {
                out.println("YES");
                out.println(3 * a[0]);
            } else if (3 * (a[0] + a[1] - a[2]) == a[2]) {
                out.println("YES");
                out.println((a[0] + a[1] - a[2]));
            } else {
                out.println("NO");
            }
            return;
        }
        if (n == 4) {
            if (a[0] + a[3] != a[1] + a[2] || 3 * a[0] != a[3]) {
                out.println("NO");
            } else {
                out.println("YES");
            }
            return;
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

        public int[] nextIntArray(int len) {
            int[] ret = new int[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextInt();
            return ret;
        }

        public long[] nextLongArray(int len) {
            long[] ret = new long[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextLong();
            return ret;
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