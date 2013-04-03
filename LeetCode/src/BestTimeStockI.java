public class BestTimeStockI {
    public int maxProfit(int[] prices) {
        //if (prices == null | prices.length == 0)
        //    return 0;

        int max = 0, low = Integer.MAX_VALUE;
        for (int i : prices) {
            low = Math.min(low, i);
            max = Math.max(max, i - low);
        }
        return max;
    }
}