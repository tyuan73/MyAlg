/**
 * Created by yuantian on 5/7/14.
 */
/*
HuarongdaoUnoptimized is a well-known game in China. The purpose of this game is to move the Cao Cao block out of the board.

Acme is interested in this game, and he invents a similar game. There is a N*M board. Some blocks in this board are movable, while some are fixed. There is only one empty position. In one step, you can move a block to the empty position, and it will take you one second. The purpose of this game is to move the Cao Cao block to a given position. Acme wants to finish the game as fast as possible.

But he finds it hard, so he cheats sometimes. When he cheats, he spends K seconds to pick a block and put it in an empty position. However, he is not allowed to pick the Cao Cao block out of the board .

Note

Immovable blocks cannot be moved while cheating.
A block can be moved only in the directions UP, DOWN, LEFT or RIGHT.
Input Format
The first line contains four integers N, M, K, Q separated by a single space. N lines follow.
Each line contains M integers 0 or 1 separated by a single space. If the jth integer is 1, then the block in ith row and jth column is movable. If the jth integer is 0 then the block in ith row and jth column is fixed. Then Q lines follows, each line contains six integers EXi, EYi, SXi, SYi, TXi, TYi separated by a single space. The ith query is the Cao Cao block is in row SXi column SYi, the exit is in TXi, TYi, and the empty position is in row EXi column EYi. It is guaranteed that the blocks in these positions are movable. Find the minimum seconds Acme needs to finish the game. If it is impossible to finish the game, you should answer -1.

Output Format
You should output Q lines, i-th line contains an integer which is the answer to i-th query.

Constraints

N,M ≤ 200
1 ≤ Q ≤ 250
10 ≤ K≤ 15
1 ≤ EXi, SXi, TXi≤ N
1 ≤ EYi, SYi,TYi ≤ M

Sample Input

5 5 12 1
1 1 1 1 1
1 1 1 1 1
0 1 1 1 1
1 1 1 1 1
0 1 0 1 1
1 5 4 3 4 1
Sample Output

20
Explanation

Move the block in (1, 4) to (1, 5);
Move the block in (1, 3) to (1, 4);
Move the block in (1, 2) to (1, 3);
Move the block in (2, 2) to (1, 2);
Move the block in (3, 2) to (2, 2);
Move the block in (4, 2) to (3, 2);
Move the block in (4, 3) to (4, 2);
Move the block in (4, 1) to (4, 3) by cheating;
Move the block in (4, 2) to (4, 1).

So, 1 + 1 + 1 + 1 + 1 + 1 + 1 + 12 + 1 = 20.
 */

import java.util.*;

public class Huarongdao {
    static int n = 0;
    static int m = 0;
    static int k = 0;
    static int q = 0;
    static int[][] h = null;
    static int[][] dp = null;
    static int x1 = 0;
    static int y1 = 0;
    static int x2 = 0;
    static int y2 = 0;
    static int x3 = 0;
    static int y3 = 0;
    static int min = 0;
    static int[][] path = null;
    static int[][] pre = null;
    static int pathL = 0;
    static int pathR = 0;

    static int dpspace[][] = null;
    static int pathSpace[][] = null;
    static int pathSpaceL = 0;
    static int pathSpaceR = 0;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        q = in.nextInt();

