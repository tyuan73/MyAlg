package problemset488;

/**
 * Created by yuantian on 11/21/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class FightTheMonster488C {
    static void go() {
        int[] y = in.nextIntArray(3);
        int[] m = in.nextIntArray(3);
        int[] c = in.nextIntArray(3);

        int l = 0, r = 1000001;
        while (l < r) {
            int mid = (l + r) / 2;
            if (ok(y, m, c, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        out.println(l);
    }

    static boolean ok(int[] y, int[] m, int[] c, int cost) {
        for (int atk = y[1]; cost >= 0; atk++, cost -= c[1]) {
            if (atk <= m[2])
                continue;
            for (int rem = cost, def = y[2]; rem >= 0; rem -= c[2], def++) {
                if (m[1] <= def)
                    return true;
                int hp = y[0] + rem / c[0];
                int seconds = (m[0] + atk - m[2] - 1) / (atk - m[2]);
                if (hp > seconds * (m[1] - def))
                    return true;
            }
        }

        return false;
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