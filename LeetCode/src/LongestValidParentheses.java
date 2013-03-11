/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/7/13
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class LongestValidParentheses {
    /**
     * The most popular solution is to use a stack.
     * But here I'm trying to solve it with DP.
     */

    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()+1];
        int max = 0;
        for(int i = s.length()-2; i >= 0; i--) {
            if(s.charAt(i) == '(') {
                int pre = i + dp[i+1] + 1;
                if(pre < s.length() && s.charAt(pre) == ')') {
                    dp[i] = dp[i+1] + dp[pre+1] + 2;
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }
}
