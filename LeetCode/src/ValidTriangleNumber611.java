/**
 * Created by yuantian on 6/12/17.
 */

import java.util.*;

public class ValidTriangleNumber611 {
    /**
     * Solution like 3-sum. The easy and best solution.
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int total = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    total += r - l;
                    r--;
                } else
                    l++;
            }
        }
        return total;
    }

    /**
     * A more complicated solution. Faster? Not sure.
     * <p>
     * Note: 1001 can be replaced by a max in all nums.
     */
    public int triangleNumber1(int[] nums) {
        int[] count = new int[1001];
        for (int x : nums)
            count[x]++;
        int[] sum = new int[1001];
        for (int i = 1; i < 1001; i++)
            sum[i] = sum[i - 1] + count[i];

        int total = 0, upper;
        for (int i = 1; i < 1001; i++) {
            if (count[i] == 0) continue;
            // choose i, i and a number larger than i
            upper = Math.min(1000, i + i - 1);
            total += count[i] * (count[i] - 1) / 2 * (sum[upper] - sum[i]);
            // choose i, i, and i
            total += count[i] * (count[i] - 1) * (count[i] - 2) / 6;

            for (int j = i + 1; j < 1001; j++) {
                if (count[j] == 0) continue;
                // choose i, j, and j
                total += count[j] * (count[j] - 1) / 2 * count[i];
                // choose i, j and a number larger than j
                upper = Math.min(1000, i + j - 1);
                total += count[i] * count[j] * (sum[upper] - sum[j]);
            }
        }
        return total;
    }
}
