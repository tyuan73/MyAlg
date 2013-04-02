public class BestTimeStockII {
    public int maxProfit(int[] prices) {
        //if (prices == null || prices.length == 0)
        //    return 0;

        int ret = 0;
        for (int i = 1; i < prices.length; i++) {
            ret += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ret;
    }
}