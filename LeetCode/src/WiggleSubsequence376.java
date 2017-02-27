/**
 * Created by yuantian on 2/27/17.
 */
public class WiggleSubsequence376 {
    /**
     * Solution 1. found a up slope, plus 1, found a down slope, plus 1....
     */
    public int wiggleMaxLength(int[] nums) {
        int count = 1, n = nums.length;
        int idx = 0;
        while (idx < n - 1) {
            boolean found = false;
            while (idx + 1 < n && nums[idx + 1] >= nums[idx]) {
                if (nums[idx + 1] > nums[idx]) found = true;
                idx++;
            }
            if (found) count++;
            found = false;
            while (idx + 1 < n && nums[idx + 1] <= nums[idx]) {
                if (nums[idx + 1] < nums[idx]) found = true;
                idx++;
            }
            if (found) count++;
        }
        return Math.min(n, count);
    }

    /**
     * a better way to code. short and easy.
     */
    public int wiggleMaxLength1(int[] nums) {
        int inc = 1, dec = 1, n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1])
                inc = dec + 1;
            else if (nums[i] < nums[i - 1])
                dec = inc + 1;
        }
        return Math.min(n, Math.max(inc, dec));
    }
}
