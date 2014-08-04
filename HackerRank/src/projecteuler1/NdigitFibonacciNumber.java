package projecteuler1;

/**
 * Created by yuantian on 8/4/14.
 */

/*
The Fibonacci sequence is defined by the recurrence relation:
Fn=Fn−1+Fn−2, where F1=1 and F2=1
.

Hence the first 12 terms will be:
F1=1F2=1F3=2F4=3F5=5F6=8F7=13F8=21F9=34F10=55F11=89F12=144
The 12th term, F12, is the first term to contain three digits.
What is the first term in the Fibonacci sequence to contain N digits?

Input Format
The first line contains an integer T , i.e., number of test cases.
Next T lines will contain an integer N.

Output Format
Print the values corresponding to each test case.

Constraints
1≤T≤5000
0≤N≤5000

Sample Input

2
3
4
Sample Output

12
17
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class NdigitFibonacciNumber {

    /*
    http://www.mathblog.dk/project-euler-25-fibonacci-sequence-1000-digits/
     */
    static void go() {
        double p = Math.log((1 + Math.sqrt(5))/2);
        double f1 = Math.log(10) / p;
        double f2 = Math.log(5)/ 2 / p;

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt() - 1;
            int x = (int) (n * f1 + f2);
            out.println(x+1);
        }
    }

    /* brute force */
    static void go1() {
        int[] ans = new int[5001];
        BigInteger b1 = BigInteger.ONE;
        BigInteger b2 = BigInteger.ONE;
        BigInteger b3 = BigInteger.ZERO;
        BigInteger base = BigInteger.TEN;
        ans[1] = 1;
        int start = 2;
        int seq = 2;
        while (start <= 5000) {
            b3 = b1.add(b2);
            b1 = b2;
            b2 = b3;
            seq++;
            while (b3.compareTo(base) >= 0 && start <= 5000) {
                ans[start++] = seq;
                base = base.multiply(BigInteger.TEN);
            }
        }

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            out.println(ans[n]);
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