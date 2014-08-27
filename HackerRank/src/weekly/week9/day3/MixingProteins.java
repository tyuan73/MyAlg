package weekly.week9.day3;

/**
 * Created by yuantian on 8/27/14.
 */

/*

Some scientists are working on protein recombination, and during their research, they have found a remarkable fact: there are 4 proteins in the protein ring that mutate after every second according to a fixed pattern. For simplicity, proteins are called A,B,C,D (you know, protein names can be very complicated). A protein mutates into another one depending on itself and the protein right after it. Scientists determined that the mutation table goes like this:

    A   B   C   D
    _   _   _   _
A|  A   B   C   D
B|  B   A   D   C
C|  C   D   A   B
D|  D   C   B   A
Here rows denote the protein at current position, while columns denote the protein at the next position. And the corresponding value in the table denotes the new protein that will emerge. So for example, if protein i is A, and protein i + 1 is B, protein i will change to B. All mutations take place simultaneously. The protein ring is seen as a circular list, so last protein of the list mutates depending on the first protein.

Using this data, they have written a small simulation software to get mutations second by second. The problem is that the protein rings can be very long (up to 1 million proteins in a single ring) and they want to know the state of the ring after upto 109 seconds. Thus their software takes too long to report the results. They ask you for your help.

Input Format
Input contains 2 lines.
First line has 2 integers N and K, N being the length of the protein ring and K the desired number of seconds.
Second line contains a string of length N containing uppercase letters A,B, C or D only, describing the ring.

Output Format
Output a single line with a string of length N, describing the state of the ring after K seconds.

Constraints
1≤N≤106
1≤K≤109

Sample Input:

5 15
AAAAD
Sample Output:

DDDDA
Explanation
The complete sequence of mutations is:

AAADD
AADAD
ADDDD
DAAAD
DAADA
DADDD
DDAAA
ADAAD
DDADD
ADDAA
DADAA
DDDAD
AADDA
ADADA
DDDDA

*/

import java.util.*;
import java.io.*;

public class MixingProteins {
    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();
        String str = in.nextString();
        int[] p = new int[n];
        for (int i = 0; i < n; i++)
            p[i] = str.charAt(i) - 'A';

        int[] q = new int[n];
        int[] temp = null;
        for (int bit = 1 << 30; bit > 0; bit >>= 1) {
            if ((k & bit) > 0) {
                for (int j = 0; j < n; j++) {
                    q[j] = p[j] ^ p[(j + bit) % n];
                }
                temp = p;
                p = q;
                q = temp;
            }
        }

        char[] s = new char[n];
        for (int x = 0; x < n; x++)
            s[x] = (char) (p[x] + 'A');
        out.println(s);
        //out.println(new String(s));
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