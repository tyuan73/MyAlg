/**
 * Created by yuantian on 2/11/16.
 */

import java.util.*;

public class OJ188BestTimeToBuyStockIV {
    /*
    DP version. Runtime O(kn)
     */
    public int maxProfit1(int k, int[] prices) {
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

    public int maxProfit(int k, int[] prices) {
        List<Integer> profit = new ArrayList<>();

        int curMax = 0, cur = 0;
        for (int i = 1; i < prices.length; i++) {
            cur += prices[i] - prices[i - 1];
            curMax = Math.max(curMax, cur);
            if (cur <= 0) {
                if (curMax > 0) profit.add(curMax);
                curMax = 0;
                cur = 0;
            }
        }
        if (cur > 0)
            profit.add(curMax);

        System.out.println("profit size = " + profit.size());
        Collections.sort(profit, Collections.reverseOrder());
        k = Math.min(k, profit.size());
        int ans = 0;
        for (int i = 0; i < k; i++) {
            ans += profit.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int k = 2;
        int[] prices = {0,0,0,0,0,0,0,0};
        //int[] prices = {1, 2, 3, 4, 5, 0, 7, 8, 9};
        OJ188BestTimeToBuyStockIV test = new OJ188BestTimeToBuyStockIV();
        System.out.println(test.maxProfit(k, prices));
    }
}