        h = new int[n+2][m+2];
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= m; j++)
                h[i][j] = in.nextInt();

        dp = new int[n+2][m+2];
        dpspace = new int[n+2][m+2];
        pathSpace = new int[40003][2];
        path = new int[40003][2];
        pre = new int[40003][2];

        while (q-- > 0) {
            x1 = in.nextInt();
            y1 = in.nextInt();
            x2 = in.nextInt();
            y2 = in.nextInt();
            x3 = in.nextInt();
            y3 = in.nextInt();

            for (int[] a : dp) {
                Arrays.fill(a, Integer.MAX_VALUE);
            }
            dp[x2][y2] = 0;
            min = Integer.MAX_VALUE;

            pathL = 0;
            pathR = 1;
            path[0][0] = x2;
            path[0][1] = y2;
            pre[0][0] = x1;
            pre[0][1] = y1;

            while(pathL < pathR) {
                bfsCao(path[pathL], pre[pathL]);
                pathL++;
            }

            if (dp[x3][y3] == Integer.MAX_VALUE)
                System.out.println(-1);
            else
                System.out.println(dp[x3][y3]);
        }
    }

    static void bfsCao(int[] from, int[] space) {
        int fromX = from[0], fromY = from[1];
        int spaceX = space[0], spaceY = space[1];
        if (fromX == x3 && fromY == y3) {
            return;
        }

        if (h[fromX-1][fromY] != 0 && dp[fromX][fromY] < dp[fromX-1][fromY]) {
            int steps = dp[fromX][fromY] + bfsSpace(spaceX, spaceY, fromX-1, fromY, fromX, fromY) + 1;
            if (dp[fromX-1][fromY] > steps) {
                dp[fromX-1][fromY] = steps;
                path[pathR][0] = fromX -1; path[pathR][1] = fromY;
                pre[pathR][0] = fromX; pre[pathR][1] = fromY;
                pathR++;
            }
        }
        if (h[fromX][fromY-1] != 0 && dp[fromX][fromY] < dp[fromX][fromY-1]) {
            int steps = dp[fromX][fromY] + bfsSpace(spaceX, spaceY, fromX, fromY-1, fromX, fromY) + 1;
            if (dp[fromX][fromY-1] > steps) {
                dp[fromX][fromY-1] = steps;
                path[pathR][0] = fromX; path[pathR][1] = fromY-1;
                pre[pathR][0] = fromX; pre[pathR][1] = fromY;
                pathR++;
            }
        }
        if (h[fromX][fromY+1] != 0 && dp[fromX][fromY] < dp[fromX][fromY+1]) {
            int steps = dp[fromX][fromY] + bfsSpace(spaceX, spaceY, fromX, fromY+1, fromX, fromY) + 1;
            if (dp[fromX][fromY+1] > steps) {
                dp[fromX][fromY+1] = steps;
                path[pathR][0] = fromX; path[pathR][1] = fromY+1;
                pre[pathR][0] = fromX; pre[pathR][1] = fromY;
                pathR++;
            }
        }
        if (h[fromX+1][fromY] != 0 && dp[fromX][fromY] < dp[fromX+1][fromY]) {
            int steps = dp[fromX][fromY] + bfsSpace(spaceX, spaceY, fromX+1, fromY, fromX, fromY) + 1;
            if (dp[fromX+1][fromY] > steps) {
                dp[fromX+1][fromY] = steps;
                path[pathR][0] = fromX +1; path[pathR][1] = fromY;
                pre[pathR][0] = fromX; pre[pathR][1] = fromY;
                pathR++;
            }
        }
    }

    static int bfsSpace(int fromX, int fromY, int toX, int toY, int caoX, int caoY) {
        for(int[] a : dpspace)
            Arrays.fill(a, k);

        dpspace[fromX][fromY] = 0;
        pathSpaceL = 0;
        pathSpaceR = 1;
        pathSpace[0][0] = fromX;
        pathSpace[0][1] = fromY;

        while(pathSpaceL < pathSpaceR) {
            int myX = pathSpace[pathSpaceL][0], myY = pathSpace[pathSpaceL][1];
            if (myX == toX && myY == toY)
                break;
            if (dpspace[myX][myY] >= k) {
                pathSpaceL++;
                continue;
            }

            if (h[myX-1][myY] != 0 && ((myX-1) != caoX || myY != caoY) && dpspace[myX][myY] < dpspace[myX-1][myY]) {
                int steps = dpspace[myX][myY] + 1;
                if (dpspace[myX-1][myY] > steps) {
                    dpspace[myX-1][myY] = steps;
                    pathSpace[pathSpaceR][0] = myX-1; pathSpace[pathSpaceR][1] = myY;
                    pathSpaceR++;
                }
            }
            if (h[myX+1][myY] != 0 && ((myX+1) != caoX || myY != caoY) && dpspace[myX][myY] < dpspace[myX+1][myY]) {
                int steps = dpspace[myX][myY] + 1;
                if (dpspace[myX+1][myY] > steps) {
                    dpspace[myX+1][myY] = steps;
                    pathSpace[pathSpaceR][0] = myX+1; pathSpace[pathSpaceR][1] = myY;
                    pathSpaceR++;
                }
            }
            if (h[myX][myY-1] != 0 && (myX != caoX || (myY-1) != caoY) && dpspace[myX][myY] < dpspace[myX][myY-1]) {
                int steps = dpspace[myX][myY] + 1;
                if (dpspace[myX][myY-1] > steps) {
                    dpspace[myX][myY-1] = steps;
                    pathSpace[pathSpaceR][0] = myX; pathSpace[pathSpaceR][1] = myY-1;
                    pathSpaceR++;
                }
            }
            if (h[myX][myY+1] != 0 && (myX != caoX || (myY+1) != caoY) && dpspace[myX][myY] < dpspace[myX][myY+1]) {
                int steps = dpspace[myX][myY] + 1;
                if (dpspace[myX][myY+1] > steps) {
                    dpspace[myX][myY+1] = steps;
                    pathSpace[pathSpaceR][0] = myX; pathSpace[pathSpaceR][1] = myY+1;
                    pathSpaceR++;
                }
            }

            pathSpaceL++;
        }

        return Math.min(k, dpspace[toX][toY]);
    }
}

/*
Test case:

30 30 14 10
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0
1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 1 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 1 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 1 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 1 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1
23 21 6 25 11 27
22 15 14 15 11 5
8 25 10 9 8 13
12 21 9 7 13 3
5 17 6 13 12 13
17 5 28 21 13 25
25 3 8 27 29 21
14 21 1 11 10 3
10 9 30 14 29 29
30 21 27 3 30 5

output:
675
2010
690
930
90
645
1245
345
2834
795
 */

/*
Test case:

input:
10 20 10 5
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 1 0
1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1
1 0 1 1 1 0 1 0 1 0 1 0 1 0 1 0 1 1 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 1 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
1 0 1 0 1 0 1 0 1 1 1 0 1 0 1 0 1 0 1 0
1 1 1 1 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 0
5 9 10 13 2 19
10 18 9 5 1 14
1 20 3 16 4 19
1 4 10 11 7 1
1 1 10 18 3 17

output:
176
273
41
275
88

 */