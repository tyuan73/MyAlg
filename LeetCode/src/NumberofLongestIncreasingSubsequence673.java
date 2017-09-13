/**
 * Created by miaomiao on 9/12/17.
 */

import java.util.*;

public class NumberofLongestIncreasingSubsequence673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] len = new int[n];
        int[] count = new int[n];
        Arrays.fill(len, 1);
        Arrays.fill(count, 1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] <= nums[j] || len[j] + 1 < len[i]) continue;
                if (len[j] + 1 > len[i]) {
                    len[i] = len[j] + 1;
                    count[i] = count[j];
                } else {
                    count[i] += count[j];
                }
            }
            max = Math.max(max, len[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (len[i] == max)
                ans += count[i];
        }
        return ans;
    }
}
