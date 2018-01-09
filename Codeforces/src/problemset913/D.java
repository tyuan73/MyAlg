package problemset913;


/*

*/

import java.util.*;
import java.io.*;

public class D {
    static void go() {
        int n = in.nextInt(), t = in.nextInt();
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++) {
            a[i][0] = in.nextInt();
            a[i][1] = in.nextInt();
            a[i][2] = i + 1;
        }
        //Arrays.sort(a, (x, y) -> (x[1] - y[1]));
        Arrays.sort(a, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (check(a, t, mid))
                l = mid;
            else
                r = mid - 1;
        }
        out.println(l);
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i][1] > t || output.size() >= l) break;
            if (a[i][0] < l) continue;
            output.add(a[i][2]);
            t -= a[i][1];
        }
        out.println(output.size());
        for (int x : output)
            out.print(x + " ");

        out.println();
    }

    static boolean check(int[][] a, int t, int mid) {
        for (int i = 0, c = 0; i < a.length; i++) {
            if (a[i][1] > t) return false;
            if (a[i][0] < mid) continue;
            t -= a[i][1];
            if (++c >= mid) return true;
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