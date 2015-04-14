package p10901;

/**
 * Created by yuantian on 4/13/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {
        int k = in.nextInt();
        while (k-- > 0) {
            int n = in.nextInt(), t = in.nextInt(), m = in.nextInt();
            Integer[][] cars = new Integer[m][3];
            LinkedList<Integer[]>[] queue = new LinkedList[2];
            queue[0] = new LinkedList<>();
            queue[1] = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                cars[i][0] = in.nextInt();
                cars[i][1] = in.nextString().charAt(0) == 'l' ? 0 : 1;
                queue[cars[i][1]].addLast(cars[i]);
            }

            int bank = 0, time = 0;
            while (queue[0].size() > 0 || queue[1].size() > 0) {
                int count = 0;
                LinkedList<Integer[]> cur = queue[bank];
                while (count < n && cur.size() > 0) {
                    Integer[] car = cur.getFirst();
                    if (car[0] <= time) {
                        count++;
                        car[2] = time + t;
                        cur.removeFirst();
                    } else
                        break;
                }

                LinkedList<Integer[]> other = queue[bank ^ 1];
                if (count > 0 || (other.size() > 0 && other.getFirst()[0] <= time)) {
                    time += t;
                    bank ^= 1;
                } else {
                    int t1 = queue[0].size() == 0 ? Integer.MAX_VALUE : queue[0].getFirst()[0];
                    int t2 = queue[1].size() == 0 ? Integer.MAX_VALUE : queue[1].getFirst()[0];
                    time = Math.min(t1, t2);
                }
            }

            for (Integer[] x : cars)
                out.println(x[2]);
            if (k != 0) out.println();
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