/**
 * Created by yuantian on 4/28/14.
 */
/*
There is a stack consisting of N bricks. You and your friend decide to play a game using this stack. In this game, one can alternatively remove 1/2/3 bricks from the top and the numbers on the bricks removed by the player is added to his score. You have to play in such a way that you obtain maximum possible score while it is given that your friend will also play optimally and you will get a chance to make first move.

Input Format
First line will contain an integer T i.e. number of test cases. There will be two lines corresponding to each test case, first line will contain and number N i.e. number of element in stack and next line will contain N numbers i.e. numbers written on bricks from top to bottom.

Output Format
A single line containing maximum value corresponding to each test case.

Constraints
1 ≤ T ≤ 5
1 ≤ N ≤ 105
0 ≤ each number on brick ≤ 109

Sample Input

2
5
999 1 1 1 0
5
0 1 1 1 999
Sample Output

1001
999
Explanation

In first test case you will pick 999,1,1. If you will play in any other way, you will not get a score of 1001.
 */

import java.util.*;

public class PlayGameRecursive {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            long[] brick = new long[n];
            for(int i = 0; i < n; i++) {
                brick[i] = in.nextLong();
            }

            System.out.println(max(brick, 0));
        }
    }

    static private long max(long[] b, int s) {
        if (s >= b.length - 3) {
            long ret = 0;
            for (int i = s; i < b.length; i++)
                ret += b[i];
            return ret;
        }

        long ret = Math.max(b[s] + min(b, s + 1), b[s] + b[s+1] + min(b, s + 2));
        ret = Math.max(ret, b[s] + b[s+1] + b[s+2] + min(b, s + 3));
        return ret;
    }

    static private long min(long[] b, int s) {
        if (s >= b.length - 3)
            return 0;

        long ret = Math.min(max(b, s+1), max(b, s+2));
        ret = Math.min(ret, max(b, s+3));
        return ret;
    }
}
