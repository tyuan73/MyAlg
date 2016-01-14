/**
 * Created by yuantian on 1/13/16.
 */

import java.util.*;

public class OJ315CountOfSmallerNumberAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        Integer[] res = new Integer[len];
        int max = clean(nums);
        int[] fw = new int[max + 1];
        for (int i = len - 1; i >= 0; i--) {
            int total = 0;
            for (int q = nums[i] - 1; q > 0; q -= q & -q)
                total += fw[q];
            res[i] = total;
            for (int q = nums[i]; q <= max; q += q & -q)
                fw[q]++;
        }
        return new ArrayList<Integer>(Arrays.asList(res));
    }

    private int clean(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int x : nums) {
            max = Math.max(max, x);
            min = Math.min(min, x);
        }
        for (int i = 0; i < nums.length; i++)
            nums[i] -= min - 1;
        return max - min + 1;
    }
}
