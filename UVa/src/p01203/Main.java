package p01203;

/**
 * Created by yuantian on 4/16/15.
 */

/*

*/


import java.util.*;
import java.io.*;

class Main {
    static class CheckPoint implements Comparable<CheckPoint> {
        int time, qid;

        CheckPoint(int t, int q) {
            this.time = t;
            this.qid = q;
        }

        public int compareTo(CheckPoint c) {
            if (this.time == c.time)
                return c.qid - this.qid;
            return c.time - this.time;
        }
    }

    static void go() {
        String str;
        ArrayList<String> input = new ArrayList<>();
        while (!(str = in.nextLine()).equals("#")) {
            input.add(str);
        }
        int n = in.nextInt();
        PriorityQueue<CheckPoint> pq = new PriorityQueue<>();
        for (String s : input) {
            String[] sp = s.split(" ");
            int id = Integer.parseInt(sp[1]);
            int intv = Integer.parseInt(sp[2]);
            for (int i = 0, time = intv; i < n; i++, time += intv) {
                CheckPoint max = pq.peek();
                CheckPoint cur = new CheckPoint(time, id);
                if (pq.size() >= n && max.compareTo(cur) > 0)
                    break;
                pq.add(cur);
                if (pq.size() > n)
                    pq.remove();
            }
        }
        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--)
            output[i] = pq.remove().qid;

        for (int qid : output)
            out.println(qid);
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
                    new InputMismatchException();
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
            while (!(c == '\n' || c == '\r')) {
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