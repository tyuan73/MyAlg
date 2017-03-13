/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/13/17
 * Time: 10:20 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class NextGreaterElementI_496 {
    /**
     * Most popular solution using hashmap and stack
     */
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int x : nums) {
            while (!stack.isEmpty() && x > stack.peek()) {
                map.put(stack.pop(), x);
            }
            stack.push(x);
        }

        int[] ans = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++)
            ans[i] = map.getOrDefault(findNums[i], -1);
        return ans;
    }

    /**
     * A faster solution using hashmap and array. loop backwards.
     */
    public int[] nextGreaterElement1(int[] findNums, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] nextIdx = new int[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int next = i + 1; next != 0; next = nextIdx[next]) {
                if (nums[i] < nums[next]) {
                    nextIdx[i] = next;
                    map.put(nums[i], nums[next]);
                    break;
                }
            }
        }
        int[] ans = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++)
            ans[i] = map.getOrDefault(findNums[i], -1);
        return ans;
    }
}
