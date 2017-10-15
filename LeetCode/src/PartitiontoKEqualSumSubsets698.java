/*

*/

import java.util.*;
import java.io.*;

public class PartitiontoKEqualSumSubsets698 {
    /**
     * INCORRECT solution: try to use backsack or dp.
     * <p>
     * It won't pass this test case:
     * [7,2,2,2,2,2,2,2,3] 3
     * Expect: false, but returns true.
     * <p>
     * <p>
     * <p>
     * As alcoholkid  said: (https://discuss.leetcode.com/topic/107127/java-backpack-solution/6)
     * This is still incorrect. Check the test case
     * [1,2,2,2,2]
     * 3
     * Until now, I don't believe this problem can be solved as a backpack problem.
     * In your dp, you want to find out # of possible subsets that sum to target.
     * However, these subsets considered in your codes can have overlapping. Thus,
     * even if # of possible subsets is larger than k, you can not necessarily conclude
     * the original array can be portioned into k disjoint subsets with a sum equal to target.
     */
    public boolean canPartitionKSubsets_WRONG(int[] nums, int k) {
        //int n = nums.length;
        int sum = 0;
        for (int v : nums) sum += v;
        if (sum % k != 0) {
            return false;
        }
        sum /= k;
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int x : nums) {
            if (x > sum) return false;
            for (int j = sum; j >= x; j--)
                dp[j] += dp[j - x];
        }
        return dp[sum] >= k;
    }

    /**
     * UWI's solution
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int v : nums) sum += v;
        if (sum % k != 0) {
            return false;
        }
        sum /= k;
        boolean[] ok = new boolean[1 << n];
        for (int i = 0; i < 1 << n; i++) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                if (i << ~j < 0) {
                    s += nums[j];
                }
            }
            ok[i] = s == sum;
        }

        boolean[] dp = new boolean[1 << n];
        dp[0] = true;

        for (int i = 0; i < 1 << n; i++) {
            if (!dp[i]) continue;
            for (int j = i; j < 1 << n; j = j + 1 | i) {
                if (ok[i ^ j]) dp[j] = true;
            }
        }
        return dp[(1 << n) - 1];
    }

    /**
     * KakaHiguain's solution.
     */
    List<Integer> list = new ArrayList<>();

    int getsum(int[] nums, int s) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++)
            if ((s & (1 << i)) > 0) ans += nums[i];
        return ans;
    }

    boolean dfs(int k, int s) {
        if (k == 0) return true;
        for (int x : list)
            if (((s & x) == x) && dfs(k - 1, s - x)) return true;
        return false;
    }

    public boolean canPartitionKSubsets1(int[] nums, int k) {
        int sum = 0, n = nums.length;
        for (int x : nums) sum += x;
        if (sum % k != 0) return false;
        sum /= k;
        int m = (1 << n);
        for (int i = 0; i <= m; i++)
            if (getsum(nums, i) == sum) list.add(i);
        return dfs(k, m - 1);
    }
}