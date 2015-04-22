package p10227;

/**
 * Created by yuantian on 4/22/15.
 */

/*

*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.InputMismatchException;

class Main {
    static void go() {
        in.nextInt();
        in.nextLine();
        while (true) {
            int p, t;
            try {
                p = in.nextInt();
                t = in.nextInt();
            } catch (Exception e) {
                break;
            }

            BitSet[] bs = new BitSet[p];
            for (int i = 0; i < p; i++)
                bs[i] = new BitSet(t + 1);

            String line;
            while ((line = in.nextLine()).length() > 0) {
                String[] split = line.split(" ");
                int pi = Integer.parseInt(split[0]) - 1;
                int ti = Integer.parseInt(split[1]) - 1;
                bs[pi].set(ti);
            }

            Arrays.sort(bs, new Comparator<BitSet>() {
                @Override
                public int compare(BitSet o1, BitSet o2) {
                    if (o1.equals(o2)) return 0;
                    BitSet clone = (BitSet) o1.clone();
                    clone.xor(o2);
                    return clone.length() == o2.length() ? 1 : -1;
                }
            });
            int count = 1;
            for (int i = 1; i < p; i++)
                if (!bs[i].equals(bs[i - 1]))
                    count++;
            out.println(count);
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