/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/12/17
 * Time: 11:51 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class NextGreaterElementII_503 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            int idx = i >= n ? i - n : i;
            while (!stack.isEmpty() && nums[idx] > nums[stack.peek()]) {
                ans[stack.pop()] = nums[idx];
            }
            stack.push(idx);
        }
        return ans;
    }
}
