package weekly.week9.day1;

/**
 * Created by yuantian on 8/25/14.
 */

/*
Sansa has an array. She wants to find the value obtained by XOR-ing the contiguous subarrays, followed by XOR-ing the values thus obtained. Can you help her in this task?

Note : [1,2,3] is contiguous subarray of [1,2,3,4] while [1,2,4] is not.

Input Format
First line contains an integer T, number of the test cases.
The first line of each test case contains an integer N, number of elements in the array.
The second line of each test case contains N integers that are elements of the array.

Output Format
Print the answer corresponding to each test case in a seperate line.

Constraints
1≤T≤5
2≤N≤105
1≤numbers in array≤108

Sample Input

1
3
1 2 3
Sample Output

2
Explanation

1⊕2⊕3⊕(1⊕2)⊕(2⊕3)⊕(1⊕2⊕3)=2
*/

import java.util.*;
import java.io.*;

public class SansaAndXOR {
    static void go() {
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++)
                a[i] = in.nextInt();

            int x = 0;
            if ((n & 1) != 0) {
                for(int i = 0; i < n; i += 2) {
                    x ^= a[i];
                }
            }
            out.println(x);
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