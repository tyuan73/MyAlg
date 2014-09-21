package CodeSprintIndia2014;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 9/19/14
 * Time: 11:21 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

https://www.hackerrank.com/contests/csindia/challenges/the-rabbit

A rabbit is sitting at x = 0. It can only jump to the right towards the positive x-axis. In the kth jump, the rabbit jumps either k meters or k+1 meters to the right. For example, in the 5th jump the rabbit can jump either 5 meters or 6 meters to the right. How many jumps does the rabbit need to reach point p?

Input Format
The first line contains an integer T, the number of test cases. This is followed by T test cases:
Each test case contains one positive integer p, the point where the rabbit needs to reach.

Output Format
For each testcase, print in a new line the number of jumps needed for the rabbit to reach point p.

Constraints
1 ≤ T ≤ 100
1 ≤ p ≤ 100000000

Sample Input

2
1
7
Sample Output

1
3
Explanation
In the first testcase, the rabbit just takes 1 jump.
In the second testcase, the rabbit jumps 3 times with lengths 1, 2, 4.
*/

import java.util.*;
import java.io.*;

public class TheRabbit {
    static void go() {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long l = 1, r = n;
            while (l < r) {
                long mid = (l + r) / 2;
                if ((mid + 3) * mid / 2 < n)
                    l = mid + 1;
                else
                    r = mid;
            }
            out.println(l);
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