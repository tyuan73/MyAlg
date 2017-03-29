/**
 * Created by yuantian on 3/29/17.
 */
public class FindPeakElement162 {
    /**
     * O(n)
     */
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) continue;
            if (i < nums.length - 1 && nums[i] <= nums[i + 1]) continue;
            return i;
        }
        return -1;
    }

    /**
     * binary search O(lg(n))
     */
    public int findPeakElement1(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < nums[mid + 1])
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}
