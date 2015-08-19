package problemset558;

/**
 * Created by yuantian on 8/19/15.
 */

import java.util.*;
import java.io.*;

public class GuessYourWayOut558D {
    static class Segment implements Comparable<Segment> {
        long l, r;

        Segment(long a, long b) {
            this.l = a;
            this.r = b;
        }

        public int compareTo(Segment s) {
            if (this.l < s.l)
                return -1;
            else if (this.l > s.l)
                return 1;
            else if (this.r > s.r)
                return -1;
            else if (this.r < s.r)
                return 1;

            return 0;
        }
    }

    static void go() {
        int h = in.nextInt();
        int q = in.nextInt();
        long il = 1 << (h - 1), ir = (1 << h) - 1;
        ArrayList<Segment> ex = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            long lx = in.nextLong();
            long rx = in.nextLong();
            int ans = in.nextInt();

            lx <<= (h - x);
            rx = ((rx + 1) << (h - x)) - 1;
            if (ans == 1) {
                il = Math.max(il, lx);
                ir = Math.min(ir, rx);
            } else {
                ex.add(new Segment(lx, rx));
            }
        }

        if (il > ir) {
            out.println("Game cheated!");
            return;
        }

        Collections.sort(ex);
        long one = -1;
        for (Segment s : ex) {
            if (s.r < il)
                continue;
            if (s.l > il + 1)
                break;
            if (s.l <= il) {
                il = s.r + 1;
            } else {
                // s.l must == il + 1
                if (one == -1) {
                    one = il;
                    il = s.r + 1;
                } else {
                    break;
                }
            }
            if (il > ir)
                break;
        }

        if (one != -1) {
            if (il > ir)
                out.println(one);
            else
                out.println("Data not sufficient!");
        } else {
            if (il > ir)
                out.println("Game cheated!");
            else if (il == ir)
                out.println(il);
            else
                out.println("Data not sufficient!");
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