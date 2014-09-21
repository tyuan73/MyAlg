package CodeSprintIndia2014;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 9/20/14
 * Time: 9:51 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

https://www.hackerrank.com/contests/csindia/challenges/jigar-and-strings

Jigar is playing with strings. He likes a string, if all the characters in it are repeated the same number of times. We are given a string of length n, what is the minimum number of changes we have to make in this string so that Jigar likes it? We are only allowed to use lowercase English letters, and we can change any letter to any other letter.

Input Format
The first line contains an integer T, the number of test cases. This is followed by T test cases each containing 1 line:
Each testcase consists of a string composed of lowercase letters.

Output Format
For each testcase, print in a new line the minimum number of changes required.

Constraints
1 ≤ T ≤ 1370
1 ≤ n ≤ 1991

Sample Input

2
bbaccaaa
ccaacb
Sample Output

2
1
Explanation
One possible solution to first test is bbabbaaa. We have changed 2 'c' to 2 'b'. Now both 'a' and 'b' are repeated 4 times and Jigar would like this string.
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class JigarAndStrings {
    static void go() {
        int t = in.nextInt();
        while (t-- > 0) {
            String str = in.nextString();
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }
            Arrays.sort(count);

            int n = str.length();
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= 26; i++) {
                if (n % i == 0) {
                    int k = n / i;
                    int unchanged = 0;
                    for (int j = 25; j >= 26 - i; j--) {
                        unchanged += Math.min(count[j], k);
                    }
                    max = Math.max(max, unchanged);
                }
            }
            out.println(n - max);
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