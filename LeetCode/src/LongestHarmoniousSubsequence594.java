/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/22/17
 * Time: 11:42 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class LongestHarmoniousSubsequence594 {
    /**
     * Use sorting, 42ms. beat 99%
     *
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int count1 = 0, count2 = 0, max = 0;
        long num1 = Long.MIN_VALUE, num2 = Long.MIN_VALUE;
        for (int x : nums) {
            if (x == num2) count2++;
            else {
                if (num2 == num1 + 1)
                    max = Math.max(max, count1 + count2);
                count1 = count2;
                num1 = num2;
                num2 = x;
                count2 = 1;
            }
        }
        if (num2 == num1 + 1)
            max = Math.max(max, count1 + count2);
        return max;
    }

    /**
     * Use HashMap, 71ms
     */
    public int findLHS1(int[] nums) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.containsKey(key - 1)) {
                max = Math.max(max, map.get(key) + map.get(key - 1));
            }
        }
        return max;
    }
}
