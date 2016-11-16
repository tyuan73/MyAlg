package problemset733;

/**
 * Created by yuantian on 11/16/16.
 */

/*

*/

import java.util.*;
import java.io.*;

public class EpidemicInMonstropolis733C {
    static int[] max;
    static List<String> steps = null;

    static void go() {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int k = in.nextInt();
        int[] b = in.nextIntArray(k);

        max = new int[n];
        steps = new ArrayList<>();
        int sum = 0;
        int from = n - 1, to = n - 1;
        for (int i = k - 1; i >= 0; i--) {
            if (from < 0) {
                out.println("NO");
                return;
            }
            int x = b[i];
            for (; from >= 0; from--) {
                sum += a[from];
                if (sum == x) {
                    if (check(a, from, to)) {
                        sum = 0;
                        to = --from;
                        break;
                    } else {
                        out.println("NO");
                        return;
                    }
                } else if (sum > x) {
                    out.println("NO");
                    return;
                }
            }
        }
        if (from >= 0) {
            out.println("NO");
            return;
        }
        out.println("YES");
        for (String s : steps)
            out.println(s);
    }

    static boolean check(int[] a, int f, int to) {
        //out.println(f +  " -> " + to);
        //steps.clear();
        if (f == to) {
            return true;
        }
        int idx = 0, maxValue = a[f];
        int from = f;
        //max[idx] = from;
        for (; from <= to; from++) {
            if (a[from] == maxValue) {
                max[idx++] = from;
            } else if (a[from] > maxValue) {
                idx = 1;
                max[0] = from;
                maxValue = a[from];
            }
        }

        for (int i = 0; i < idx; i++) {
            if (max[i] < to && a[max[i]] > a[max[i] + 1]) {
                for (int j = max[i]; j < to; j++) {
                    steps.add((max[i] + 1) + " R");
                }
                for (int j = max[i]; j > f; j--) {
                    steps.add((j + 1) + " L");
                }

                return true;
            } else if (max[i] > f && a[max[i]] > a[max[i] - 1]) {
                for (int j = max[i]; j > f; j--) {
                    steps.add((j + 1) + " L");
                }
                for (int j = max[i]; j < to; j++) {
                    steps.add((f + 1) + " R");
                }
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