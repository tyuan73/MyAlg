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

    /**
     * Backtracking solution 1 - LTE
     */
    public boolean canPartitionKSubsets_LTE(int[] nums, int k) {
        int n = nums.length;
        int total = 0;
        for (int x : nums) total += x;
        if (total % k != 0) return false;
        return partition(nums, 0, total / k, new boolean[n], k);
    }

    private boolean partition(int[] nums, int sum, int target, boolean[] used, int rem) {
        //if (sum > target) return false;
        if (rem == 0) return true;
        if (sum == target) return partition(nums, 0, target, used, rem - 1);
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || sum + nums[i] > target) continue;
            used[i] = true;
            if (partition(nums, sum + nums[i], target, used, rem))
                return true;
            used[i] = false;
        }
        return false;
    }

    /**
     * Backtracking solution 2 , better than previous one , but still LTE
     */
    public boolean canPartitionKSubsets_LTE1(int[] nums, int k) {
        int n = nums.length;
        int total = 0;
        for (int x : nums) total += x;
        if (total % k != 0) return false;
        return partition(nums, new int[k], 0, total / k);
    }

    private boolean partition(int[] nums, int[] sum, int idx, int target) {
        if (idx >= nums.length) {
            for (int s : sum)
                if (s != target) return false;
            return true;
        }
        for (int i = 0; i < sum.length; i++) {
            if (nums[idx] + sum[i] > target) continue;
            sum[i] += nums[idx];
            if (partition(nums, sum, idx + 1, target))
                return true;
            sum[i] -= nums[idx];
        }
        return false;
    }

    /**
     * ACed solution modified from KakaHiguain's
     */
    public boolean canPartitionKSubsets_ac(int[] nums, int k) {
        int n = nums.length;
        int total = 0;
        for (int x : nums) total += x;
        if (total % k != 0) return false;

        int target = total / k;
        list = new ArrayList<>();
        outer:
        for (int i = (1 << n) - 1; i > 0; i--) {
            int j = i, sum = 0;
            while (j > 0) {
                int idx = Integer.numberOfTrailingZeros(j);
                if (sum + nums[idx] > target) continue outer;
                sum += nums[idx];
                j -= j & -j;
            }
            if (sum == target) list.add(i);
        }
        return dfs1(k, (1 << n) - 1);
    }

    private boolean dfs1(int rem, int bits) {
        if (rem == 0) return true;
        for (int x : list) {
            if ((bits & x) == x && dfs(rem - 1, bits - x)) return true;
        }
        return false;
    }

    // TEST
    public static void main(String[] args) {
        PartitiontoKEqualSumSubsets698 test = new PartitiontoKEqualSumSubsets698();
        int[] data = {7628, 3147, 7137, 2578, 7742, 2746, 4264, 7704, 9532, 9679, 8963, 3223, 2133, 7792, 5911, 3979};
        int k = 6;
        //data = new int[]{129, 17, 74, 57, 1421, 99, 92, 285, 1276, 218, 1588, 215, 369, 117, 153, 22};
        //k = 3;
        //data = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,6};
        //k= 4;

        long start = System.currentTimeMillis();
        //test.canPartitionKSubsets1(data, k);
        //test.canPartitionKSubsets_LTE(data, k);
        //test.canPartitionKSubsets_LTE1(data, k);
        test.canPartitionKSubsets(data, k);
        //test.canPartitionKSubsets_ac(data, k);
        System.out.println(System.currentTimeMillis() - start);
    }
}