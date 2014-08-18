package weekly.week8.day2;

/**
 * Created by yuantian on 8/18/14.
 */

/*
John is new to Mathematics and does not know how to calculate GCD of numbers. So he wants you to help him in a few GCD calculations. John has a list A of numbers, indexed 1 to N. He wants to create another list B having N+1 numbers, indexed from 1 to N+1, and having the following property:

GCD(B[i], B[i+1]) = A[i], ∀ 1 ≤ i ≤ N

As there can be many such lists, John wants to know the list B in which sum of all elements is minimum. It is guaranteed that such a list will always exist.

Input Format
The first line contains an integer T, i.e., the number of the test cases. T testcases follow.
The first line of each test case contains an integer N, i.e., the number of elements in the array.
The second line of each test case contains N space separated integers that denote the elements of the list A.

Output Format
For each test case, print in a new line the list B such that each element is separated by a single space.

Constraints
1 ≤ T ≤ 10
2 ≤ N ≤ 103
1 ≤ A[i] ≤ 104
1 ≤ B[i]

Sample Input

2
3
1 2 3
3
5 10 5
Sample Output

1 2 6 3
5 10 10 5
Explanation

For the first testcase,

 GCD(1,2) = 1
 GCD(2,6) = 2
 GCD(6,3) = 3
 sum = 1+2+6+3 = 12 which is minimum among all possible list B
For the second testcase,

GCD(5, 10) = 5
GCD(10, 10) = 10
GCD(10, 5) = 5
sum = 5 + 10 + 10 + 5 = 30 which is the minimum among all possible list B

*/

import java.util.*;
import java.io.*;

public class JohnAndGCDList {
    static void go() {
        int t = in.nextInt();

        while(t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++)
                a[i] = in.nextInt();

            int b = a[0];
            out.print(b);
            for(int i = 1; i < n; i++) {
                int g = gcd(a[i-1], a[i]);
                b = a[i]/g * a[i-1];
                out.print(" " + b);
            }
            out.println(" " + a[n-1]);
        }
    }

    static int gcd(int a, int b) {
        if (a > b)
            return gcd(b, a);

        if (a == 0)
            return b;
        else
            return gcd(b%a, a);
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

/*
2
9
1 2 3 1 1 3 1 1 1
9
1 1 4 1 1 1 1 1 1
 */

/*
1 2 6 3 1 3 3 1 1 1
1 1 4 4 1 1 1 1 1 1
 */