package problemset486;

/**
 * Created by yuantian on 11/14/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class LISOfSequence486E {
    static void go() {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        int[] m = new int[n+1];
        //m[1] = 0;
        int maxLen = 1;
        int[] lis1 = new int[n];
        for(int i = 0; i < n; i++) {
            if (a[i] > a[m[maxLen]]) {
                m[++maxLen] = i;
                lis1[i] = maxLen;
                continue;
            }

            int l = 0, r = m[maxLen];
            while(l < r) {
                int mid = (l+r+1)/2;
                if (a[m[mid]] >= a[i])
                    r = mid-1;
                else
                    l = mid;
            }

            if (a[i] <= a[m[l]]) {
                lis1[i] = 1;
                m[l] = i;
            } else {
                m[l+1] = i;
                lis1[i] = l+1;
            }
        }

        int[] lis2 = new int[n];
        Arrays.fill(m, 0);
        m[1] = n-1;
        maxLen = 1;
        for(int i = n-1; i >= 0; i--) {
            if (a[i] < a[m[maxLen]]) {
                m[++maxLen] = i;
                lis2[i] = maxLen;
                continue;
            }

            int l = m[maxLen], r = 0;
            while(l < r) {
                int mid = (l+r+1)/2;
                if (a[m[mid]] <= a[i])
                    r = mid-1;
                else
                    l = mid;
            }

            if (a[i] >= a[m[l]]) {
                lis2[i] = 1;
                m[l] = i;
            } else {
                m[l+1] = i;
                lis2[i] = l+1;
            }
        }

        for(int x : lis1)
            System.out.print(x + " ");
        System.out.println();
        for(int x : lis2)
            System.out.print(x + " ");
        System.out.println();

        System.out.println("maxLen = " + maxLen);

        char[] ans = new char[n];
        int[] exist = new int[n+1];
        Arrays.fill(exist, -1);

        for(int i = 0; i < n; i++) {
            if (lis1[i] + lis2[i] - 1 != maxLen) {
                ans[i] = '1';
                continue;
            }

            if (exist[lis1[i]] >= 0) {
                ans[i] = '2';
                ans[exist[lis1[i]]] = '2';
            } else {
                exist[lis1[i]] = i;
                ans[i] = '3';
            }
        }

        out.println(ans);
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