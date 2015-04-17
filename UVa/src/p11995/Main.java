package p11995;

/**
 * Created by yuantian on 4/17/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static final String[] ANS = {
            "impossible",       // 0
            "stack",            // 1
            "priority queue",   // 2
            "not sure",         // 3
            "queue",            // 4
            "not sure",         // 5
            "not sure",         // 6
            "not sure"          // 7
    };

    static void go() {
        int n;
        Stack<Integer> stack = new Stack<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(100, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        LinkedList<Integer> queue = new LinkedList<>();
        while (true) {
            try {
                n = in.nextInt();
            } catch (Exception e) {
                break;
            }

            stack.clear();
            pq.clear();
            queue.clear();
            int result = 7;
            for (int i = 0; i < n; i++) {
                int op = in.nextInt();
                int num = in.nextInt();
                if (op == 1) {
                    if ((result & 1) > 0)
                        stack.add(num);
                    if ((result & 2) > 0)
                        pq.add(num);
                    if ((result & 4) > 0)
                        queue.addLast(num);
                } else {
                    if ((result & 1) > 0 && (stack.isEmpty() || num != stack.pop())) {
                        result ^= 1;
                    }
                    if ((result & 2) > 0 && (pq.isEmpty() || num != pq.poll())) {
                        result ^= 2;
                    }
                    if ((result & 4) > 0 && (queue.isEmpty() || num != queue.removeFirst())) {
                        result ^= 4;
                    }
                }
            }
            out.println(ANS[result]);
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