package addeparhackathon;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 6/28/14
 * Time: 9:40 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*


Joe goes to school in a very tall building. He attends N classes during the day, numbered chronologically from 1 through N. Class i is located on floor ai. Joe's schedule allows him to switch the order of two consecutive classes. Since Joe does not like to walk up stairs, he wants to choose the schedule that requires him to walk up the least number of stairs throughout the day (each pair of consecutive floors is separated by the same number of stairs). Help Joe determine which classes to switch to minimize the number of stairs he must walk up during the school day, assuming he starts counting at the beginning of his first class.

Input: The first line of the input contains a single integer N. The second line of the input contains N space-separated integers a1,a2,…,aN.

Output: A single integer f, if it is optimal for Joe to switch classes f and f+1. If switching no classes is at least as good as the best possible switch, output −1. Otherwise, if there are multiple possible f, output the smallest one.

Constraints: 1≤N≤100000, 1≤ai≤1000.

Scoring:

    Test cases worth a total of 70 points will have N≤5000.

Sample Input:
4
5 3 5 2

Sample Output:
2

Explanation: If classes 2 and 3 are switched, then Joe doesn't need to walk up any stairs during the day. This is clearly optimal.

*/

import java.util.*;
import java.io.*;

public class Upstairs {
    static void go() {
        int n = in.nextInt();
        int max = 0;
        int output = -1;
        int[] a = new int[n+2];
        for(int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }
        a[0] = Integer.MAX_VALUE;
        a[n+1] = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++) {
            int x = a[i] > a[i-1] ? a[i] - a[i-1] : 0;
            x += a[i+1] > a[i] ? a[i+1] - a[i] : 0;
            x += a[i+2] > a[i+1] ? a[i+2] - a[i+1] : 0;
            int y = a[i+1] > a[i-1] ? a[i+1] - a[i-1] : 0;
            y += a[i] > a[i+1] ? a[i] - a[i+1] : 0;
            y += a[i+2] > a[i] ? a[i+2] - a[i] : 0;
            if (x - y > max) {
                max = x - y;
                output = i;
            }
        }
        out.println(output);
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