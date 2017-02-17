/**
 * Created by yuantian on 2/17/17.
 */
public class OJ456_132Pattern {
    /**
     * The most popular solution uses stack. This is a solution with binary search which is
     * a lot faster that beats 99% in java.
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] low = new int[n + 1];
        low[0] = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        int r = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < min)
                return true;
            if (nums[i] <= low[r])
                low[++r] = nums[i];
            else {
                int l = 0;
                while (l < r) {
                    int mid = (l + r) / 2;
                    if (low[mid] >= nums[i])
                        l = mid + 1;
                    else
                        r = mid;
                }
                min = low[r];
                low[r] = nums[i];
            }
        }
        return false;
    }
}
