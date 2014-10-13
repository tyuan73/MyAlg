/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/21/14
 * Time: 10:59 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
/*
The problem is quite simple. You’re given a number N and a positive integer K. Tell if N can be represented as a sum of K prime numbers (not necessarily distinct).

Input Format
The first line contains a single integer T, denoting the number of test cases.
Each of the next T lines contains two positive integers, N & K, separated by a single space.

Output Format
For every test case, output “Yes” or “No” (without quotes).

Constraints
1 <= T <= 5000
1 <= N <= 1012
1 <= K <= 1012

Sample Input

2
10 2
1 6

Sample Output

Yes
No

Explanation

In the first case, 10 can be written as 5 + 5, and 5 is a prime number. In the second case, 1 cannot be represented as a sum of prime numbers, because there are no prime numbers less than 1.
 */

import java.io.*;
import java.util.*;

public class PrimeSum {
    static int[] primes = new int[1000009];
    static int total = 0;

    static public void go() {
        InputReader in = new InputReader(System.in);

        primes[0]= primes[1]=1;       // we know 0 and 1 are not prime.

        total = 0;
        for (int i=2;i< primes.length;i++) {
            if(primes[i] == 0) {
                primes[total++] = i;
                for (int j=2;i*j< primes.length;j++) {
                    primes[i*j]=1;
                }
            }
        }

        int t = in.nextInt();
        while(t-- > 0) {
            long n = in.nextLong();
            long k = in.nextLong();

            if (n < 2*k) {
                System.out.println("No");
                continue;
            }

            if (k == 1) {
                if (isPrime(n)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
                continue;
            }

            if (k == 2) {
                if (n % 2 == 0) {
                    System.out.println("Yes");
                } else {
                    if (isPrime(n-2)) {
                        System.out.println("Yes");
                    } else {
                        System.out.println("No");
                    }
                }
                continue;
            }

            System.out.println("Yes");
        }
    }

    static boolean isPrime(long x) {
        if (x == 1)
            return false;
        if (x == 2)
            return true;
        long y = (long) Math.sqrt(x) + 1;
        int i = 0;
        while(primes[i] < y) {
            if (x % primes[i] == 0)
                return false;
            i++;
        }

        return true;
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

