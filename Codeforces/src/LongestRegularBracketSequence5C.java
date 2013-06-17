/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/27/13
 * Time: 11:41 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class LongestRegularBracketSequence5C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String b = in.next();
        int n = b.length();
        int[] dp = new int[n + 1];

        for (int i = n - 2; i >= 0; i--) {
            if (b.charAt(i) == '(') {
                int next = i + dp[i + 1] + 1;
                if (next < n && b.charAt(next) == ')') {
                    dp[i] = dp[i + 1] + 2 + dp[next + 1];
                }
            }
        }

        int max = 0, count = 1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > max) {
                max = dp[i];
                count = 1;
            } else if (dp[i] == max && max != 0) {
                count++;
            }
        }

        System.out.println(max + " " + count);
    }
}
