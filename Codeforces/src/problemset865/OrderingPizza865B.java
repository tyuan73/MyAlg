package problemset865;


/*

*/

import java.util.*;
import java.io.*;

public class OrderingPizza865B {
    static class Contestant implements Comparable<Contestant> {
        int c, val;

        Contestant(int c, int val) {
            this.c = c;
            this.val = val;
        }

        public int compareTo(Contestant a) {
            return this.val - a.val;
        }
    }

    static void go() {
        int n = in.nextInt(), s = in.nextInt();
        long totalS = 0, totalSA = 0, totalSB = 0, ans = 0;
        List<Contestant> A = new ArrayList<>(), B = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int c = in.nextInt(), a = in.nextInt(), b = in.nextInt();
            totalS += c;
            if (a > b) {
                totalSA += c;
                A.add(new Contestant(c, a - b));
            } else if (a < b) {
                totalSB += c;
                B.add(new Contestant(c, b - a));
            }
            ans += c * (long) Math.max(a, b);
        }

        long target = (totalS + s - 1) / s;
        long cA = (totalSA + s - 1) / s, cB = (totalSB + s - 1) / s;
        if (target >= cA + cB) {
            out.println(ans);
            return;
        }

        Collections.sort(A);
        Collections.sort(B);
        long extraA = totalSA - (cA - 1) * s, extraB = totalSB - (cB - 1) * s;
        long diffA = 0, diffB = 0;
        // calculate diffA
        for (Contestant a : A) {
            if (a.c >= extraA) {
                diffA -= extraA * a.val;
                break;
            }
            extraA -= a.c;
            diffA -= a.c * (long) a.val;
        }

        // calculate diffB
        for (Contestant b : B) {
            if (b.c >= extraB) {
                diffB -= extraB * b.val;
                break;
            }
            extraB -= b.c;
            diffB -= b.c * (long) b.val;
        }

        out.println(ans + Math.max(diffA, diffB));
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