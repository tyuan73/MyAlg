package weekly.week12.day3;

/**
 * Created by yuantian on 11/18/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class FavoriteSequence {
    static void go() {
        int n = in.nextInt();
        int[] ans = new int[1000001];
        int[] tmp = new int[1000001];
        int[] index = new int[1000001];
        Arrays.fill(index, -1);
        int count = 0;
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            int[] s = in.nextIntArray(k);
            int p = 0, q = 0, r = 0;
            while (p < count && q < k) {
                if (index[s[q]] != -1) {
                    for (; p <= index[s[q]]; p++, r++) {
                        tmp[r] = ans[p];
                    }
                    q++;
                } else {
                    while (p < count && ans[p] < s[q]) {
                        tmp[r] = ans[p];
                        r++;
                        p++;
                    }
                    tmp[r] = s[q++];
                    r++;
                }
            }
            while (p < count) {
                tmp[r] = ans[p];
                r++;
                p++;
            }
            while (q < k) {
                tmp[r] = s[q];
                r++;
                q++;
            }
            int[] t = ans;
            ans = tmp;
            tmp = t;
            count = r;
            for (int j = 0; j < count; j++)
                index[ans[j]] = j;
        }

        for (int i = 0; i < count; i++)
            out.print(ans[i] + " ");
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