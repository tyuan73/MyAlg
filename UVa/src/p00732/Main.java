package p00732;

/**
 * Created by yuantian on 4/10/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static String l1 = null, l2 = null;
    static char[] output = null;
    static Stack<Integer> S = new Stack<>();

    static void go() {
        while(true) {
            try {
                l1 = in.nextString();
                l2 = in.nextString();
            } catch (Exception e) {
                break;
            }
            if (l1.length() != l2.length()) {
                System.out.println("[\n]");
                continue;
            }
            System.out.println('[');

            output = new char[2 * l1.length()];
            S.clear();
            //check(-1, 0, 0);
            check2(0, 0, 0);

            System.out.println(']');
        }

    }

    static void check2(int i1, int i2, int idx) {
        if (i2 >= l2.length()) {
            printLine();
            return;
        }

        if (i1 < l1.length()) {
            S.push(i1);
            output[idx] = 'i';
            check2(i1+1, i2, idx+1);
            if (!S.empty() && S.peek() == i1) S.pop();
        }
        if (!S.empty() && l1.charAt(S.peek()) == l2.charAt(i2)) {
            S.pop();
            output[idx] = 'o';
            check2(i1, i2+1, idx+1);
        }
    }

    /*
    static void check(int i1, int i2, int idx) {
        if (i2 >= l2.length()) {
            printLine();
            return;
        }

        char ch = l2.charAt(i2);
        for(int i = l1.length()-1; i > i1; i--) {
            if (l1.charAt(i) == ch) {
                int k = idx;
                int pre = S.size();
                for(int j = i1 +1; j < i; j++) {
                    S.push(l1.charAt(j));
                    output[k++] = 'i';
                }
                output[k++] = 'i';
                output[k++] = 'o';
                check(i, i2+1, k);
                while(S.size() > pre) {
                    S.pop();
                }
            }
        }
        if (!S.empty() && S.peek() == ch) {
            S.pop();
            output[idx++] = 'o';
            check(i1, i2+1, idx);
        }
    }
    */

    static void printLine() {
        for(char x : output) {
            System.out.print(x);
            System.out.print(' ');
        }
        System.out.println();
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