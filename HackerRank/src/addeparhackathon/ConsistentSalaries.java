package addeparhackathon;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 6/29/14
 * Time: 12:38 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*


At a large bank, each of N employees e besides the CEO (employee #1) reports to exactly one person ke (it is guaranteed that there are no cycles in the reporting graph). Initially, each employee e has salary se.

The CEO performs Q operations. Each operation i designates an employee e and a dollar amount v, and e and anyone who reports to e (directly or indirectly) has v added to their salary. Determine after which operations there exists a worker who makes strictly more than someone she reports to (directly or indirectly).

Input: The first line contains two space-separated integers N,Q. The second line contains N−1 space-separated integers k2,k3,…,kN (employee 1 is the CEO and does not report to anyone). The third line contains N space-separated integers s1,s2,…,sN. Q lines follow, the i-th of which contains two space-separated integers ei,vi, representing the i-th operation.

Output: Q lines. The i-th line should be "GOOD" (without the quotes) if no worker has a strictly higher salary than someone she reports to and "BAD" otherwise.

Constraints: 1≤N,Q≤200000,1≤ke,ei≤N,1≤se≤107,−1000≤vi≤1000.

Scoring:

    Test cases worth 30 points will have 1≤N,Q≤300.
    Test cases worth an additional 20 points will have 1≤N,Q≤5000.

Sample Input:
4 4
1 1 3
40 30 20 10
4 20
4 -20
2 15
1 30

Sample Output:
BAD
GOOD
BAD
BAD

Explanation: After the first operation, employee #4 makes more than his supervisor employee #3. After the second operation, no employee makes more than any of his supervisors. After the third operation, employee #2 makes more than his supervisor employee #1. After the fourth operation, employee #2 makes more than his supervisor employee #1.

*/

import java.util.*;
import java.io.*;

public class ConsistentSalaries {
    static List<Integer>[] tree = null;
    static long[] s = null;
    static long[] a = null;
    static HashSet<Integer> set = null;
    static void go() {
        int n = in.nextInt();
        int q = in.nextInt();

        tree = new List[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for(int i = 1; i < n; i++) {
            int from = in.nextInt() - 1;
            tree[from].add(i);
        }

        s = new long[n];
        for(int i = 0; i < n; i++)
            s[i] = in.nextLong();

        a = new long[n];
        a[0] = Long.MIN_VALUE;
        set = new HashSet<Integer>();
        dfs(0);
        while(q-- > 0) {
            int idx = in.nextInt() - 1;
            int v = in.nextInt();
            a[idx] += v;
            if (a[idx] >= 0)
                set.add(idx);
            else
                set.remove(idx);
            if (set.size() > 0)
                System.out.println("BAD");
            else
                System.out.println("GOOD");
            for(int x : set) {
                System.out.print(x);
            }
            System.out.println();
        }
    }

    static void dfs(int parent) {
        for(int next : tree[parent]) {
            a[next] = s[next] - s[parent];
            if (a[next] >= 0)
                set.add(next);
            dfs(next);
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

/*
10 6
3 1 1 1 10 3 10 3 5
1000 800 900 900 900 800 700 700 800 810
10 90
10 100
2 110
10 -200
2 -200
4 100

output:
GOOD
BAD
BAD
BAD
GOOD
GOOD

 */