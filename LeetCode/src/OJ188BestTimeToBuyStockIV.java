/**
 * Created by yuantian on 2/11/16.
 */
public class OJ188BestTimeToBuyStockIV {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;

        // this is very important! Otherwise, you will get TLE
        if (k >= len / 2) {
            int ans = 0;
            for (int i = 1; i < prices.length; i++)
                ans += Math.max(0, prices[i] - prices[i - 1]);
            return ans;
        }

        // See comments in OJ123BestTimeToBuyStockIII.java
        int[] choose = new int[k + 1];
        int[] dp = new int[k + 1];

        for (int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = k; j > 0; j--) {
                choose[j] = Math.max(choose[j], dp[j - 1]) + diff;
                dp[j] = Math.max(dp[j], choose[j]);
            }
        }
        int max = 0;
        for (int x : dp)
            max = Math.max(max, x);
        return max;
    }
}
