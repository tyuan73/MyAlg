/**
 * Created by yuantian on 2/3/16.
 *
 * https://leetcode.com/problems/patching-array/
 *
 */
public class OJ330PatchingArray {

    /*
    Very slow solution!!!!! runtime might be over O(n^2)
     */
    public int minPatches1(int[] nums, int n) {
        int max = 0;
        int[] res = new int[n + 1];
        res[0] = 1;
        int count = 0;
        for (int x : nums) {
            for (int i = max; i >= 0; i--) {
                if (res[i] == 1 && (i + x <= n) && res[i + x] == 0) {
                    count++;
                    res[i + x] = 1;
                }
            }
            max += x;
        }

        int ans = 0;
        int[] more = new int[n + 1];
        int idx = 0;
        while (count < n) {
            int toAdd = 0;
            for (int i = 1; i <= n; i++) {
                if (res[i] == 0) {
                    toAdd = i;
                    ans++;
                    break;
                }
            }
            for (int i = 0; i < n; i++) {
                if (res[i] == 1 && (i + toAdd <= n) && res[i + toAdd] == 0) {
                    more[idx++] = i + toAdd;
                }
            }
            count += idx;
            for (int i = 0; i < idx; i++)
                res[more[i]] = 1;
        }
        return ans;
    }

    /*
    A much better solution. O(n) ???
     */

    public int minPatches(int[] nums, int n) {
        long max = 1;
        int ans = 0, i = 0;
        while(max <= n) {
            if (i < nums.length && nums[i] <= max) {
                max += nums[i];
                i++;
            } else {
                ans++;
                max += max;
            }
        }
        return ans;
    }
}
