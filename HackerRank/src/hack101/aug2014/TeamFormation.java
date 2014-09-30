package hack101.aug2014;

/**
 * Created by yuantian on 9/26/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class TeamFormation {
    static void go() {
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            if (n == 0) {
                out.println(0);
                continue;
            }

            int[] a = new int[n+1];
            a[n] = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++)
                a[i] = in.nextInt();

            Arrays.sort(a);
            int min = Integer.MAX_VALUE;
            int[] len = new int[n+1];
            int idx1 = 0, idx2 = 0;
            int pre = 0, cur = 1;
            for(int i = 0; i <= n; i++) {
                if (a[i] == a[i-1]) {
                    cur++;
                } else if (a[i] == a[i-1] + 1) {
                    if (cur >= pre) {
                        for(int j = idx1; j < idx1+cur; j++)
                            len[j]++;
                        idx2 = idx1+cur-1;
                    } else {
                        for(int j = idx2; j > idx2-cur; j--)
                            len[j]++;
                        for(int j = idx1; j <= idx1-cur; j++)
                            min = Math.min(min, len[j]);
                        idx1 = idx2-cur+1;

                    }
                    pre = cur;
                    cur = 1;
                } else {
                    if (cur >= pre) {
                        for(int j = idx1; j < idx1+cur; j++)
                            len[j]++;
                        idx2 = idx1+cur-1;
                    } else {
                        for(int j = idx2; j > idx2-cur; j--)
                            len[j]++;
                    }
                    for(int j = idx1; j <= idx2; j++)
                        min = Math.min(min, len[j]);
                    idx2++;
                    idx1 = idx2;
                    pre = 0; cur = 1;
                }
            }

            out.println(min);
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