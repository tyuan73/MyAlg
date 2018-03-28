package problemset949;


/*

 */

import java.util.*;
import java.io.*;

public class Zebras_949A {
    static void go() {
        char[] str = in.nextString().toCharArray();
        LinkedList<List<Integer>> zeros = new LinkedList<>();
        LinkedList<List<Integer>> ones = new LinkedList<>();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '0') {
                if (ones.size() == 0) {
                    List<Integer> list = new ArrayList();
                    list.add(i + 1);
                    zeros.add(list);
                } else {
                    List<Integer> list = ones.pollFirst();
                    list.add(i + 1);
                    zeros.offerLast(list);
                }
            } else {
                if (zeros.size() == 0) {
                    out.println(-1);
                    return;
                }
                List<Integer> list = zeros.pollFirst();
                list.add(i + 1);
                ones.offerLast(list);
            }
        }
        if (ones.size() != 0) {
            out.println(-1);
            return;
        }
        out.println(zeros.size());
        for (List<Integer> seq : zeros) {
            out.print(seq.size());
            for (int s : seq)
                out.print(" " + s);
            out.println();
        }
    }

    /**
     * TLE
     */
    static void go2() {
        char[] str = in.nextString().toCharArray();
        int n = str.length;
        boolean[] used = new boolean[n];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            if (str[i] == '1') {
                out.println(-1);
                return;
            }
            List<Integer> seq = new ArrayList<>();
            int d = 0;
            for (int j = i; j < n; j++) {
                if (used[j] || str[j] != d + '0') continue;
                used[j] = true;
                seq.add(j + 1);
                d ^= 1;
            }
            if (d == 0) {
                out.println(-1);
                return;
            }
            list.add(seq);
        }
        out.println(list.size());
        for (List<Integer> seq : list) {
            out.print(seq.size());
            for (int s : seq)
                out.print(" " + s);
            out.println();
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