package weekly.week5.day2;/**
 * Created by yuantian on 6/17/14.
 */

/*
https://www.hackerrank.com/contests/w5/challenges/closest-number

You are given 3 numbers a, b and x. You need to output the multiple of x which is closest to ab. If more than one answer exists , display the smallest one.

Input Format
The first line contains T, the number of testcases.
T lines follow, each line contains 3 space separated integers (a, b and x respectively)

Output Format
For each test case , output the multiple of x which is closest to ab

Constraints
1 ≤ T ≤ 105
1 ≤ x ≤ 109
0 < ab ≤ 109
1 ≤ a ≤ 109
-109 ≤ b ≤ 109

Sample Input

3
349 1 4
395 1 7
4 -2 2
Sample Output

348
392
0
Explanation

The closest multiple of 4 to 349 is 348.
The closest multiple of 7 to 395 is 392.
The closest multiple of 2 to 1/16 is 0.
*/

import java.util.*;
import java.io.*;

public class ClosestNumber {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        int t = in.readInt();
        while(t-- > 0) {
            long a = in.readInt();
            long b = in.readInt();
            int x = in.readInt();

            if (b > 0) {
                if (b >= 30) {
                    if (x == 1)
                        System.out.println(1);
                    else
                        System.out.println(0);
                } else {
                    long l = 1;
                    for(int i = 0; i < b; i++) {
                        l *= a;
                    }
                    long m = l/x;
                    if (l - m*x > (m+1)*x - l) {
                        System.out.println((m+1) * x);
                    } else
                        System.out.println(m*x);
                }
            } else if (b == 0) {
                if (x == 1)
                    System.out.println(1);
                else
                    System.out.println(0);
            } else {
                if (a == 1) {
                    if (x == 1)
                        System.out.println(1);
                    else
                        System.out.println(0);
                } else {
                    System.out.println(0);
                }
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
