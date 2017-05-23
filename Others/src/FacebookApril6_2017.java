/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 4/6/17
 * Time: 7:03 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class FacebookApril6_2017 {
    /**
     * First coding test, permutation of a string.
     */
    public void perm(char[] str) {
        int n = str.length;
        Arrays.sort(str);
        while (true) {
            System.out.println(str);
            int i = n - 1;
            while (i > 0 && str[i] <= str[i - 1])
                i--;
            if (i > 0) {
                int pre = i - 1;

                int j = n - 1;
                while (str[j] <= str[pre]) j--;

                swap(str, pre, j);
                for (int l = i, r = n - 1; l < r; l++, r--)
                    swap(str, l, r);

            } else {
                break;
            }
        }
    }

    /**
     * This is what I got in interview, see comments for the mistake I made.
     */
    private void perm(char[] str, int idx) {
        if (idx == str.length) {
            System.out.println(str);
            return;
        }

        for (int i = idx; i < str.length; i++) {
            swap(str, i, idx);
            perm(str, idx + 1);  // I made mistake by "perm(str, i + 1)"
            swap(str, i, idx);
        }
    }

    private void swap(char[] str, int i, int j) {
        char ch = str[i];
        str[i] = str[j];
        str[j] = ch;
    }

    /**
     * The second coding question. Give an integer array, find the
     * increasing continuous subarray that the diff is 1. For example: [1,2,3]
     * but [1,2,4] is not valid. Find the one with max length.
     */
    public int[] findSubarray(int[] nums) {
        int max = 1, idx = 0, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                count++;
                if (count > max) {
                    max = count;
                    idx = i - count + 1;
                }
            } else {
                count = 1;
            }
        }
        int[] ans = new int[max];
        for (int i = idx, j = 0; j < max; i++, j++)
            ans[j] = nums[i];

        return ans;
    }

    /**
     * Follow up on the second question. If you are allowed to change one element.
     * Get the subarray with max length.
     * <p>
     * I made a mistabke. I thought [2,3,6,8,9,10] is a valid array. But
     * [2,3,6,5,6,7] is the valid option since you can change the first 6 to 4 and the new
     * subarray is an increasing continuous subarray. Not just 2 subarray joint by any one
     * number in between. I didn't ask. I think I failed because of this.
     */

    public int[] findSubarray1(int[] nums) {
        int n = nums.length;
        if (n <= 2) return nums;
        int max = 1, idx = 0;
        int[] dp = new int[n + 2];
        int start = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                dp[start + 1] = i - start;
                dp[i] = i - start;
                start = i;
            }
        }
        if (start == 0) return nums;

        dp[start + 1] = n - start;
        for (int i = 1; i <= n; i++) {
            if (dp[i] != 1) continue;

            if (dp[i - 1] + 1 + dp[i + 1] > max) {
                max = dp[i - 1] + 1 + dp[i + 1];
                idx = i - dp[i - 1] - 1;
            }
        }

        /*
        if (nums[0] != nums[1] - 1) {
            if (index[1] + 1 > max) {
                max = index[1] + 1;
                idx = 0;
            }
        }

        if (nums[n - 1] != nums[n - 2] + 1) {
            if (n - index[n - 2] > max) {
                max = n - index[n - 2];
                idx = index[n - 2];
            }
        }
        */

        int[] ans = new int[max];
        for (int i = 0; i < max; i++, idx++)
            ans[i] = nums[idx];
        return ans;
    }

    public static void main(String[] args) {
        FacebookApril6_2017 test = new FacebookApril6_2017();
        //char[] data = {'a', 'b', 'c', 'd'};
        //char[] data = {'a'};
        //test.perm(data, 0);

        /*
        int[] data2 = {4, 2, 3, 6, 4, 5, 6, 9};
        int[] ret = test.findSubarray(data2);
        for (int x : ret) {
            System.out.print(x + " ");
        }
        System.out.println();
        */

        //int[] data3 = {4, 2, 3, 6, 5, 6, 7, 9};
        //int[] data3 = {4, 2, 3, 4, 5};
        int[][] data3 = {
                {4, 2, 3, 6, 5, 6, 7, 9},
                {4, 2, 3, 4, 5},
                {1, 2, 3, 4, 5, 8},
                {1, 2, 3, 0, 5, 6, 7, 2, 9, 10},
                {1, 2, 3},
                {3, 6},
                {1, 5, 4, 7},
                {1, 2, 3, 6, 8, 9, 10},
                {2}
        };
        for (int[] d : data3) {
            int[] ret = test.findSubarray1(d);
            for (int x : ret) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}
