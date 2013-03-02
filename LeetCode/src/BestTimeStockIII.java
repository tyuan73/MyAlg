public class BestTimeStockIII {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0)
            return 0;
        
        int n = prices.length;
        int[] dp = new int[n];
        
        int low = prices[0];
        for(int i = 1; i < n; i++) {
            low = Math.min(low, prices[i]);
            dp[i] = Math.max(dp[i-1], prices[i] - low);
        }
        
        int ret = dp[n-1];
        int pre = 0;
        int high = prices[n-1];
        for(int i = n-2; i >= 0; i--) {
            high = Math.max(high, prices[i]);
            pre = Math.max(pre, high - prices[i]);
            ret = Math.max(ret, dp[i]+pre);
        }
        
        return ret;
    }
}