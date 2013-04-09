/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/22/13
 * Time: 10:34 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int total = 0;
            for (int l = 0; l < i; l++)
                total += dp[l] * dp[i - 1 - l];
            dp[i] = total;
        }
        return dp[n];
    }

    /**
     * Or, even better:
     * It's catalan number. Cn = (2n!)/((n+1)!n!), So we can get the recurrence relation between Cn and Cn-1
     * http://en.wikipedia.org/wiki/Catalan_number
     *
     * @param n
     * @return
     */
    public int numTrees1(int n) {
        int c = 1;
        for (int i = 2; i <= n; i++)
            c = 2 * (2 * i - 1) * c / (i + 1);
        return c;
    }
}
