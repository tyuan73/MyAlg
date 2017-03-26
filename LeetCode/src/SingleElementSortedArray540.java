/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/24/17
 * Time: 10:49 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class SingleElementSortedArray540 {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            //if (mid == 0 || mid == nums.length-1) return nums[mid];    // don't need
            //int pair = mid ^ 1;
            if (nums[mid ^ 1] == nums[mid])
                l = mid + 1;
            else
                r = mid - 1;
        }
        return nums[l];
    }
}
