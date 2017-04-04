/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 4/3/17
 * Time: 10:49 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class ThreeSumSmaller259 {
    /**
     * Binary search O(n*n*lg(n))
     */
    public int threeSumSmaller(int[] nums, int target) {
        int ans = 0;
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int idx = bSearch(nums, j + 1, n - 1, target - nums[i] - nums[j]);
                if (idx == -1) break;
                ans += idx - j;
            }
        }
        return ans;
    }

    private int bSearch(int[] nums, int from, int to, int x) {
        if (x <= nums[from]) return -1;
        while (from < to) {
            int mid = (from + to + 1) / 2;
            if (nums[mid] >= x)
                to = mid - 1;
            else
                from = mid;
        }
        return from;
    }

    /**
     * Two pointers
     */
    public int threeSumSmaller1(int[] nums, int target) {
        int ans = 0;
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] < target) {
                    ans += k - j;
                    j++;
                } else {
                    k--;
                }
            }
        }
        return ans;
    }
}
