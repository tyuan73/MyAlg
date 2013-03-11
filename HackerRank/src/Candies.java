/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/6/13
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class Candies {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] rate = new int[n];
        for(int i = 0; i < n; i++)
            rate[i] = in.nextInt();

        int[] dp = new int[n];
        for(int i = 1; i < n; i++) {
            if(rate[i] > rate[i-1])
                dp[i] = dp[i-1] + 1;
        }

        int total = dp[n-1] + n;
        for(int i = n-2; i >= 0; i--) {
            if(rate[i] > rate[i+1])
                dp[i] = Math.max(dp[i],dp[i+1] + 1);

            total += dp[i];
        }

        System.out.println(total);
    }
}
