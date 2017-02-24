/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class DecodeWays {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    /**
     * DP version
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int n = s.length();
        s = s + "0";
        int[] dp = new int[n + 2];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch != '0') {
                dp[i] = dp[i + 1];
            }
            if (ch == '1' || (ch == '2' && s.charAt(i + 1) <= '6')) {
                dp[i] += dp[i + 2];
            }
        }

        return dp[0];
    }

    /**
     * DP version - another way
     */
    public int numDecodings1(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length();
        int[] dp = new int[n + 2];
        dp[n] = 1;
        int c = 0;
        for (int i = n - 1; i >= 0; i--) {
            int ch = s.charAt(i) - '0';
            if (ch == 0 && i > 0 && s.charAt(i - 1) != '1' && s.charAt(i - 1) != '2')
                return 0;
            if (ch != 0) {
                if (ch * 10 + c <= 26) {
                    dp[i] = dp[i + 1] + dp[i + 2];
                } else {
                    dp[i] = dp[i + 1];
                }
            }
            c = ch;
        }
        return dp[0];
    }

    /**
     * DP with constant space.
     *
     * @param s
     * @return
     */

    public int numDecodings3(String s) {
        int n = s.length();
        if (n == 0) return 0;
        s = s + "9";                    // add an 9 to the end does not change the answer.
        //int[] dp = new int[n + 2];    // dp with O(n) space
        //dp[n] = 1;
        int dp1 = 1, dp2 = 0;
        for (int i = n - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case '0':
                    dp2 = dp1;
                    dp1 = 0;
                    break;
                case '1':
                    //dp = dp1 + dp2;
                    //dp2 = dp1;
                    //dp1 = dp;
                    dp1 = dp2 + (dp2 = dp1);
                    //dp[i] = dp[i+1] + dp[i+2];  // the equavlent of dp with O(n) space
                    break;
                case '2':
                    if (s.charAt(i + 1) <= '6') {
                        //dp = dp1 + dp2;
                        //dp2 = dp1;
                        //dp1 = dp;
                        dp1 = dp2 + (dp2 = dp1);
                        //dp[i] = dp[i+1] + dp[i+2];  // the equalent of dp with O(n) space
                    } else {
                        //dp[i] = dp[i+1];
                        //dp = dp1;           // the equalent of dp with O(n) space
                        dp2 = dp1;
                    }
                    break;
                default:
                    //dp[i] = dp[i+1];     // the equalent of dp with O(n) space
                    //dp = dp1;
                    dp2 = dp1;
            }

        }
        return dp1;
    }

    /**
     * Recursive ways. not efficient. Will over time limit if s is considerable long.
     */
    public int numDecodingsRecursive(String s) {
        if (s == null || s.length() == 0)
            return 0;
        return decode(s.toCharArray(), 0);
    }

    int decode(char[] s, int index) {
        if (index == s.length)
            return 1;

        if (s[index] == '0')
            return 0;

        int ret = decode(s, index + 1);
        if (s[index] == '1' && index < s.length - 1)
            ret += decode(s, index + 2);
        else if (index < s.length - 1 && s[index] == '2' && s[index + 1] <= '6')
            ret += decode(s, index + 2);
        return ret;
    }
}
