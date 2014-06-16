package weekly.week5.day1;/**
 * Created by yuantian on 6/16/14.
 */

/*
You are given an array A of size N. You are also given an interger Q. Can you figure out the answer to each of the Q queries?

Each query contains 2 integers x and y, and you need to find whether the value find(x,y) is Odd or Even:

find(int x,int y)
{
    if(x>y) return 1;
    ans = pow(A[x],find(x+1,y))
    return ans
}
Note : pow(a,b) = ab.

Input Format
The first line of the input contains an integer N. The next line contains N space separated non-negative integers(whole numbers less than or equal to 9).
The line after that contains a positive integer, Q , the denotes the number of queries to follow. Q lines follow, each line contains two positive integer x and y separated by a single space.

Output Format
For each query, display 'Even' if the value returned is Even, otherwise display 'Odd'.

Constraints
2 ≤ N ≤ 105
2 ≤ Q ≤ 105
1 ≤ x,y ≤ N
x ≤ y

No 2 consecutive entries in the array will be zero.

Sample Input

3
3 2 7
2
1 2
2 3
Sample Output

Odd
Even
Explanation

find(1,2) = 9, which is Odd
find(2,3) = 128, which is even
*/

/**
 * test case
 * 3
 * 2 0 7
 * 2
 * 1 2
 * 1 1
 *
 * output:
 * Odd
 * Even
 */

import java.util.*;
import java.io.*;

public class EvenOddQuery {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        //OutputWriter out = new OutputWriter(System.out);

        int n = in.readInt();
        int[] a = new int[n];

        for(int i = 0; i < n; i++) {
            a[i] = in.readInt();
        }

        int q = in.readInt();
        while(q-- > 0) {
            int x = in.readInt() - 1;
            int y = in.readInt() - 1;
            if ((a[x] & 1) == 1) {
                System.out.println("Odd");
            } else {
                if (y > x && a[x+1] == 0)
                    System.out.println("Odd");
                else
                    System.out.println("Even");
            }
        }
    }
}

class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

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

    public int peek() {
        if (numChars == -1)
            return -1;
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                return -1;
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public long readLong() {
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

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return isWhitespace(c);
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public boolean isExhausted() {
        int value;
        while (isSpaceChar(value = peek()) && value != -1)
            read();
        return value == -1;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void close() {
        writer.close();
    }

    public void printLine(long i) {
        writer.println(i);
    }

    public void printLine(int i) {
        writer.println(i);
    }
}
