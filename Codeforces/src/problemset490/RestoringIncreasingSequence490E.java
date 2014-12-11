package problemset490;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 12/8/14
 * Time: 10:55 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class RestoringIncreasingSequence490E {
    static void go() {
        int n = in.nextInt();
        char[][] seq = new char[n + 1][8];
        Arrays.fill(seq[0], '0');
        int[] val = new int[n + 1];
        int[] base = new int[8];
        base[7] = 1;
        for (int i = 6; i >= 0; i--)
            base[i] = base[i + 1] * 10;
        for (int i = 0; i < n; i++) {
            Arrays.fill(seq[i+1], '0');
            String str = in.nextString();
            for (int p = 7, q = str.length() - 1; q >= 0; p--, q--) {
                seq[i + 1][p] = str.charAt(q);
                if (seq[i + 1][p] != '?') {
                    val[i + 1] += (seq[i + 1][p] - '0') * base[p];
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int diff = val[i] - val[i - 1];
            int inc = 0;
            if (diff <= 0)
            for (int j = 0; j < 8; j++) {
                if (seq[i][j] == '?')
                    inc += (seq[i - 1][j] - '0') * base[j];
            }
            int last = 8;
            for (int j = 7; j >= 0; j--) {
                if (seq[i][j] != '?')
                    continue;
                last = j;
                if (inc + diff > 0)
                    continue;
                if (seq[i - 1][j] != '9' && inc + base[j] + diff > 0) {
                    inc = inc / base[j] * base[j] + base[j];
                    continue;
                }
                //inc = inc / base[j] * base[j];
            }
            if (diff + inc <= 0) {
                out.println("NO");
                out.println("i = " + i);
                for(int j = 0; j <= n; j++)
                    out.println(val[j]);
                return;
            }

            if (last < 8 && inc < base[last]) {
                boolean first = true;
                for (int j = last - 1; j >= 0; j--) {
                    if (seq[i][j] != '0') {
                        first = false;
                        break;
                    }
                }
                if (first) {
                    inc = base[last];
                }
            }
            for (int j = 7; j >= 0; j--) {
                if (seq[i][j] == '?') {
                    seq[i][j] = (char) (((inc / base[j]) % 10) + '0');
                }
            }

            val[i] += inc;
        }

        out.println("YES");
        for (int i = 1; i <= n; i++)
            out.println(val[i]);
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


/*
3
??
11
12
 */

/*
3
99
???
110
 */