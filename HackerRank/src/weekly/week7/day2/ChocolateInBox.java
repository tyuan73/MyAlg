package weekly.week7.day2;

/**
 * Created by yuantian on 7/15/14.
 */

/*
Dexter and Debra are playing a game. They have N containers each having one or more chocolates. Containers are numbered from 1 to N, where ith container has A[i] number of chocolates.

The game goes like this. First player will choose a container and take one or more chocolates from it. Then, second player will choose a non-empty container and take one or more chocolates from it. And then they alternate turns. This process will continue, until one of the players is not able to take any chocolates (because no chocolates are left). One who is not able to take any chocolates loses the game. Note that player can choose only non-empty container.

The game between Dexter and Debra has just started, and Dexter has got the first Chance. He wants to know the number of ways to make a first move such that under optimal play, the first player always wins.

Input Format
The first line contains an integer N, i.e., number of containers.
The second line contains N integers, i.e., number of chocolates in each of the containers separated by a single space.

Output Format
Print the number of ways to make the first move such that under optimal play, the first player always wins. If the first player cannot win under optimal play, print 0.

Constraints
1 ≤ N ≤ 106
1 ≤ A[i] ≤ 109

Sample Input

2
2 3
Sample Output

1
Explanation

Only 1 set of moves helps player 1 win.

Player:      1      2      1      2      1
Chocolates: 2 3 -> 2 2 -> 1 2 -> 1 1 -> 0 1
*/

import java.util.*;
import java.io.*;

public class ChocolateInBox {
    static void go() {
        int n = in.nextInt();
        long[] a = new long[n];
        long xor = 0;
        for(int i = 0; i < n; i++) {
            a[i] = in.nextLong();
            xor ^= a[i];
        }

        int total = 0;
        for(int i = 0; i < n; i++)
            if (a[i] >= (a[i] ^ xor))
                total++;
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