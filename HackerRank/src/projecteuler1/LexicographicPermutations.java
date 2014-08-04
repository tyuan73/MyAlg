package projecteuler1;

/**
 * Created by yuantian on 8/4/14.
 */

/*
A permutation is an ordered arrangement of objects. For example, dabc is one possible permutation of the word abcd. If all of the permutations are listed alphabetically, we call it lexicographic order. The lexicographic permutations of abc are:
abc   acb   bac   bca   cab   cba
What is the Nth lexicographic permutation of the word abcdefghijklm?

Input Format
The first line contains an integer T , i.e., number of test cases.
Next T lines will contain an integer N.

Output Format
Print the values corresponding to each test case.

Constraints
1≤T≤1000
1≤N≤13!

Sample Input

2
1
2
Sample Output

abcdefghijklm
abcdefghijkml
*/

import java.util.*;
import java.io.*;

public class LexicographicPermutations {
    static void go() {
        long[] f = new long[14];
        f[0] = 1;
        for(int i = 1; i < 14; i++) {
            f[i] = f[i-1] * i;
        }
        int t = in.nextInt();
        while(t-- > 0) {
            char[] str = "abcdefghijklm".toCharArray();
            long n = in.nextLong() - 1;
            while(n > 0) {
                int num = 13;
                while(n < f[num])
                    num--;

                int start = 12-num;
                int offset = (int) (n / f[num]);
                perm(str, start, offset);
                n -= offset*f[num];
            }
            out.println(new String(str));
        }
    }

    static void perm(char[] str, int start, int offset) {
        char c = str[start+offset];
        for(int i = start+offset; i > start; i--) {
            str[i] = str[i-1];
        }
        str[start] = c;
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