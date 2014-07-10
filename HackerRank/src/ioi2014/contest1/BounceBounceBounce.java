package ioi2014.contest1;

/**
 * Created by yuantian on 7/10/14.
 */

/*
https://www.hackerrank.com/contests/ioi-2014-practice-contest-1/challenges/bounce-bounce-bounce-ioi14

Fidel invented a circular ring. Its boundary is made of a reflective material and at one point on the circle's boundary, a laser is placed. He wishes to set the angle of the laser in such a way that it bounces N times around the circle then returns to its original position. How many ways can he choose the initial direction of the laser?

Assume that the angle of incidence is equal to the angle of reflection (see the image below; the location of the laser in the image is for demonstration purposes only).
image

Input Format
The input consists of a single integer, N, on a line by itself.

Output Format
Output one line containing the number of ways to launch the laser so that it bounces exactly N times and returns at the exact same point.

Constraints
1≤N≤106
Sample Input 1

4
Sample Output 1

4
Sample Input 2

3
Sample Output 2

2
Explanation
For the first example, there are exactly four ways to bounce exactly four times and return to the same point. See the figure below.
image

For the second example, there are exactly two ways to bounce exactly three times and return to the same point. See the figure below.

*/

import java.util.*;
import java.io.*;

public class BounceBounceBounce {
    static void go() {
        long n = in.nextLong()+1;
        long start = System.currentTimeMillis();
        long count = 1;
        for(int i = 2; i < n; i++) {
            if (gcd(i, n) == 1) {
                count++;
            }
        }
        out.println(count);
        out.println(System.currentTimeMillis() - start);
    }

    static long gcd(long x, long y) {
        while(x > 0) {
            long z = x;
            x = y % x;
            y = z;
        }
        return y;
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