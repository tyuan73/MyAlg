package weekly.week11.day3;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 10/8/14
 * Time: 12:50 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

https://www.hackerrank.com/contests/w11/challenges/tree-pruning


Given a tree with N vertices numbered from 1 to N. The vertex 1 is the root of the tree. Each vertex is assigned with an integer weight. A remove operation can remove sub-tree rooted at an arbitrary vertex u. You must use at most K remove operations (possibly zero) so that the total weight of the remaining vertices is largest.

Input Format
The first line contains two integers N,K.
The second line contains N integers, the ith integer is the weight of the ith vertex.
Each of the next N−1 lines contains a pair of integers u and v, which represents an edge from vertex u to vertex v.

Output Format
Print out a single integer which is the largest total weight of the remaining vertices.

Constraints
2≤N≤105
1≤K≤200
−109≤weight≤109
Sample Input

5 2
1 1 -1 -1 -1
1 2
2 3
4 1
4 5
Sample Output

2
Explanation
We use 2 remove operations: one for the sub-tree rooted at 3 and the other one for the sub-tree rooted at 4. There are only 2 remaining vertices which are 1 and 2. The total weight is 1+1=2.

*/

import java.util.*;
import java.io.*;

public class TreePruning {
    static List<Integer>[] tree = null;
    static boolean[] visited = null;
    static int[] order = null;
    static int[] last = null;
    static int index = 0;

    static void go() {
        int n = in.nextInt();
        int k = in.nextInt();

        tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<Integer>();

        long[] w = new long[n];
        for (int i = 0; i < n; i++) {
            w[i] = in.nextLong();
        }

        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            tree[from].add(to);
            tree[to].add(from);
        }

        visited = new boolean[n];
        order = new int[n];
        index = 0;
        last = new int[n];

        dfs(0);

        long[] dp = new long[n + 1];
        for (int i = 1; i < n; i++)
            dp[i] = dp[i - 1] + w[order[i - 1]];
        for (int x = 0; x < k; x++) {
            for (int i = n; i >= 1; i--) {
                // if node i is not selected, all subtree should be removed,
                // update the last node to dp[i-1]
                int lastIndex = i + last[order[i - 1]];
                dp[lastIndex] = Math.max(dp[lastIndex], dp[i - 1]);
            }

            for (int i = 1; i <= n; i++) {
                // if node i is selected, update current value to "dp[i-1] + current weight".
                dp[i] = Math.max(dp[i], dp[i - 1] + w[order[i - 1]]);
            }
        }

        out.println(dp[n]);
    }

    static void dfs(int v) {
        visited[v] = true;
        int start = index;
        order[index++] = v;
        for (int next : tree[v]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
        last[v] = index - start - 1;
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


/*
input:
20 5
773581246 -348306003 -788117784 629111611 -142726426 241605607 418519531 -291199082 -453706450 -850635818 -641575760 453047217 -874946563 -257858612 927125122 860575225 -162713554 61368550 -262466871 361084678
2 1
3 2
4 2
5 1
6 3
7 2
8 1
9 6
10 7
11 8
12 1
13 7
14 10
15 3
16 14
17 14
18 15
19 17
20 3

output:
2729019775
 */