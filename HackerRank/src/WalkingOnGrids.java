/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/6/13
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class WalkingOnGrids {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        if(n == 1) {
            System.out.println(1);
            return;
        }
        int[][] dp = new int[n+1][n+1];
        dp[1][1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 10007;
            }
        }
        /*
        for(int[] x : dp)
        {
            for(int y : x)
                System.out.printf("%5d", y);
            System.out.println();
        }
        */
        System.out.println((dp[n][n] * 2) % 10007);
    }
}
