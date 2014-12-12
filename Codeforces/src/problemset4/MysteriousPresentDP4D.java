package problemset4; /**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/2/13
 * Time: 11:52 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class MysteriousPresentDP4D {

    static void go() {
        int n = in.nextInt();
        int w = in.nextInt();
        int h = in.nextInt();
        int[][] chain = new int[n][3];
        int act = n;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (x <= w || y <= h) {
                chain[i][0] = Integer.MAX_VALUE;
                chain[i][1] = Integer.MAX_VALUE;
                act--;
            } else {
                chain[i][0] = x;
                chain[i][1] = y;
            }
            chain[i][2] = i + 1;
        }

        if (act == 0) {
            out.println(0);
            return;
        }

        Arrays.sort(chain, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o2[1] - o1[1]; // if width are the same, sort height by decreasing order
                return o1[0] - o2[0];
            }
        });

        // DP
        int[] dp = new int[act];
        int[] pre = new int[act];
        int max = 0, maxIndx = 0;
        for (int i = 1; i < act; i++) {
            for (int j = 0; j < i; j++) {
                if (chain[j][1] < chain[i][1]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                maxIndx = i;
            }
        }

        // restore original order
        int[] ans = new int[max + 1];
        for (int i = max; i >= 0; i--) {
            ans[i] = chain[maxIndx][2];
            maxIndx = pre[maxIndx];
        }
        out.println(max + 1);
        for (int x : ans) {
            out.print(x + " ");
        }
        out.println();
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
