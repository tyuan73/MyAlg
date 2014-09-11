/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 9:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class PalindromePartitioningII {
    public int minCut(String s) {
        int n = s.length();
        // number of cuts for the first k characters
        int[] dp = new int[n + 1];
        // init dp so that dp[0] = -1, dp[1] = 0, dp[2] = 1 ....
        for (int i = 0; i <= n; i++)
            dp[i] = i - 1;
        for (int i = 1; i <= n; i++) {
            // go through all palindromes with odd length that center this char
            for (int j = 0; i - j >= 0 && i + j < n && s.charAt(i - j) == s.charAt(i + j); j++)
                dp[i + j + 1] = Math.min(dp[i + j + 1], dp[i - j] + 1);
            // even length palindrome
            for (int j = 1; i - j >= 0 && i + j - 1 < n && s.charAt(i - j) == s.charAt(i + j - 1); j++)
                dp[i + j] = Math.min(dp[i + j], dp[i - j] + 1);
        }
        return dp[n];
    }

    /* this solution didn't pass big test */
    int minPartitionCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = -1;
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] + 1;
            for (int j = i + 1; j < n; j++) {
                if (isPalindrome(s, i, j)) {
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }

        return dp[0];
    }

    boolean isPalindrome(String s, int start, int end) {
        for (; start < end; start++, end--) {
            if (s.charAt(start) != s.charAt(end))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioningII pp2 = new PalindromePartitioningII();
        int ret = pp2.minPartitionCut("aabaaa");
        System.out.println(ret);
    }
}
