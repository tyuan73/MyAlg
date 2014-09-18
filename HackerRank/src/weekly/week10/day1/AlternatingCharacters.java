package weekly.week10.day1;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 9/17/14
 * Time: 10:35 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

https://www.hackerrank.com/contests/w10/challenges/alternating-characters


Shashank likes strings in which consecutive characters are different. For example, he likes ABABA, while he doesn't like ABAA. Given a string containing characters A and B only, he wants to change it into a string he likes. To do this, he is allowed to delete the characters in the string.

Your task is to find the minimum number of required deletions.

Input Format
The first line contains an integer T i.e. the number of test cases.
Next T lines contain a string each.

Output Format
Print minimum number of required steps for each test case.

Constraints

1≤T≤10
1≤lengthofString≤105

Sample Input

5
AAAA
BBBBB
ABABABAB
BABABA
AAABBB
Sample Output

3
4
0
0
4
Explanation

AAAA⟹A, 3 deletions
BBBBB⟹B, 4 deletions
ABABABAB⟹ABABABAB, 0 deletions
BABABA⟹BABABA, 0 deletions
AAABBB⟹AB, 4 deletions
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class AlternatingCharacters {
    static void go() {
        int t = in.nextInt();

        while (t-- > 0) {
            String line = in.nextString();
            int count = 0;
            for (int i = 1; i < line.length(); i++) {
                if (line.charAt(i) == line.charAt(i - 1))
                    count++;
            }
            out.println(count);
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