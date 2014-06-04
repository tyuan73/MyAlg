package weekly.week4.day2;

/**
 * Created by yuantian on 6/3/14.
 */
/*
Devendra is on cloud nine after seeing his crush smiling at him in class. At that very moment his professor singles him out and asks him a question. Devendra’s mind is all too fuzzy with his crush and her smile to concentrate on anything else. Help him solve the problem :

You are given a list of size N, initialized with zeroes. You have to perform M queries on the list and output the maximum of final values of all the N elements in the list. For every query, you are given three integers a, b and k and you have to add value k to all the elements ranging from index a to b(both inclusive).

Input Format
First line will contain two integers N and M separated by a single space.
Next M lines will contain three integers a, b and k separated by a single space.
Numbers in list are numbered from 1 to N.

Output Format
A single line containing maximum value in the final list.

Constraints
3 ≤ N ≤ 107
1 ≤ M ≤ 2 * 105
1 ≤ a ≤ b ≤ N
0 ≤ k ≤ 109

Sample Input #00

5 3
1 2 100
2 5 100
3 4 100
Sample Output #00

200
Explanation

After first update list will be 100 100 0 0 0.
After second update list will be 100 200 100 100 100.
After third update list will be 100 200 200 200 100.
So the required answer will be 200.
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class AlgorithmicCrush {
    public static void main(String[] args) {

        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        int n = in.readInt();
        int m = in.readInt();

        long[] list = new long[n+3];

        while(m-- > 0) {
            int a = in.readInt();
            int b = in.readInt();
            long k = in.readLong();

            list[a] += k;
            list[b+1] -= k;
        }

        long max = 0;
        long c = 0;
        for (int i = 1; i <= n; i++) {
            c += list[i];
            max = Math.max(max, c);
        }
        System.out.println(max);
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