public class BestTimeStockI {
    public int maxProfit(int[] prices) {
        if (prices == null | prices.length == 0)
            return 0;

        int low = prices[0];
        int ret = 0;
        for (int i : prices) {
            low = Math.min(low, i);
            ret = Math.max(ret, i - low);
        }
        return ret;
    }
}