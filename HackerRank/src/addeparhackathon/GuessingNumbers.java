package addeparhackathon;

/**
 * Created by yuantian on 7/1/14.
 */

/*
Joe picks an integer from the list 1,2,…,N, with a probability pi of picking i for all 1≤i≤N. He then gives Jason K attempts to guess his number. On each guess, Joe will tell Jason if his number is higher or lower than Jason's guess. If Jason guesses Joe's number correctly on any of the K guesses, the game terminates and Jason wins. Jason loses otherwise. If Jason knows all pi's and plays optimally, what is the probability he wins?

Input: The first line contains two space-separated integers N,K. The second line contains N space-separated numbers p1,p2,…,pN, expressed as real numbers with 8 digits after the decimal point.

Output: A single line containing the probability that Jason wins. The output will be considered correct if its absolute error does not exceed 10−6.

Constraints: 1≤N≤200000,1≤K≤20,p1+p2+⋯+pn=1,pi≥0.

Scoring:

Test cases worth 20 points will have N≤40.
Test cases worth an additional 40 points will have N≤150.
Sample Input:
5 1
0.20000000 0.30000000 0.40000000 0.10000000 0.00000000

Sample Output:
0.40000000

Explanation: With only one attempt, Jason wins iff his first guess is correct. So to maximize the probability he wins, he guesses 3 and wins with probability p3=0.4.
*/

import java.util.*;
import java.io.*;

public class GuessingNumbers {
    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();
        double[] p = new double[n];

        for(int i = 0; i < n; i++) {
            p[i] = Double.parseDouble(in.nextString());
        }

        Arrays.sort(p);

        int total = (1 << k) - 1;
        if (total >= n) {
            out.println(1);
            return;
        }
        double ans = 0;
        for(int i = 1; i <= total; i++)
            ans += p[n-i];

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