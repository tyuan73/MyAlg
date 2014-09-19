/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        if (n + m != s3.length())
            return false;

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                    dp[i][j] = dp[i - 1][j];
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1))
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
            }
        }
        return dp[n][m];
    }


    /**
     * the same solution with one dimension array
     */
    public boolean isInterleave1(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 + n2 != s3.length())
            return false;

        boolean[] dp = new boolean[n1 + 1];
        dp[0] = true;
        int index = 1;
        while (index <= n1 && s1.charAt(index - 1) == s3.charAt(index - 1)) {
            dp[index++] = true;
        }

        for (int i = 1; i <= n2; i++) {
            dp[0] = dp[0] && s2.charAt(i - 1) == s3.charAt(i - 1);
            for (int j = 1; j <= n1; j++) {
                dp[j] = (dp[j] && s2.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s1.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[dp.length - 1];
    }


    // test
    public static void main(String[] args) {

        InterleavingString is = new InterleavingString();
        String s1 = "cd";
        String s2 = "ab";
        String s3 = "abcd";

        System.out.println(is.isInterleave(s1, s2, s3));
        System.out.println(is.isInterleave1(s1, s2, s3));
    }
}
