/*

*/

import java.util.*;
import java.io.*;

public class MaximumSumof3NonOverlappingSubarrays689 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + nums[i - 1];

        int[] left = new int[n];
        for (int i = k; i < n; i++) {
            if (sum[i + 1] - sum[i - k + 1] > sum[left[i - 1] + k] - sum[left[i - 1]])
                left[i] = i - k + 1;
            else
                left[i] = left[i - 1];
        }

        int[] right = new int[n];
        right[n - k] = n - k;
        for (int i = n - k - 1; i >= 0; i--) {
            if (sum[i + k] - sum[i] >= sum[right[i + 1] + k] - sum[right[i + 1]])
                right[i] = i;
            else
                right[i] = right[i + 1];
        }

        int max = 0;
        int[] ans = null;
        for (int s = k, e = s + k - 1; e < n - k; s++, e++) {
            int local = sum[e + 1] - sum[s] + sum[left[s - 1] + k] - sum[left[s - 1]] + sum[right[e + 1] + k] - sum[right[e + 1]];
            if (local > max) {
                max = local;
                ans = new int[]{left[s - 1], s, right[e + 1]};
            }
        }
        return ans;
    }

}