package CodeSprintIndia2014;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 9/19/14
 * Time: 10:38 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

https://www.hackerrank.com/contests/csindia/challenges/largest-lexicographical-rotation

Bidhan studies at the University of Dhaka. Recently he noticed that, some of the university students are speaking a strange language. The words used in that language are not meaningful to him. After some investigation he learned that, the students use the largest lexicographical rotation of words instead of the original words. Word p′ is a rotation of word p if,
p=a+b and
p′=b+a, where a and b are some strings ( possibly empty ) and + sign denotes the concatenation operation.
w, the largest lexicographical rotation of p is the lexicographically biggest one among all possible p′s.

Given a string, output it's largest lexicographical rotation.

Input Format
The first line of input will contain t, number of test cases. Each of the next t lines contains p.

Constraints
1≤t≤100
1≤|p|≤103
|p| denotes the length of p.
p will contain lowercase english letters only.

Sample Input

3
bidhan
zorro
apple
Sample Output

nbidha
zorro
pplea
Explanation

Test case 1 : The rotations of string bidhan are
bidhan,
idhanb,
dhanbi,
hanbid,
anbidh and
nbidha.
nbidha is lexicographically biggest among all of them.

*/

import java.util.*;
import java.io.*;

public class LargestLexicographicalRotation {
    static void go() {
        int t = in.nextInt();
        while (t-- > 0) {
            String str = in.nextString();
            int n = str.length();
            String[] suffix = new String[n];
            for (int i = 0; i < n; i++) {
                suffix[i] = str.substring(i) + str.substring(0, i);
            }
            Arrays.sort(suffix);
            out.println(suffix[n - 1].substring(0, n));
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

/*
input :
1
iovnqfnczgnjovvonpdflrxinkderxqwdnmzeeyvmprdmjcz

output:
ziovnqfnczgnjovvonpdflrxinkderxqwdnmzeeyvmprdmjc

zgnjovvonpdflrxinkderxqwdnmzeeyvmprdmjcziovnqfnc
* */