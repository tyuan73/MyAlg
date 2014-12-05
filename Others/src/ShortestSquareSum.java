/**
 * Created by yuantian on 12/4/14.
 */

/**
 * Any number can be the sum of square numbers. For example: 1 = 1^2, 6 = 2^2 + 1^2 + 1^2
 * Find the shortest possible square decomposites of a number.
 * 8 = 1^2 + 1^2 + 1^2 + 1^2 + 2^2 (let's say the length is 5, that is four 1s and one 2)
 * it can also be:
 * 8 = 2^ + 2^2 (the length is 2, that is two 2s)
 */

import java.util.*;

public class ShortestSquareSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        /* to verify both solutions work */
        //for(int i = 1; i < n; i++) {
        //    if (solution1(i) != solution2(i)) {
        //        System.out.println(i + " not correct!");
        //    }
        //}

        //System.out.println("solotion1 returns: " + solution1(n));
        //System.out.println("solotion2 returns: " + solution2(n));

        solution2(n);
    }

    static private int solution1(int n) {
        long start = System.currentTimeMillis();
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int root = (int) Math.sqrt(i);
            if (i == root * root) {
                dp[i] = 1;
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int l = 1, r = i - 1; l <= r; l++, r--) {
                min = Math.min(min, dp[l] + dp[r]);
            }

            dp[i] = min;
        }

        //for (int i = 0; i <= n; i++)
        //    System.out.println(i + ": " + dp[i]);

        System.out.println("solution1 complete in: " + (System.currentTimeMillis() - start));
        return dp[n];
    }

    static private int solution2(int n) {
        long start = System.currentTimeMillis();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++)
            dp[i] = i;

        int root = (int) Math.sqrt(n);
        for (int i = 2; i <= root; i++) {
            dp[i * i] = 1;
            for (int j = i * i + 1; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }

        //for (int i = 0; i <= n; i++)
        //    System.out.println(i + ": " + dp[i]);

        int max = 0;
        for(int x : dp)
            max = Math.max(max, x);
        System.out.println("max = " + max);

        System.out.println("solution2 complete in: " + (System.currentTimeMillis() - start));
        return dp[n];
    }
}

/*
0: 0
1: 1
2: 2
3: 3
4: 1
5: 2
6: 3
7: 4
8: 2
9: 1
10: 2
11: 3
12: 3
13: 2
14: 3
15: 4
16: 1
17: 2
18: 2
19: 3
20: 2
 */