/**
 * Created by yuantian on 2/17/17.
 */

import java.util.*;

public class DifferentWaystoAddParentheses241 {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> nums = new ArrayList<>();
        List<Character> op = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            switch (ch) {
                case '-':
                case '+':
                case '*':
                    nums.add(num);
                    num = 0;
                    op.add(ch);
                    break;
                default:
                    num = num * 10 + ch - '0';
            }
        }
        nums.add(num);

        int n = nums.size();
        List<Integer>[][] dp = new List[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                dp[i][j] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++)
            dp[i][i].add(nums.get(i));

        for (int to = 1; to < n; to++) {
            for (int from = to - 1; from >= 0; from--) {
                for (int mid = from; mid < to; mid++) {
                    for (int left : dp[from][mid]) {
                        for (int right : dp[mid + 1][to]) {
                            switch (op.get(mid)) {
                                case '-':
                                    dp[from][to].add(left - right);
                                    break;
                                case '+':
                                    dp[from][to].add(left + right);
                                    break;
                                case '*':
                                    dp[from][to].add(left * right);
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "34+45-4";
        String[] sout = s.split("\\[+-\\*\\]");
        for (String x : sout)
            System.out.println(x);
    }
}
