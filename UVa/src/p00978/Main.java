package p00978;

/**
 * Created by yuantian on 4/15/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {
        //long start = System.currentTimeMillis();

        int t = in.nextInt();
        HashMap<Integer, Integer> b1 = new HashMap<>();
        HashMap<Integer, Integer> g1 = new HashMap<>();
        while (t-- > 0) {
            int n = in.nextInt(), sg = in.nextInt(), sb = in.nextInt();
            int[] green = new int[102];
            int[] blue = new int[102];

            for (int i = 0; i < sg; i++) {
                green[in.nextInt()]++;
            }
            for (int i = 0; i < sb; i++) {
                blue[in.nextInt()]++;
            }

            int maxb = 101, maxg = 101;
            while (blue[0] != sb && green[0] != sg) {
                b1.clear();
                g1.clear();
                int rem = n;
                while (rem > 0) {
                    while (maxg > 0 && green[maxg] == 0) maxg--;
                    if (maxg == 0) break;
                    while (maxb > 0 && blue[maxb] == 0) maxb--;
                    if (maxb == 0) break;
                    int small = Math.min(maxb, maxg);
                    int c = Math.min(rem, Math.min(blue[maxb], green[maxg]));
                    blue[maxb] -= c;
                    green[maxg] -= c;
                    rem -= c;

                    int keyb = maxb - small, cb = c;
                    if (b1.containsKey(keyb)) {
                        cb += b1.get(keyb);
                    }
                    b1.put(keyb, cb);
                    int keyg = maxg - small, cg = c;
                    if (g1.containsKey(keyg)) {
                        cg += g1.get(keyg);
                    }
                    g1.put(keyg, cg);
                }
                for (int key : g1.keySet()) {
                    green[key] += g1.get(key);
                    maxg = Math.max(maxg, key);  // this is important, see test case at the bottom
                }
                for (int key : b1.keySet()) {
                    blue[key] += b1.get(key);
                    maxb = Math.max(maxb, key);
                }
            }

            if (blue[0] == sb && green[0] == sg) {
                out.println("green and blue died");
            } else if (blue[0] == sb) {
                out.println("green wins");
                print(green, maxg);
            } else {
                out.println("blue wins");
                print(blue, maxb);
            }
            if (t != 0) out.println();
        }

        //System.out.println("run time: " + (System.currentTimeMillis() - start));
    }

    static void print(int[] lem, int max) {
        while (max > 0) {
            for (int i = 0; i < lem[max]; i++)
                out.println(max);
            max--;
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
                    new InputMismatchException();
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
            while (!(c == '\n' || c == '\r')) {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
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

/**
 * input:
 * 1
 * 3 6 6
 * 36
 * 64
 * 17
 * 72
 * 43
 * 33
 * 82
 * 60
 * 94
 * 2
 * 5
 * 23
 * <p/>
 * output:
 * blue wins
 * 1
 */