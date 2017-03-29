/**
 * Created by yuantian on 3/28/17.
 */

import java.util.*;

public class KdiffPairsinArray532 {
    /**
     * The first solution with HashMap. note: k can be 0.
     */
    public int findPairs(int[] nums, int k) {
        if (k < 0) return 0;
        Map<Integer, Boolean> map = new HashMap<>();
        int ans = 0;
        for (int x : nums) {
            if (map.containsKey(x)) {
                if (k == 0 && !map.get(x)) {
                    map.put(x, true);
                    ans++;
                }
                continue;
            }
            if (map.containsKey(x - k)) ans++;
            if (map.containsKey(x + k)) ans++;
            map.put(x, false);
        }
        return ans;
    }

    /**
     * Two pointers
     */
    public int findPairs1(int[] nums, int k) {
        if (k < 0) return 0;
        Arrays.sort(nums);
        int l = 0, r = 0;
        int ans = 0;
        while (l < nums.length && r < nums.length) {
            if (k == 0) {
                if (l < nums.length - 1 && nums[l] == nums[l + 1]) {
                    ans++;
                    l++;
                    while (l < nums.length && nums[l] == nums[l - 1]) l++;
                } else {
                    l++;
                }
                continue;
            }
            if (l > 0 && nums[l] == nums[l - 1]) {
                l++;
                continue;
            } else if (r > 0 && nums[r] == nums[r - 1]) {
                r++;
                continue;
            }

            if (nums[r] - nums[l] == k) {
                ans++;
                l++;
                r++;
            } else if (nums[r] - nums[l] < k) {
                r++;
            } else {
                l++;
            }
        }
        return ans;
    }
}
