/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/21/13
 * Time: 11:54 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Triangle {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n - 1] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            ArrayList<Integer> list = triangle.get(i);
            for (int j = n - 1 - i, k = 0; j < n; j++, k++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + list.get(k);
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i : dp)
            min = Math.min(i, min);
        return min;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
