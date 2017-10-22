/*

*/

import java.util.*;
import java.io.*;

public class SubarrayProductLessThanK713 {
    /**
     * DP, count how many such subarrays ending with "i"th element.
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0; // if k == 0 or 1, the following code will error out because "start++" increase over n.
        int start = 0, prd = 1, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            prd *= nums[i];
            while (prd >= k) {
                prd /= nums[start++];
            }
            ans += i - start + 1;
        }
        return ans;
    }
}