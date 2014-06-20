package weekly.week5.day3;

/**
 * Created by yuantian on 6/18/14.
 */

/*
https://www.hackerrank.com/contests/w5/challenges/kundu-and-tree

Kundu is true tree lover. Tree is a connected graph having N vertices and N-1 edges. Today when he got a tree, He colored each edges with one of either red(r) or black(b) color. He is interested in knowing how many triplets(a,b,c) of vertices are there, such that on the path from vertex a to b, vertex b to c and vertex c to a there is atleast one edge having red color. It should be noted that (a,b,c), (b,a,c) and all such permutation will be considered as the same triplets.

If the answer is no less than 109 + 7, print the answer modulo (%) 109 + 7.

Input Format
The first line contains an integer N, i.e., the number of vertices in tree.
The next N-1 lines will be representing edges i.e. pair of two numbers representing edges followed by color of edges. Colours of the edges will be a small letter of english alphabet and it will be either red(r) or black(b) each separated by a single space.

Output Format
Print a single number i.e. number of triplets.

Constraints
1 ≤ N ≤ 105
A node is numbered between 1 to N.

Sample Input

5
1 2 b
2 3 r
3 4 r
4 5 b
Sample Output

4
Explanation

Given tree is something like this.
img1

(2,3,4) is one such triplet because on all paths i.e 2 to 3, 3 to 4 and 2 to 4 there is at least one edge having red color.
(2,3,5), (1,3,4) and (1,3,5) are such other triplets.

Note:
--------------------------------------
all pathes (from a to b, from b to c, and from c to a) must have at least one red edge.
So, path (1,2,5) is not valid, because path from 1 to 2 does not have red edge.
--------------------------------------

*/

import java.util.*;
import java.io.*;

public class KunduAndTree {
    static List<Integer>[] tree = null;
    static int[] group = null;
    static boolean[] visited = null;
    static long P = 1000000007;

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);

        int n = in.nextInt();

        if (n < 3) {
            System.out.println(0);
            return;
        }

        tree = new List[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < n-1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            String color = in.nextString();
            if (color.equals("b")) {
                tree[from].add(to);
                tree[to].add(from);
            }
        }

        group = new int[n];
        visited = new boolean[n];
        int gid = 0;
        for(int i = 0; i < n; i++) {
            if (!visited[i]) {
                checkGroup(i, gid);
                gid++;
            }
        }

        //for(int i = 0; i < gid; i++)
        //    System.out.println(i + " : " + group[i]);

        long[] C = new long[n+1];
        C[3] = 1;
        for(int i = 4; i <= n; i++) {
            C[i] = (C[i-1] * i) % P;
            C[i] = (C[i] * calInverse(i-3, P-2)) % P;
        }

        //for(int i = 0; i <= n; i++)
        //    System.out.println(i + " : " + C[i]);

        long out = C[n];
        for(int i = 0; i < gid; i++) {
            long x = group[i];
            long g = ((x * (x - 1)) / 2 % P) * (n-x) % P;
            g = (g + C[group[i]]) % P;
            out += P - g;
            if (out >= P)
                out -= P;
        }
        System.out.println(out);
    }

    static long calInverse(long x, long p) {
        long ret = 1;
        long base = x;
        while(p > 0) {
            if ((p & 1) == 1) {
                ret = (ret * base) % P;
            }
            base = (base * base) % P;
            p >>= 1;
        }
        return ret;
    }

    static void checkGroup(int from, int gid) {
        visited[from] = true;
        group[gid]++;
        for(int next : tree[from]) {
            if (!visited[next]) {
                checkGroup(next, gid);
            }
        }
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
                sb.append((char)c);
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


