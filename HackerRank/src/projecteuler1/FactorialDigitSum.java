package projecteuler1;

/**
 * Created by yuantian on 7/29/14.
 */

/*
For example, 10!=10×9×⋯×3×2×1=3628800,
and the sum of the digits in the number 10! is 3+6+2+8+8+0+0=27.

Find the sum of the digits in the number N!
Input Format
The first line contains an integer T , i.e., number of test cases.
Next T lines will contain an integer N.

Output Format
Print the values corresponding to each test case.

Constraints
1≤T≤100
0≤N≤1000

Sample Input

2
3
6
Sample Output

6
9
*/

import java.util.*;
import java.io.*;

public class FactorialDigitSum {
    static void go() {
        int[] dp = new int[1001];
        dp[0] = 1;
        String a = "1";
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= 1000;i ++) {
            sb.setLength(0);
            int c = 0;
            int total = 0;
            for(int j = 0; j < a.length(); j++) {
                int num = (a.charAt(j) - '0') * i + c;
                sb.append((num % 10));
                total += num % 10;
                c = num / 10;
            }
            while(c > 0) {
                sb.append(c%10);
                total += c % 10;
                c /= 10;
            }
            dp[i] = total;
            a = sb.toString();

            int last = 0;
            while(a.charAt(last) == '0') {
                last++;
            }
            a = a.substring(last);
        }

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            out.println(dp[n]);
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