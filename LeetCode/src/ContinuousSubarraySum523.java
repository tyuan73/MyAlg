/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 4/4/17
 * Time: 8:48 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class ContinuousSubarraySum523 {
    /**
     * First try. Works, but pretty messy.
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        if (k < 0) k = -k;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, zeros = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (++zeros == 2)
                    return true;
            } else {
                zeros = 0;
            }
            if (k == 0) continue;
            sum += nums[i];
            Integer idx = map.get(sum % k);
            if (idx != null) {
                if (i - idx >= 2) return true;
            } else
                map.put(sum % k, i);
        }
        return false;
    }

    /**
     * A much better solution
     */
    public boolean checkSubarraySum1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) sum %= k;
            Integer pre = map.get(sum);
            if (pre != null) {
                if (i - pre >= 2) return true;
            } else
                map.put(sum, i);
        }
        return false;
    }
}
