
import java.util.*;

public class OJ123BestTimeToBuyStockIII {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int n = prices.length;
        int[] dp = new int[n];

        int low = prices[0];
        for (int i = 1; i < n; i++) {
            low = Math.min(low, prices[i]);
            dp[i] = Math.max(dp[i - 1], prices[i] - low);
        }

        int ret = dp[n - 1];
        int pre = 0;
        int high = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            high = Math.max(high, prices[i]);
            pre = Math.max(pre, high - prices[i]);
            ret = Math.max(ret, dp[i] + pre);
        }

        return ret;
    }

    /**
     * A better and more generic solution which can solve the same problems with at most "M" transactions.
     * It runs in O(NM) time.
     * <p/>
     * The description is below.
     */
    public int maxProfit2(int[] prices) {
        int[] f = new int[3]; // 3 = m + 1 where m = 2;
        int[] g = new int[3]; // 3 = m + 1 where m = 2;

        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            int m = Math.min(i, 2);
            for (int j = m; j > 0; j--) {
                f[j] = Math.max(f[j], g[j - 1]) + diff;
                g[j] = Math.max(f[j], g[j]);
            }
        }

        return Math.max(g[1], g[2]);
    }

    /**
     * A different way to implement maxProfit2() above.
     */
    public int maxProfit3(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0;
        int buy2 = Integer.MIN_VALUE, sell2 = 0;
        for (int price : prices) {
            sell2 = Math.max(sell2, buy2 + price);  // check the second sell first!!
                                                    // Check the second buy + current price (buy2 has reduced the price to buy),
                                                    // so the whole price is all profit.
            buy2 = Math.max(buy2, sell1 - price);   // calculate the best after second buy based on the best first buy.
                                                    // remove the amount of price so that later on, we don't need to remember
                                                    // at what price we bought the stock.
            sell1 = Math.max(sell1, buy1 + price);  // the same as sell2
            buy1 = Math.max(buy1, -price);          // the same as buy2 - "0-price" means we start with 0 dollar.
        }
        return sell2;
    }

    /**
     * To generalize maxProfit3(). m below can be any number.
     * (This is the same idea as maxProfit2() but more neat in coding.)
     */
    public int maxProfit4(int[] prices) {
        int m = 2;
        int[] buy = new int[m + 1];
        int[] sell = new int[m + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int price : prices) {
            for (int i = m; i > 0; i--) {
                sell[i] = Math.max(sell[i], buy[i] + price);
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
            }
        }
        return sell[m];
    }
}

/**
 * ***********************************************************************
 * http://discuss.leetcode.com/questions/287/best-time-to-buy-and-sell-stock-iii
 * ***********************************************************************
 * <p/>
 * <p/>
 * A new problem of completing at most m transactions can be efficiently solved by using the method below.
 * <p/>
 * The profit of one transaction is price[i]-price[j] where i > j. We then can rewrite the expression to be: price[i]-price[i-1] + price[i-1]-price[i-2] + ... + price[j+1]-price[j]. If we construct an array of {diff[i]} = {price[i+1]-price[i]}, then the problem can be reduced to the maximum m segments sum problem on diff[], where m = 2 in this case. Then we can play the fancy dynamic programming to solve it.
 * <p/>
 * Here is one solution of the maximum m segments sum problem. The running time is O(NM).
 * <p/>
 * Let f[i][j] to be the maximum sum of j segments from the first i numbers, where the last element we choose is a[i]. We have two strategies to achieve it:
 * <p/>
 * Choosing the optimal j-1 segments from the first k numbers, and starting a new segment with a[i]:
 * <p/>
 * f[i][j] = f[k][j-1] + a[i], where j-1 <= k <= i-1.
 * <p/>
 * However, f[k][j-1] is the subproblems that we've already solved. If we memorize the optimal j-1 segments, namely g[j-1] = max(f[k][j-1]), the state transition can be achieved in O(1):
 * <p/>
 * f[i][j] = g[j-1] + a[i]
 * <p/>
 * Appending a[i] to the last segment in the first i-1 numbers
 * <p/>
 * f[i][j] = f[i-1][j] + a[i].
 * <p/>
 * Here is why we must choose a[i] in our strategies. If f[i-1][j] is not ends at a[i-1], then appending a[i] to f[i-1][j] will get j+1 segments, which violates the definition of f[i][j].
 * <p/>
 * class Solution {
 * public:
 * int maxProfit(vector<int> &prices) {
 * int f[3] = {0};
 * int g[3] = {0};
 * <p/>
 * int n = prices.size() - 1;
 * for (int i = 0; i < n; ++i) {
 * int diff = prices[i+1] - prices[i];
 * int m = min(i+1, 2);
 * for (int j = m; j >= 1; --j) {
 * f[j] = max(f[j], g[j-1]) + diff;
 * g[j] = max(g[j], f[j]);
 * }
 * }
 * return max(g[1], g[2]);
 * }
 * };
 */