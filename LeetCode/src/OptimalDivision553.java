/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 4/16/17
 * Time: 10:17 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class OptimalDivision553 {
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        double[][] max = new double[n][n];
        double[][] min = new double[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(min[i], Integer.MAX_VALUE);
            max[i][i] = min[i][i] = (double) nums[i];
        }

        int[][][] track = new int[n][n][2];

        for (int i = 1; i < n; i++) {
            for (int row = 0, col = i; col < n; row++, col++) {
                for (int k = row; k < col; k++) {
                    if (max[row][k] / min[k + 1][col] > max[row][col]) {
                        max[row][col] = max[row][k] / min[k + 1][col];
                        track[row][col][0] = k;
                    }
                    if (min[row][k] / max[k + 1][col] < min[row][col]) {
                        min[row][col] = min[row][k] / max[k + 1][col];
                        track[row][col][1] = k;
                    }
                }
            }
        }

        return buildStr(nums, track, 0, n - 1, 0);

        //return Double.toString(max[0][n-1]);    // max[0][n-1] is the max value.
    }

    private String buildStr(int[] nums, int[][][] track, int from, int to, int flag) {
        if (from == to) return Integer.toString(nums[from]);
        int mid = track[from][to][flag];
        if (mid == to - 1)
            return buildStr(nums, track, from, mid, flag) + "/" + buildStr(nums, track, mid + 1, to, 1 - flag);
        return buildStr(nums, track, from, mid, flag) + "/(" + buildStr(nums, track, mid + 1, to, 1 - flag) + ")";
    }
}
