package problemset946;


/*

 */

import java.util.*;
import java.io.*;

public class Timetable_946D {
    static void go() {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        List<List<Integer>> dmin = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> idx = new ArrayList<>();
            char[] str = in.nextString().toCharArray();
            for (int j = 0; j < m; j++) {
                if (str[j] == '1') idx.add(j);
            }
            if (idx.size() == 0) continue;
            List<Integer> dd = new ArrayList<>();
            for (int diff = 0; diff < idx.size(); diff++) {
                int min = Integer.MAX_VALUE;
                for (int s = 0, e = idx.size() - 1 - diff; e < idx.size(); s++, e++)
                    min = Math.min(min, idx.get(e) - idx.get(s) + 1);
                dd.add(min);
            }
            dd.add(0);
            dmin.add(dd);
        }

        n = dmin.size();
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                List<Integer> day = dmin.get(j - 1);
                dp[i][j] = dp[i][j - 1] + day.get(0);
                for (int d = 1; d < Math.min(i + 1, day.size()); d++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - d][j - 1] + day.get(d));
                }
            }
        }
        out.println(dp[k][n]);
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