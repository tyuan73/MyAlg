/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class DistinctSubsequence {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        DistinctSubsequence ds = new DistinctSubsequence();
        ds.numDistinct1("bbaaaaaaa", "aaaaa");
    }

    /**
     * One dimension DP.
     */
    public int numDistinct1(String S, String T) {
        int n = T.length();
        int m = S.length();
        int[] dp = new int[n+1];
        dp[0] = 1;

        for(int i = 1; i <= m; i++) {
            for(int j = Math.min(n, i); j >= 1; j--) {
                if(T.charAt(j-1) == S.charAt(i-1))
                    dp[j] += dp[j-1];
            }
        }

        return dp[n];
    }

    /**
     * Two Dimensions DP.
     */
    public int numDistinct(String S, String T) {
        int n = T.length();
        int m = S.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 0; i <= m; i++)
            dp[0][i] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = i; j <= m; j++) {
                if(T.charAt(i-1) == S.charAt(j-1))
                    dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
                else
                    dp[i][j] = dp[i][j-1];
            }
        }

        return dp[n][m];
    }
}
