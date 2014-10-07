package weekly.week11.day2;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 10/7/14
 * Time: 1:30 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

https://www.hackerrank.com/contests/w11/challenges/strange-numbers

Let length(A) denote the count of digits of a number A in its decimal representation.
John is looking for new methods of determining which numbers are strange all day long.
All non-negative numbers of length 1 are strange. Further, a number X with length(X)≥1 can also be considered strange if and only if

    X is evenly divisible by length(X)
    the number X/length(X) is recursively strange

Your task is to calculate how many strange numbers belong to an interval [L,R].

Input Format
The first line contains single integer T - the number of test cases. Next T lines contain two integers separated by single space L and R.

Output Format
In T lines, print T integers - count of strange numbers belonging to the interval [L,R].

Constraints
1≤T≤200
0≤L<R≤1018

Sample Input

5
7 25
45 50
1 100
99 103
0 1000000

Sample Output

10
1
26
0
96

Explanation
First testcase: There are 10 strange numbers that belong to the interval [7,25]. They are 7,8,9,10,12,14,16,18,20,24.
Second testcase: Only 48 satisfies the given constraints.

*/

import java.util.*;
import java.io.*;

public class StrangeNumbers {
    static void go() {
        long[] ans = new long[285];
        int count = 0;
        for (int i = 0; i <= 9; i++) {
            ans[count++] = i;
        }

        for (int i = 2; i < 18; i++) {
            int c = count;
            for (int j = 0; j < c; j++) {
                long x = ans[j];
                long low = getBound(i);
                long up = getBound(i + 1);
                while (i * x >= low && i * x < up) {
                    ans[count++] = i * x;
                    x *= i;
                }
            }
        }
        ans[284] = Long.MAX_VALUE;

        Arrays.sort(ans);

        int t = in.nextInt();
        while (t-- > 0) {
            long l = in.nextLong();
            long r = in.nextLong();
            int i1 = 0;
            while (ans[i1] < l)
                i1++;
            int i2 = count - 1;
            while (ans[i2] > r)
                i2--;
            out.println(i2 - i1 + 1);
        }

        /* make sure there is no duplicated values in ans[]
        for(int i = 1; i < count; i++) {
            if (ans[i] == ans[i-1])
                out.println("something is wrong: " + ans[i]);
        }
        */
    }

    static long getBound(int c) {
        long ret = 1;
        while (c-- > 1) {
            ret *= 10;
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