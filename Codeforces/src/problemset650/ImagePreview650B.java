package problemset650;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/19/16
 * Time: 11:29 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class ImagePreview650B {
    static void go() {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int t = in.nextInt();
        String img = in.nextString();

        int[] time = new int[n];
        int[] revTime = new int[n];
        time[0] = img.charAt(0) == 'h' ? 1 : b + 1;
        for (int i = 1; i < n; i++) {
            if (img.charAt(i) == 'h')
                time[i] = time[i - 1] + a + 1;
            else
                time[i] = time[i - 1] + a + b + 1;
        }
        revTime[0] = time[0];
        for (int i = n - 1; i > 0; i--) {
            if (img.charAt(i) == 'h')
                revTime[n - i] = revTime[n - i - 1] + a + 1;
            else
                revTime[n - i] = revTime[n - i - 1] + a + b + 1;
        }

        if (time[n - 1] <= t) {
            out.println(n);
            return;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (time[i] > t) break;
            int total = i + 1;
            int rem = t - time[i] - i * a + time[0];
            if (rem > 0) {
                int l = 0, r = n - 1;
                while (l < r) {
                    int mid = (l + r + 1) / 2;
                    if (revTime[mid] > rem)
                        r = mid - 1;
                    else
                        l = mid;
                }
                total += l;
            }
            max = Math.max(max, total);
        }
        //out.println(max);
        for (int i = 0; i < n; i++) {
            if (revTime[i] > t) break;
            int total = i + 1;
            int rem = t - revTime[i] - i * a + time[0];
            if (rem > 0) {
                int l = 0, r = n - 1;
                while (l < r) {
                    int mid = (l + r + 1) / 2;
                    if (time[mid] > rem)
                        r = mid - 1;
                    else
                        l = mid;
                }
                total += l;
            }
            max = Math.max(max, total);
        }
        out.println(max);
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