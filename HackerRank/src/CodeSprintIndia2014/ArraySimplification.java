package CodeSprintIndia2014;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 9/20/14
 * Time: 10:24 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

https://www.hackerrank.com/contests/csindia/challenges/array-simplification

Chelsea likes to play with arrays very much, but today she is lazy and only wants to play with arrays containing single element. Her brother Mike wants to help her and takes an array A of N elements. Now Mike wants to apply N-1 operations to obtain a single element. In a single operation the number of array elements is reduced by one. A single operation is described below:

For every element in A start at position 0 calculate a new array B where B[i] = A[i] - A[i+1] for 0<= i < number of elements on A.

Set B to A and go back to step 1, until the number of array elements in A is 1.

Calculate the single element that Mike gives to her sister. As this number can be very large or negative, so return it's modulo (109 + 7).

Input Format
The first line of input contains a number T, the number of test cases.
For every test case: the first line contains an integer N, the number of element in the array. The next line contains N space separated integers which denote the array.

Constraints
1<=T<=5
1<=N<=100000
Every number in A fits into 32 signed bit integer.

Output Format
One line per test case that represents the single element Mike gives to his sister.

Sample Input

2
1
3
3
5 1 3
Sample Output

3
6
Explanation

In the second test case: First compute 5-1,1-3. The second step is 4-(-2) = 6.
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class ArraySimplification {
    static long P = 1000000007L;

    static void go() {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = in.nextInt();

            long res = a[0];
            boolean minus = true;
            long c = 1;
            for (int i = 1; i < n; i++) {
                c = (((c * (n - i)) % P) * power(i, P - 2)) % P;
                if (minus)
                    res -= c * a[i];
                else
                    res += c * a[i];
                minus = !minus;
                res %= P;
            }
            if (res < 0)
                res += P;
            out.println(res);
        }
    }

    static private long power(long x, long p) {
        long ret = 1;
        while (p > 0) {
            if ((p & 1) == 1)
                ret = (ret * x) % P;
            p >>= 1;
            x = (x * x) % P;
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