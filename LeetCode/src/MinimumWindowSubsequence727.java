/*

*/

import java.util.*;
import java.io.*;

public class MinimumWindowSubsequence727 {
    public String minWindow(String S, String T) {
        int n = S.length(), m = T.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= n; i++)
            if (S.charAt(i - 1) == T.charAt(0))
                dp[1][i] = i;
            else
                dp[1][i] = dp[1][i - 1];
        for (int i = 2; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (T.charAt(i - 1) != S.charAt(j - 1))
                    dp[i][j] = dp[i][j - 1];
                else {
                    if (dp[i - 1][j - 1] != 0) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        int len = 10000000, first = 0;
        char last = T.charAt(m - 1);
        for (int i = 1; i <= n; i++) {
            if (S.charAt(i - 1) == last && dp[m][i] != 0 && i - dp[m][i] + 1 < len) {
                len = i - dp[m][i] + 1;
                first = dp[m][i] - 1;
            }
        }

        return len == 10000000 ? "" : S.substring(first, first + len);
    }

}