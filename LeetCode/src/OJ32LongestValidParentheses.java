/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/7/13
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class OJ32LongestValidParentheses {
    /**
     * The most popular solution is to use a stack.
     * But here I'm trying to solve it with DP.
     */

    /*
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length() + 1];
        int max = 0;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                int pre = i + dp[i + 1] + 1;
                if (pre < s.length() && s.charAt(pre) == ')') {
                    dp[i] = dp[i + 1] + dp[pre + 1] + 2;
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }
    */

    /*
    One pass, O(n) runtime, O(n) space
     */
    public int longestValidParentheses(String s) {
        s = s + "(";
        int[] dp = new int[s.length()];
        int max = 0;
        for(int i = s.length()-2; i >= 0; i--) {
            if (s.charAt(i) == ')') continue;
            int pre = i + dp[i + 1] + 1;
            if (s.charAt(pre) == ')') {
                dp[i] = dp[i+1] + dp[pre+1] + 2;
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /*
    Two pass, O(n) runtime, O(1) space.
     */
    public int longestValidParentheses1(String s) {
        // forward
        int max = 0;
        int count = 0, len = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
                len++;
            } else {
                if (count > 0) {
                    count--;
                    len++;
                    if (count == 0) {
                        max = Math.max(max, len);
                    }
                } else {
                    len = 0;
                }
            }
        }
        count = 0;
        len = 0;
        // backward
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                count++;
                len++;
            } else {
                if (count > 0) {
                    count--;
                    len++;
                    if (count == 0) {
                        max = Math.max(max, len);
                    }
                } else {
                    len = 0;
                }
            }
        }
        return max;
    }
}
