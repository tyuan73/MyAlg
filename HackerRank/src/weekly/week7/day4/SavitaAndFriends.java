package weekly.week7.day4;

/**
 * Created by yuantian on 7/17/14.
 */

/*
After completing her final semester, Savita is back home. She is excited to meet all her friends. Her N friends live in different houses spread across the city.

There are M roads connecting the houses. The road network formed is connected and does not contain self loops and multiple roads between same pair of houses. Savita and Friends decide to meet.

Savita wants to choose a point(not necessarily an integer) P on the road numbered K, such that, the maximum of dist(i) for all 1≤i≤N is minimised,
where dist(i) is the shortest distance between the i'th friend and P.

If K'th road connects friend A and friend B you should print distance of chosen point from A. Also, print the max(dist(i)) for all 1≤i≤N. If there is more than one solution, print the one in which the point P is closest to A.

Note:

Use scanf/printf instead of cin/cout. Large input files.
Order of A and B as given in the input must be maintained. If P is at a distance of 8 from A and 2 from B, you should print 8 and not 2.
Input Format
First line contain T, the number of testcases.
T testcases follow.
First Line of each testcase contains 3 space separated integers N,M,K .
Next M lines contain description of the ith road : three space separated integers A,B,C, where C is the length of road connecting A and B.

Output Format
For each testcase, print two space separated values in one line. The first value is the distance of P from the point A and the second value is the maximum of all the possible shortest paths between P and all of Savita's and her friends' houses. Round both answers to 5 decimal digits and print exactly 5 digits after the decimal point.

Constraints
1≤T≤10
2≤N,M≤105
N−1≤M≤N∗(N−1)/2
1≤A,B≤N
1≤C≤109
1≤K≤M

Sample Input

2
2 1 1
1 2 10
4 4 1
1 2 10
2 3 10
3 4 1
4 1 5
Sample Output

5.00000 5.00000
2.00000 8.00000
Explanation

First testcase:
As K = 1, they will meet at the point P on the road that connects friend 1 with friend 2. If we choose mid point then distance for both of them will be 5. In any other position the maximum of distance will be more than 5.

Second testcase:
As K = 1, they will meet at a point P on the road connecting friend 1 and friend 2. If we choose point at a distance of 2 from friend 1: Friend 1 will have to travel distance 2.
Friend 2 will have to travel distance 8.
Friend 3 will have to travel distance 8.
Friend 4 will have to travel distance 7.
So, the maximum will be 8.
In any other position of point choosen, the maximum distance will be more than 8.

Timelimits

Timelimits for this problem is 2 times the environment limit.
*/


import java.util.*;
import java.io.*;

public class SavitaAndFriends {
    static List<City>[] g = null;
    static long[][] shortest = null;
    static int[] order = null;
    static int index = 0;

    static class Path implements Comparable<Path> {
        long v;
        int id;
        public Path(int id, long v) {
            this.id = id; this.v = v;
        }
        public int compareTo(Path p) {
            long d = this.v - p.v;
            if (d > 0)
                return 1;
            else if (d == 0)
                return 0;
            return -1;
        }
    }

    static class City {
        int to; long len;
        public City(int id, int len) {
            this.to = id; this.len = len;
        }
    }

    static void go() {
        int t = in.nextInt();
        int a = 0, b = 0, l = 0;

        while(t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            g = new List[n];
            for(int i = 0; i < n; i++) {
                g[i] = new ArrayList<City>();
            }
            for(int i = 1; i <= m; i++) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                int c = in.nextInt();
                g[from].add(new City(to, c));
                g[to].add(new City(from, c));
                if (i == k) {
                    a = from; b = to;
                    l = c;
                }
            }

            shortest = new long[2][n];
            order = new int[n];
            //index = 0;

            dijkstra(b, 1);
            dijkstra(a, 0);

            /* for test */
            /*
            for(double i : shortest[0])
                out.print(i + " ");
            out.println();
            for(double i : shortest[1])
                out.print(i + " ");
            out.println();
            */

            double prey = shortest[1][order[n-1]];
            double dist = 0;
            double max = shortest[0][order[n-1]];
            for(int i = n-2; i >= 0; i--) {
                int cur = order[i];
                double x = shortest[0][cur];
                double y = shortest[1][cur];
                if (y <= prey)
                    continue;
                double z = (x+prey+l)/2;
                if (z < max) {
                    max = z;
                    dist = (prey-x+l)/2;
                }
                prey = y;
            }
            if (prey < max) {
                max = prey;
                dist = l;
            }

            out.println(String.format("%.5f", dist) + " " + String.format("%.5f", max));
        }
    }

    static void dijkstra(int start, int idx) {
        long[] path = shortest[idx];
        Arrays.fill(path, Long.MAX_VALUE);
        PriorityQueue<Path> q = new PriorityQueue<Path>();
        q.add(new Path(start, 0));
        index = 0;

        while(q.size() > 0) {
            Path x = q.poll();
            if (x.v >= path[x.id])
                continue;

            path[x.id] = x.v;
            order[index++] = x.id;
            for(City y : g[x.id]) {
                if (path[x.id] + y.len < path[y.to])
                    q.add(new Path(y.to, path[x.id] + y.len));
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