/*

*/

import java.util.*;
import java.io.*;

public class MaximumSumof3NonOverlappingSubarrays689 {
    /**
     * First version.
     */
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

    /**
     * Improved a little bit.
     */
    public int[] maxSumOfThreeSubarrays1(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n];
        int total = 0;
        for (int i = n - 1; i >= 0; i--) {
            total += nums[i];
            if (i > n - k) continue;
            sum[i] = total;
            total -= nums[i + k - 1];
        }
        int[] left = new int[n];
        for (int i = k; i < n; i++) {
            if (sum[i - k + 1] > sum[left[i - 1]])
                left[i] = i - k + 1;
            else
                left[i] = left[i - 1];
        }
        int[] right = new int[n];
        right[n - k] = n - k;
        for (int i = n - k - 1; i >= 0; i--) {
            if (sum[i] >= sum[right[i + 1]])
                right[i] = i;
            else
                right[i] = right[i + 1];
        }
        int max = 0;
        int[] ans = null;
        for (int l = k, r = l + k - 1; r < n - k; l++, r++) {
            int cur = sum[left[l - 1]] + sum[l] + sum[right[r + 1]];
            if (cur > max) {
                max = cur;
                ans = new int[]{left[l - 1], l, right[r + 1]};
            }
        }
        return ans;
    }

    /**
     * Improved more.
     */
    public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        int n = nums.length;
        int[] ksum = new int[n];
        int[] right = new int[n + 1];
        //right[n-k] = n-k;
        for (int i = n - 1, total = 0; i >= 0; i--) {
            total += nums[i];
            if (i > n - k) continue;
            ksum[i] = total;
            total -= nums[i + k - 1];
            right[i] = ksum[i] >= ksum[right[i + 1]] ? i : right[i + 1];
        }

        int max = 0, first = 0;
        int[] ans = null;
        for (int second = k, third = second + k; third <= n - k; second++, third++) {
            first = ksum[second - k] > ksum[first] ? second - k : first;
            int cur = ksum[first] + ksum[second] + ksum[right[third]];
            if (cur > max) {
                max = cur;
                ans = new int[]{first, second, right[third]};
            }
        }
        return ans;
    }
}