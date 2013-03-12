/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 9:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class PalindromePartitioningII {
    int minPartitionCut(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        dp[n] = -1;
        for(int i = n-2; i >= 0; i--) {
            dp[i] = dp[i+1] + 1;
            for(int j = i+1; j < n; j++) {
                if(isPalindrome(s, i, j)) {
                    dp[i] = Math.min(dp[i], dp[j+1]+1);
                }
            }
        }

        return dp[0];
    }

    boolean isPalindrome(String s, int start, int end) {
        for(; start < end; start++, end--) {
            if(s.charAt(start) != s.charAt(end))
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
