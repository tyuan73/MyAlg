package challenges.bendersplay;

/**
 * Created by yuantian on 6/25/14.
 */

/*
General Iroh and Commandant Bumi are heading to the Republic City to stop a rebellion. But it's quite a long travel, so in the meantime they have started discussing about possible attacking plans. Right now, they're arguing about best ways for moving soldiers during the battle. Tired of not getting a final and concise strategy, Iroh proposed a particularly original idea.

Iroh:

Bumi, look at this map: here we have all possible locations in the battle field soldiers can occupy. I know a game which can give us some ideas.
Bumi:

A game? How will a game help us here?
Iroh:

It's pretty simple, we know which location is connected to each one, and also, that all those paths between locations are one-way (it's to dangerous to have two ways paths), so we place some soldiers at random initial locations, take turns, and in each turn, we try to make a valid move with one soldier from one location to another. Eventually, we won't be able to move any man so, the first one which is not able to perform any valid move, loses. One important thing is, at some moment, we may have some men at the same field location.
Bumi:

Are you sure we are gonna end this? We have so many locations and paths... don't know, soldiers could be moving in circles for ever.
Iroh:

Take it easy man, those paths were built by the best architects I've ever known, so there is no way that could happen.
Bumi:

Well, I still don't get how does this help us.
Iroh:

Me neither, but greatest generals from the Earth Kingdom created their strategies from this game, so, who knows?
Bumi:

Ok, I'm in. Who plays first?
Iroh:

You go first my friend. Just make sure you always do your best, because I will show no mercy to you :).
Input Format

First line in the input contains two integers N and M, describing the number of locations and paths between them, respectively. M lines follow, each one with two integers u and v, denoting a one-way path from u to v.
Then comes a line with a single integer Q, denoting how many times Bumi and Iroh played the game over the given field. Q queries follow each one with two lines, first one with a single integer K, the number of soldiers in the field; and second one with K integers b_i separated by space, each one denoting the initial location of some soldier.

Output Format

Output Q lines, each one saying Bumi if Bumi should be the winner of the corresponding game or Iroh otherwise.
Remember that, being both top strategy masters, they will always perform the best possible move each turn.

Constraints

1 < N <= 105
1 <= M <= 106
1 <= u, v, b_i <= N
1 <= K <= 102
1 <= Q <= 105

Sample Input:

10 10
1 10
3 10
7 8
6 8
7 4
9 4
7 6
5 8
1 8
2 8
5
4
10 7 6 4
3
1 9 4
3
8 3 5
3
4 9 7
3
7 9 10
Sample Output:

Bumi
Iroh
Iroh
Bumi
Bumi
*/

import java.util.*;
import java.io.*;

public class Solution {
    static List<Integer>[] map = null;
    static boolean[] visited = null;
    static int[] order = null;
    //static int[] nim = null;
    static int idx = 0;

    static void go() {

        int n = in.nextInt();
        int m = in.nextInt();
        map = new List[n];
        for(int i = 0; i < n; i++)
            map[i] = new ArrayList<Integer>();

        while(m-- > 0) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            map[from].add(to);
        }

        visited = new boolean[n];
        order = new int[n];
        idx = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i);
            }
        }

        int[] nim = new int[n];
        for(int i = 0; i < n; i++) {
            int c = order[i];
            int len = map[c].size();
            int[] y = new int[len];
            for(int j = 0; j < len; j++) {
                y[j] = nim[map[c].get(j)];
            }
            int x = 0;
            while(x < len) {
                int z = y[x];
                if (z == x) {
                    x++;
                    continue;
                }
                if (z < x || z >= len || y[z] == z) {
                    y[x] = y[--len];
                } else {
                    y[x] = y[z];
                    y[z] = z;
                }
            }
            nim[c] = x;
        }

        int q = in.nextInt();
        while(q-- > 0) {
            int k = in.nextInt();
            int N = 0;
            for(int i = 0; i < k; i++) {
                int next = in.nextInt() - 1;
                N ^= nim[next];
            }
            if (N == 0) {
                out.println("Iroh");
            } else {
                out.println("Bumi");
            }
        }
    }

    static void dfs(int c) {
        visited[c] = true;
        for(int next : map[c]) {
            if(!visited[next])
                dfs(next);
        }
        order[idx++] = c;
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