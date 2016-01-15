/**
 * Created by yuantian on 1/15/16.
 */

import java.util.*;

public class OJ322CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (c > i || dp[i - c] == -1) continue;
                dp[i] = dp[i] == -1 ? dp[i - c] + 1 : Math.min(dp[i], dp[i - c] + 1);
            }
        }
        return dp[amount];
    }
}
