package weekly.week7.day5;

/**
 * Created by yuantian on 7/18/14.
 */

/*
Natural numbers from 1 to N have been placed in an increasing order over some helix ( a circular structure ). When the helix starts rotating, it is easy to find out

The position of a given number
The number located at a given position.
The helix has numbers arranged in the following fashion:

[1, 2, 3, ..., N]
Due to some malfunction, the helix has started rotating in a weird manner. Right now, every possible contiguous interval can be rotated, and hence, locating a particular number or identifying the number at a given position is almost impossible. For example, if at some particular instant, the integer list is like this:

[1, 2, 3, 4, 5, ..., N]
rotating the interval [5...N] will leave the list like this:

[1, 2, 3, 4, N, N - 1, N - 2, ..., 5]
We need a software to handle this. Can you help us?

Input Format
The first line of the input consists of two space separated integers, N, Q. N signifies that initially our list contains numbers from 1 to N, placed in an increasing order. Q lines follow and contain input in one of the following formats:

1 A B
indicating that the helix rotated circularly in the interval [A..B] ( both inclusive);

2 A
indicating that we are interested in knowing the current position of the number A

3 A
indicating that we are interested in knowing the number at position A.

Output Format
For each line in the input of the form 2 A

output a line saying

element A is at position x
where A is the number we are interested in and x is its current position.

For each line of the form 3 A

output a line saying

element at position A is x
where A is the position we are interested in and x is the integer located at this position.

Constraints

1 ≤ N, Q ≤ 105
positions are 1-indexed.

Sample Input

5 10
1 1 3
2 3
3 3
1 3 5
1 2 4
3 1
3 5
2 4
1 5 5
2 2
Sample Output

element 3 is at position 1
element at position 3 is 1
element at position 1 is 3
element at position 5 is 1
element 4 is at position 2
element 2 is at position 4
Explanation

Initially elements are placed like this:

[1, 2, 3, 4, 5]
after the first rotation, they are placed like this:

[3, 2, 1, 4, 5]
and that's how we get the first 2 results (first 2 lines in the output). After second rotation, they are placed like this:

[3, 2, 5, 4, 1]
and third one does this:

[3, 4, 5, 2, 1]
In the last rotation (1 5 5), it's easy to see that output matches the initial positions of the elements. Last rotation doesn't change the positions of the elements.
*/

import java.util.*;
import java.io.*;

public class CrazyHelix {
    static void go() {
        int n = in.nextInt();
        int q = in.nextInt();

        int[] a = new int[n+1];
        for(int i = 0; i <= n; i++) {
            a[i] = i;
        }

        int[] idx = new int[n+1];
        for(int i = 0; i <= n; i++) {
            idx[i] = i;
        }

        while(q-- > 0) {
            int op = in.nextInt();
            switch (op) {
                case 1:
                    int l = in.nextInt();
                    int r = in.nextInt();
                    for(; l < r; l++, r--) {
                        int x = a[l];
                        int y = a[r];
                        a[l] = y;
                        a[r] = x;
                        idx[y] = l;
                        idx[x] = r;
                    }

                    break;
                case 2:
                    int e = in.nextInt();
                    out.println("element " + e + " is at position " + idx[e]);
                    break;
                case 3:
                    int p = in.nextInt();
                    out.println("element at position " + p + " is " + a[p]);
                    break;
            }
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