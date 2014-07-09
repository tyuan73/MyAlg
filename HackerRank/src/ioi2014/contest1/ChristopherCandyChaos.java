package ioi2014.contest1;

/**
 * Created by yuantian on 7/9/14.
 */

/*
During its early years, Christopher's Candy Store offered only two flavors of candies - chocolate and strawberry. The Candy Store is popular for its "2 Chocolate 1 Strawberry" promo. You can buy a strip containing one strawberry candy and two chocolates for just the price of the chocolates! Talk about a steal!

Christopher has a machine that packs candies into capsules. These were known as candy strips. He just enters what flavors goes into a strip and the machine does it. But one day, the machine just malfunctioned and packed the candies randomly into one big strip. But that day, a high demand for the promo was met. And Christopher went into a panic.

Now, Christopher's idea is this: instead of throwing away the big strip and start anew, he should just trim the strip so that for every strawberry candy, there's two chocolate candies in the strip.

For example, if the strip was SSCCSCSCC, he could trim it from the left by removing the two strawberry candies. Then, trim it from the right and remove one chocolate candy. Thus, he's left with a strip looking like CCSCSC. This strip has one strawberry candy for every two chocolate candies.

As he recalls the fond memories of his newly-opened shop, he wondered how many ways he could trim each strip so that he could sell the remainder under the "2 Chocolate 1 Strawberry" promo.

Input Format
The first line contains a single integer, which is N.

The second line contains a string of length N, consisting of S and C.

Output Format
Output a single line containing how many ways he can tri the strips in order to make it sellable under the "2 Chocolate 1 Strawberry" promo.

Constraints
1≤N≤106

Sample Input

10
CCCCSSCCCC
Sample Output

7
Explanation
The seven possible trimmings are:

xxCCSxxxxx
xxxxxSCCxx
CCCCSSxxxx
xCCCSSCxxx
xxCCSSCCxx
xxxCSSCCCx
xxxxSSCCCC
*/

import java.util.*;
import java.io.*;

public class ChristopherCandyChaos {
    static void go() {
        int n = in.nextInt();
        String str = in.nextString();
        int[] a = new int[3*n+3];
        long total = 0;
        int count = 2*n;
        a[count] = 1;
        for(int i = 0; i < n; i++) {
            if (str.charAt(i) == 'C')
                count++;
            else
                count -= 2;
            total += a[count];
            a[count]++;
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