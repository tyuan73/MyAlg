/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/20/17
 * Time: 10:48 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class FindAllDuplicatesInArray442 {
    /**
     * The easiest way. Flip the target number to negative. If it is already negative, duplicate is found.
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int n : nums) {
            int idx = Math.abs(n) - 1;
            if (nums[idx] < 0) ans.add(idx + 1);
            else nums[idx] = -nums[idx];
        }
        return ans;
    }

    /**
     * Another way to do it. swap the numbers. A little bit messy.
     */
    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            int n = nums[i];
            if (n == i + 1) {
                i++;
                continue;
            }
            if (nums[n - 1] == n) {
                ans.add(n);
                nums[i] = -1;
                i++;
                continue;
            }
            if (nums[n - 1] == -1) {
                nums[n - 1] = n;
                nums[i] = -1;
                i++;
                continue;
            }
            nums[i] = nums[n - 1];
            nums[n - 1] = n;
        }
        return ans;
    }
}
