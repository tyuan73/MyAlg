package weekly.week11.day1;

/**
 * Created by yuantian on 10/6/14.
 */

/*

https://www.hackerrank.com/contests/w11/challenges/sherlock-and-square


Watson gives a square of side length 1 to Sherlock. Now, after each second, each square of side L will break into four squares each of side L/2(as shown in the image below).

img

Now, Watson asks Sherlock: What will be the sum of length of solid lines after N seconds?

As the number can be large print result mod (109+7)
For example, after 0 seconds, the length is 4.
After 1 second, the length is 6.

Input Format
First line contains T, the number of testcases. Each testcase contains N in one line.

Output Format

For each testcase, print the required answer in a new line.

Constraints
1≤T≤105
0≤N≤109
Sample input

2
0
1
Sample output

4
6
*/

import java.util.*;
import java.io.*;

public class SherlockAndSquare {
    static long P = 1000000007;

    static void go() {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            out.println((pow(n + 1) + 2) % P);
        }
    }

    static long pow(int n) {
        long ret = 1;
        long base = 2;
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = (ret * base) % P;
            }
            base = (base * base) % P;
            n >>= 1;
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