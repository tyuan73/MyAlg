/*

*/

import java.util.*;
import java.io.*;

public class BestTimetoBuyandSellStockwithTransactionFee714 {
    /**
     * DP.
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int noStock = 0, hasStock = -500000;
        for (int i = 0; i < n; i++) {
            int a = Math.max(noStock, hasStock + prices[i] - fee);
            int b = Math.max(hasStock, noStock - prices[i]);
            noStock = a;
            hasStock = b;
        }
        return noStock;
    }

    /**
     * A little improved.
     */
    public int maxProfit1(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
}