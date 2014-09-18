package weekly.week10.day2; /**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 9/17/14
 * Time: 10:54 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

https://www.hackerrank.com/contests/w10/challenges/cipher


Jack and Daniel are friends.
They want to encrypt their conversation so that they can save themselves from interception by a detective agency. So they invent a new cipher.
Every message is encoded to its binary representation B of length N.
Then it is written down K times, shifted by 0,1,⋯,K−1 bits.
If B=1001010 and K=4 it looks so:

1001010
 1001010
  1001010
   1001010
Then calculate XOR in every column and write it down. This number is called S. For example, XOR-ing the numbers in the above example results in

1110100110
Then the encoded message S and K are sent to Daniel.

Jack is using this encoding algorithm and asks Daniel to implement a decoding algorithm.
Can you help Daniel implement this?

Input Format
The first line contains two integers N and K.
The second line contains string S of length N+K−1 consisting of ones and zeros.

Output Format
Decoded message of length N, consisting of ones and zeros.

Constraints
1≤N≤106
1≤K≤106
|S|=N+K−1
It is guaranteed that S is correct.

Sample Input#00

7 4
1110100110
Sample Output#00

1001010
Sample Input#01

6 2
1110001
Sample Output#01

101111
Explanation

Input#00

1001010
 1001010
  1001010
   1001010
----------
1110100110
Input#01

101111
 101111
-------
1110001
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Cipher {
    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.nextString();
        int x = 0;
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (char) (((s.charAt(i) - '0') ^ x) + '0');
            x ^= ans[i] - '0';
            if (i >= k - 1)
                x ^= ans[i - k + 1] - '0';
        }
        out.println(ans);
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