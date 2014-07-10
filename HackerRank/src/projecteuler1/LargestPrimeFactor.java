package projecteuler1;

/**
 * Created by yuantian on 7/9/14.
 */

/*
This problem is a programming version of Problem 3 from projecteuler.net

The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of a given number N?

Input Format
First line contains T, the number of test cases. This is followed by T lines each containing an integer N.

Output Format
For each test case, display the largest prime factor of N.

Constraints
1≤T≤10
10≤N≤1012
Sample Input

2
10
17
Sample Output

5
17
*/

import java.util.*;
import java.io.*;

public class LargestPrimeFactor {
    static void go() {
        int t = in.nextInt();
        boolean[] notPrimes = new boolean[1000000];
        //notPrimes[2] = true;
        for(int i = 2; i < 1000000; i++) {
            if (!notPrimes[i]) {
                for(int j = i+i; j < 1000000; j += i) {
                    notPrimes[j] = true;
                }
            }
        }

        while(t-- > 0) {
            long l = in.nextLong();
            long max = 1;
            long x = l;
            while(x > max) {
                boolean isPrime = true;
                for (int i = (int) Math.min(999999, x); i > 1; i--) {
                    if (!notPrimes[i] && (x % i == 0)) {
                        max = max < i ? i : max;
                        isPrime = false;
                        while(x % i == 0)
                            x /= i;
                        break;
                    }
                }
                if (isPrime) {
                    max = x;
                    break;
                }
            }
            if (max == 1)
                out.println(l);
            else
                out.println(max);
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