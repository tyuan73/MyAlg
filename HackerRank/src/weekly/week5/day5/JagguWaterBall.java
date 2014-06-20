package weekly.week5.day5;

/**
 * Created by yuantian on 6/20/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class JagguWaterBall {
    static long[] buckets = null;

    static void go() {
        int q = in.nextInt();

        buckets = new long[1000000];
        while (q-- > 0) {
            String command = in.nextString();
            if (command.charAt(0) == 'U') {
                int p = in.nextInt();
                int m = in.nextInt();
                int plus = in.nextInt();
                update(p, m, plus);
            } else {
                int p1 = in.nextInt();
                int p2 = in.nextInt();
                int total = 0;
                for (; p1<= p2; p1++)
                    total += buckets[p1];
                System.out.println(total);
            }
        }
    }

    static void update(int pos, int m, int plus) {
        int N = 1000000;  //1 million
        for (int i = 1; i <= 50; i++) {
            System.out.println("i= " + i);
            int back = pos;
            for (int j = 1; j <= 1000; j++) {
                //System.out.println("pos1 = " + pos);
                buckets[pos] += m;
                int s, in = getBITs(pos);
                for (int k = 0; ; k++) {
                    //System.out.println("k = " + k);
                    s = pos + (1<<k);
                    if (getBITs(s) <= in) {
                        in = getBITs(s);
                        pos = s;
                        if (pos > N) {
                            //System.out.println("pos = " + pos);
                            break;
                        }
                        //System.out.println("pos2 = " + pos);
                        buckets[pos] += m;
                    }
                }
                pos = pos - N;
            }
            pos = back + plus;
            System.out.println("pos3 = " + pos);
            if (pos > N)
                pos -= N;
        }
    }

    static int getBITs(long x) {
        int ret = 0;
        while (x > 0) {
            ret++;
            x &= x - 1;
        }
        return ret;
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