/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/1/17
 * Time: 10:15 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class MaximumSizeSubarraySumEqualsk325 {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k)
                max = i + 1;
            else if (map.containsKey(sum - k))
                max = Math.max(max, i - map.get(sum - k));

            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }
}
