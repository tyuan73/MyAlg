package problemset865;


/*

*/

import java.util.*;
import java.io.*;

public class BuyLowSellHigh865D {
    /**
     * First edition. Without improvement.
     */
    static void go1() {
        int n = in.nextInt();
        long ans = 0;
        int[] rem = new int[n + 1];
        int r = 0;
        rem[0] = Integer.MAX_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x <= pq.peek() && x <= rem[r]) {
                rem[++r] = x;
            } else {
                if (x > pq.peek() && x > rem[r]) {
                    if (pq.peek() > rem[r]) {
                        ans += x - rem[r--];
                        pq.offer(x);
                    } else {
                        ans += x - pq.peek();
                        rem[++r] = pq.poll();
                        pq.offer(x);
                    }
                } else {
                    if (x > pq.peek()) {
                        ans += x - pq.peek();
                        rem[++r] = pq.poll();
                        pq.offer(x);
                    } else {
                        ans += x - rem[r--];
                        pq.offer(x);
                    }
                }
            }
        }

        out.println(ans);
    }

    /**
     * Improved a little bit.
     * rem: not purchased yet - in descending order, for example: 10, 8, 5, 1
     * pq: PriorityQueue, sold and made profit
     */
    static void go() {
        int n = in.nextInt();
        long ans = 0;
        int[] rem = new int[n + 1];
        int r = 0;
        rem[0] = Integer.MAX_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x <= pq.peek() && x <= rem[r]) {
                // if current price (x) is the lowest between 'lowest not purchased (rem[r])', and 'lowest sold (pq.peek())',
                // simply add it to the end of not purchased (rem).
                rem[++r] = x;
            } else {
                // x is not the lowest. Either rem[r] or pq.peek() is the lowest.
                if (pq.peek() >= rem[r]) {
                    // if rem[r] is the lowest, add the difference (x - rem[r]) to profit and remove it from the end of rem
                    ans += x - rem[r--];
                } else {
                    // if pq.peek() is the lowest, add the difference (x - pq.peek()) to profit, and
                    // remove the smallest from sold pq and add it to the end of rem - this will keep rem in descending order.
                    // The idea is to replace pq.peek() with x. Whatever the profit we make from pq.peek(), we can instead
                    // sold the stock at price of x - that makes more profit:
                    //          newProfit = oldProfit - (pq.peek() - y) + (x - y) = oldProfit + (x - pq.peek())
                    // Remember to put pq.peek() to the end of rem.
                    // The following line is the same as:
                    //      ans += x - pq.peek();
                    //      rem[++r] = pq.poll();
                    ans += x - (rem[++r] = pq.poll());
                }
                // put x in sold pq.
                pq.offer(x);
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