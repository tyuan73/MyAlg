package projecteuler1;

/**
 * Created by yuantian on 7/30/14.
 */

/*
Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
If d(a)=b and d(b)=a, where a≠b, then a and b are an amicable pair and each of a and b are called amicable numbers.

For example, the proper divisors of 220 are 1,2,4,5,10,11,20,22,44,55 and 110 therefore d(220)=284. The proper divisors of 284 are 1,2,4,71 and 142 so d(284)=220.

Evaluate the sum of all the amicable numbers under N.

Input Format
The first line contains an integer T , i.e., number of test cases.
Next T lines will contain an integer N.

Output Format
Print the values corresponding to each test case.

Constraints
1≤T≤100
1≤N≤105

Sample Input

1
300
Sample Output

504
*/

/*
We should clarify that if the bound is between an amicable number and its pair then the smaller is counted,
and the larger not. At first I though the bound refered to the sum of all pairs where both numbers are bounded by N.
 */

import java.util.*;
import java.io.*;

public class AmicableNumbers {
    static void go() {
        int[] d = new int[100001];
        int[] ans = new int[100001];
        for (int i = 2; i <= 100000; i++) {
            d[i] = getD(i);
        }

        for (int i = 2; i <= 100000; i++) {
            int sum = d[i];
            if ((sum != i && sum <= 100000 && d[sum] == i) || (sum > 100000 && getD(sum) == i)) {
                ans[i] = ans[i - 1] + i;
            } else {
                ans[i] = ans[i - 1];
            }
        }

        int t = in.nextInt();
        while (t-- > 0) {
            out.println(ans[in.nextInt() - 1]);
        }
    }

    static int getD(int x) {
        int sum = 1, up = (int) Math.sqrt(x);
        for (int y = 2; y < up; y++) {
            if (x % y == 0) {
                sum += y + x / y;
            }
            if (up * up == x)
                sum += up;
        }
        return sum;
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