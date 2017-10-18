package problemset875;


/*

*/

import java.util.*;
import java.io.*;

public class SortingTheCoins875B {

    /**
     * First try. Use BIT/Fenwick Tree. Not the best.
     */
    static void goBIT() {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] bit = new int[n + 1];
        boolean[] flag = new boolean[n + 1];
        out.print("1 ");
        int last = n;
        for (int x : a) {
            add(bit, x);
            flag[x] = true;
            while (last > 0 && flag[last])
                last--;
            if (last == 0)
                out.print("1 ");
            else
                out.print(sum(bit, last) + " ");
        }
    }

    static int sum(int[] bit, int x) {
        int sum = 1;
        for (; x > 0; x -= x & -x)
            sum += bit[x];
        return sum;
    }

    static void add(int[] bit, int x) {
        for (; x < bit.length; x += x & -x)
            bit[x]++;
    }

    /**
     * Try without Fenwick Tree
     */
    static void go1() {
        int n = in.nextInt();
        //int[] a = in.nextIntArray(n);
        boolean[] flag = new boolean[n];
        int last = n - 1;
        out.print("1 ");
        for (int i = 0; i < n; i++) {
            flag[in.nextInt() - 1] = true;
            while (last >= 0 && flag[last]) last--;
            out.print((i + 1 - (n - 1 - last) + 1) + " ");
        }

    }

    /**
     * The third try.
     */
    static void go() {
        int n = in.nextInt();
        int last = n - 1, ans = 1;
        boolean[] flag = new boolean[n];
        out.print("1 ");
        for (int i = 0; i < n; i++) {
            int x = in.nextInt() - 1;
            flag[x] = true;
            ans++;
            while (last >= 0 && flag[last]) {
                ans--;
                last--;
            }
            out.print(ans + " ");
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

        public String nextLine() {
            StringBuilder sb = new StringBuilder(1024);
            int c = read();
            while (!(c == '\n' || c == '\r' || c == -1)) {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        public char nextChar() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            return (char) c;
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

        public char[] nextCharArray(int n) {
            char[] ca = new char[n];
            for (int i = 0; i < n; i++) {
                int c = read();
                while (isSpaceChar(c))
                    c = read();
                ca[i] = (char) c;
            }
            return ca;
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