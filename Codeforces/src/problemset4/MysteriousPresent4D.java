package problemset4;

/**
 * Created by yuantian on 12/12/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class MysteriousPresent4D {
    static void go() {
        int n = in.nextInt();
        int w = in.nextInt();
        int h = in.nextInt();
        int[][] chain = new int[n][3];
        int real = n;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (x <= w || y <= h) {
                chain[i][0] = Integer.MAX_VALUE;
                chain[i][1] = Integer.MAX_VALUE;
                real--;
            } else {
                chain[i][0] = x;
                chain[i][1] = y;
            }
            chain[i][2] = i + 1;
        }

        if (real == 0) {
            out.println(0);
            return;
        }

        Arrays.sort(chain, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o2[1] - o1[1];  // <---- this is important, this is not o1[1]-o2[1] as you may think of
                return o1[0] - o2[0];
            }
        });

        /*
        input:
        5002 5005
        5003 5004
        5003 5002
        5002 5001
        5002 5002

        after sorting, the array may look like:
        5002 5005 1
        5002 5002 5
        5002 5001 4
        5003 5004 2
        5003 5002 3

        the second element is sorted in decreasing order if the
        first element are the same
        */

        // LIS -- longest increasing sequence
        int[] pre = new int[n];
        int[] list = new int[n];
        pre[0] = -1;
        int count = 1;
        for (int i = 1; i < real; i++) {
            int p = list[count - 1];
            if (chain[i][1] > chain[p][1]) {
                pre[i] = p;
                list[count++] = i;
                continue;
            }

            int l = -1, r = count - 1;
            while (l < r) {
                int m = (l + r + 1) / 2;
                if (chain[list[m]][1] >= chain[i][1])
                    r = m - 1;
                else
                    l = m;
            }
            pre[i] = l == -1 ? -1 : list[l];
            list[l + 1] = i;
        }

        out.println(count);
        int[] ans = new int[count];
        int p = list[count - 1];
        for (int i = count - 1; i >= 0; i--) {
            ans[i] = chain[p][2];
            p = pre[p];
        }
        for (int i = 0; i < count; i++) {
            out.print(ans[i] + " ");
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