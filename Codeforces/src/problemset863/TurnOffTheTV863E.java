package problemset863;


/*

*/

import java.util.*;
import java.io.*;

public class TurnOffTheTV863E {
    /**
     * Easy solution with TreeMap.
     */
    static void go1() {
        int n = in.nextInt();
        int[][] t = new int[n][];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            t[i] = new int[]{in.nextInt(), in.nextInt()};
            map.put(t[i][0], map.getOrDefault(t[i][0], 0) + 1);
            map.put(t[i][1] + 1, map.getOrDefault(t[i][1] + 1, 0) - 1);
        }
        int sum = 0;
        for (int key : map.keySet()) {
            sum += map.get(key);
            map.put(key, sum);
        }
        outer:
        for (int i = 0; i < n; i++) {
            int l = t[i][0], r = t[i][1] + 1;
            for (int key : map.subMap(l, r).keySet()) {
                if (map.get(key) == 1) continue outer;
            }
            out.println(i + 1);
            return;
        }
        out.println(-1);
    }

    /**
     * Don't use TreeMap. Use array and sorting instead.
     */
    static void go() {
        int n = in.nextInt();
        int[][] q = new int[n][];
        int[][] t = new int[2 * n][];
        for (int i = 0; i < n; i++) {
            q[i] = new int[]{in.nextInt(), in.nextInt()};
            t[i * 2] = new int[]{q[i][0], 1};
            t[i * 2 + 1] = new int[]{q[i][1] + 1, -1};
        }
        //Arrays.sort(t, (a, b) -> (a[0] - b[0]));
        Arrays.sort(t, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int idx = 0;
        for (int i = 1; i < 2 * n; i++) {
            if (t[i][0] != t[idx][0]) {
                if (idx > 0) t[idx][1] += t[idx - 1][1];
                t[++idx] = t[i];
            } else {
                t[idx][1] += t[i][1];
            }
        }

        outer:
        for (int i = 0; i < n; i++) {
            int l = q[i][0], r = q[i][1];
            int j = bSearch(t, idx, l);
            while (t[j][0] <= r) {
                if (t[j++][1] == 1) continue outer;
            }
            out.println(i + 1);
            return;
        }
        out.println(-1);
    }

    private static int bSearch(int[][] t, int last, int v) {
        int l = 0, r = last;
        while (l < r) {
            int m = (l + r) / 2;
            if (t[m][0] < v) {
                l = m + 1;
            } else
                r = m;
        }
        return l;
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