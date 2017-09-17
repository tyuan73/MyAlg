/*

*/

import java.util.*;
import java.io.*;

public class ValidParenthesisString678 {
    /**
     * Recursive + memorized table.
     */
    public boolean checkValidString(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        return check(0, 0, dp, s);
    }

    boolean check(int left, int idx, int[][] dp, String s) {
        if (idx == s.length()) return left == 0;

        if (dp[left][idx] == 1) return true;
        else if (dp[left][idx] == 2) return false;

        boolean ret = false;
        if (s.charAt(idx) == '(') {
            if (check(left + 1, idx + 1, dp, s)) ret = true;
        } else if (s.charAt(idx) == ')') {
            if (left > 0 && check(left - 1, idx + 1, dp, s)) ret = true;
        } else {
            if ((left > 0 && check(left - 1, idx + 1, dp, s)) || (check(left + 1, idx + 1, dp, s)) || (check(left, idx + 1, dp, s)))
                ret = true;
        }
        dp[left][idx] = ret ? 1 : 2;
        return ret;
    }

    /**
     * DP solution
     */
    public boolean checkValidStringDP(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 2][n + 1];
        dp[0][n] = true;
        for (int col = n - 1, row = 1; col >= 0; col--, row++) {
            for (int i = 0; i <= row; i++) {
                if (s.charAt(col) == '(') {
                    dp[i][col] = dp[i + 1][col + 1];
                } else if (s.charAt(col) == ')') {
                    if (i > 0) dp[i][col] = dp[i - 1][col + 1];
                } else {
                    dp[i][col] = dp[i + 1][col + 1] || dp[i][col + 1];
                    if (i > 0) dp[i][col] = dp[i][col] || dp[i - 1][col + 1];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * DP solution - improved: remove "if (i > 0)" check
     */
    public boolean checkValidStringDP2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 3][n + 1];
        dp[1][n] = true;
        for (int col = n - 1, row = 2; col >= 0; col--, row++) {
            for (int i = 1; i <= row; i++) {
                if (s.charAt(col) == '(') {
                    dp[i][col] = dp[i + 1][col + 1];
                } else if (s.charAt(col) == ')') {
                    dp[i][col] = dp[i - 1][col + 1];
                } else {
                    dp[i][col] = dp[i + 1][col + 1] || dp[i][col + 1] || dp[i - 1][col + 1];
                }
            }
        }
        return dp[1][0];
    }

    /**
     * DP solution - improved: row (upper) can be calculated instead of row++
     */
    public boolean checkValidString3(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n + 3][n + 1];
        dp[1][n] = true;
        for (int col = n - 1; col >= 0; col--) {
            int row = Math.min(col, n - col) + 1;
            for (int i = 1; i <= row; i++) {
                if (s.charAt(col) == '(') {
                    dp[i][col] = dp[i + 1][col + 1];
                } else if (s.charAt(col) == ')') {
                    dp[i][col] = dp[i - 1][col + 1];
                } else {
                    dp[i][col] = dp[i + 1][col + 1] || dp[i][col + 1] || dp[i - 1][col + 1];
                }
            }
        }
        return dp[1][0];
    }

    /**
     * O(n) solution - backward
     */
    public boolean checkValidString2(String s) {
        int n = s.length();
        int upper = 0, lower = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '*') {
                upper++;
                lower--;
            } else if (s.charAt(i) == '(') {
                lower--;
                upper--;
            } else {
                lower++;
                upper++;
            }
            if (upper < 0 || lower > i) return false;
            if (lower < 0) lower = 0;
        }
        return lower == 0;
    }

    /**
     * O(n) solution - forward
     */
    public boolean checkValidString4(String s) {
        int n = s.length();
        int upper = 0, lower = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                upper++;
                lower--;
            } else if (s.charAt(i) == '(') {
                lower++;
                upper++;
            } else {
                lower--;
                upper--;
            }
            if (upper < 0 || lower > (n - i)) return false;
            if (lower < 0) lower = 0;
        }
        return lower == 0;
    }
}