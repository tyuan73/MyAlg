/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 2/6/17
 * Time: 9:37 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class PredictTheWinner486 {
    public boolean PredictTheWinner(int[] nums) {
        return check(nums, 0, nums.length - 1, 0, 0, true);
    }

    boolean check(int[] nums, int l, int r, int t1, int t2, boolean first) {
        if (l > r) {
            if (t1 > t2) return true;
            else if (t1 < t2) return false;
            else {
                return first;
            }
        }
        if (!check(nums, l + 1, r, t2, t1 + nums[l], !first))
            return true;
        if (!check(nums, l, r - 1, t2, t1 + nums[r], !first))
            return true;
        return false;
    }

    /**
     * Improved recursive solution
     * https://discuss.leetcode.com/topic/76312/java-1-line-recursion-solution
     */
    public boolean PredictTheWinner1(int[] nums) {
        return check(nums, 0, nums.length - 1) >= 0;
    }

    int check(int[] nums, int l, int r) {
        if (l == r)
            return nums[l];
        return Math.max(nums[l] - check(nums, l + 1, r), nums[r] - check(nums, l, r - 1));
    }
}
