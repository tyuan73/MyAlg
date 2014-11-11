package problemset464;

/**
 * Created by yuantian on 11/10/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class PetrSolution464C {
    static long P = (long) 1e9 + 7;

    static void go() {
        String s = in.nextString();
        int n = in.nextInt();
        String[] req = new String[n];
        for (int i = 0; i < n; ++i)
            req[i] = in.nextString();

        int[] p10 = new int[10];
        int[] h = new int[10];
        for (int i = 0; i < 10; ++i) {
            p10[i] = 10;
            h[i] = i;
        }
        for (int i = req.length - 1; i >= 0; --i) {
            String st = req[i];
            int from = st.charAt(0) - '0';
            long np10 = 1;
            long nh = 0;
            for (int j = 3; j < st.length(); ++j) {
                int cur = st.charAt(j) - '0';
                np10 = np10 * p10[cur] % P;
                nh = (nh * p10[cur] + h[cur]) % P;
            }
            p10[from] = (int) np10;
            h[from] = (int) nh;

            out.println("p10:");
            for(int x : p10)
                out.println(x);
            out.println();
            out.println("h:");
            for(int x : h)
                out.println(x);
            out.println();
        }
        long res = 0;
        for (int i = 0; i < s.length(); ++i) {
            int cur = s.charAt(i) - '0';
            res = (res * p10[cur] + h[cur]) % P;
        }
        out.println(res);
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