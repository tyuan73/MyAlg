package hack101.Sept2014;

/**
 * Created by yuantian on 10/1/14.
 */

/*

https://www.hackerrank.com/contests/101sep14/challenges/jim-and-the-skyscrapers


Jim has invented a new flying object called HZ42. HZ42 is like a broom and can only fly horizontally, independent of the environment. One day, Jim started his flight from Dubai's highest skyscraper, traveled some distance and landed on another skyscraper of same height! So much fun! But unfortunately, new skyscrapers have been built recently.

Let us describe the problem in one dimensional space. We have in total N skyscrapers aligned from left to right. The ith skyscraper has a height of hi. A flying route can be described as (i,j) with i≠j, which means, Jim starts his HZ42 at the top of the skyscraper i and lands on the skyscraper j. Since HZ42 can only fly horizontally, Jim will remain at the height hi only. Thus the path (i,j) can be valid, only if each of the skyscrapers i,i+1,...,j−1,j is not strictly greater than hi and if the height of the skyscraper he starts from and arrives on have the same height. Formally, (i,j) is valid iff ∄k∈[i,j]:hk>hi and hi=hj.

Help Jim in counting the number of valid paths represented by ordered pairs (i,j).

Input Format

The first line contains N, the number of skyscrapers. The next line contains N space separated integers representing the heights of the skyscrapers.

Output Format

Print an integer that denotes the number of valid routes.

Constraints

1≤N≤3⋅105 and no skyscraper will have height greater than 106 and lesser than 1.

Sample Input #00

6
3 2 1 2 3 3
Sample Output #00

8
Sample Input #01

3
1 1000 1
Sample Output #01

0
Explanation

First testcase: (1, 5), (1, 6) (5, 6) and (2, 4) and the routes in the opposite directions are the only valid routes.

Second testcase: (1, 3) and (3, 1) could have been valid, if there wasn't a big skyscraper with height 1000 between them.

*/

import java.util.*;
import java.io.*;

public class JimAndTheSkyscrapers {
    static void go() {
        int n = in.nextInt();
        long[][] s = new long[n + 1][2];
        s[0][0] = Integer.MAX_VALUE;
        s[0][1] = 1;
        int index = 1;
        long total = 0;
        for (int i = 0; i < n; i++) {
            int h = in.nextInt();
            if (h == s[index - 1][0])
                s[index - 1][1]++;
            else if (h < s[index - 1][0]) {
                s[index][0] = h;
                s[index][1] = 1;
                index++;
            } else {
                do {
                    index--;
                    total += s[index][1] * (s[index][1] - 1);
                } while (h > s[index - 1][0]);
                if (h == s[index - 1][0])
                    s[index - 1][1]++;
                else {
                    s[index][0] = h;
                    s[index][1] = 1;
                    index++;
                }
            }
        }
        while (--index > 0) {
            total += s[index][1] * (s[index][1] - 1);
        }

        out.println(total);
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