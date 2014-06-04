package weekly.week4.day3;/**
 * Created by yuantian on 6/4/14.
 */

/*
Little Lucy loves to arrange her flowers in patterns similar to those of a binary search tree. Her father, a computer scientist himself, takes note of this and finds out that she has N flowers. Every day she takes some of these N flowers and arranges them into all possible different patterns. The more the number of arrangements, the more time she spends outside.

Now, her father knows that she takes a non-empty subset of these N flowers any given day. He wants to find out the total number of arrangements that she can ever make. As this may be too large to handle, you only need to report final answer modulo 109+9 if the answer is no less than 109+9.

Note: All flowers are distinct and are labelled by distinct numbers.

Input Format
The first line contains an integer, T, the number of test cases.
The next T lines contain a single integer each and denote the labels of the respective N flowers.

Output Format
Output T lines each containing an answer to corresponding query.

Constraints

1 ≤ T ≤ 5000
1 ≤ N ≤ 5000

Sample Input

4
1
2
3
4
Sample Output

1
4
14
50
Explanation

For the first case, only one BST is possible.
For the second case, only 4 BSTs are possible (shown below).

1    2    1      2
           \    /
            2   1
Similarly, 14 BSTs are possible for N = 3, and 50 are possible for N = 4.
*/

import java.util.*;
import java.io.*;

public class LucyAndFlowers {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        long P = 1000000009;

        long[] a = new long[5001];
        a[1] = 1;
        for(int i = 2; i <= 5000; i++) {
            long cur = 0;
            for(int l = 0, r = i-1; r >= 0; l++, r--) {
                cur += (a[l]+1)*(a[r]+1);
                cur = cur % P;
            }
            a[i] = cur;
        }

        int t = in.readInt();
        while(t-- > 0) {
            int n = in.readInt();
            System.out.println(a[n]);
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
