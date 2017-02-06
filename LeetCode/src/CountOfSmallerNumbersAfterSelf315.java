/**
 * Created by yuantian on 2/6/17.
 */

import java.util.*;

public class CountOfSmallerNumbersAfterSelf315 {
    /**
     * BIT / fenwrick tree
     *
     * @param nums
     * @return
     */
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

    /**
     * Modified merge sort (try) -- NOT WORKING YET!!!!
     *
     * @return
     */
    class Ele implements Comparable<Ele> {
        int val, idx, count;

        Ele(int val, int idx) {
            this.val = val;
            this.idx = idx;
            this.count = 0;
        }

        public int compareTo(Ele e) {
            if (e.val > this.val)
                e.count += this.count + 1;
            return this.val - e.val;
        }
    }

    public List<Integer> countSmaller1(int[] nums) {
        int n = nums.length;
        Ele[] e = new Ele[n];
        for (int i = 0; i < n; i++) {
            e[i] = new Ele(nums[i], i);
        }
        Arrays.sort(e);
        Integer[] ans = new Integer[n];
        for (Ele x : e) {
            ans[x.idx] = x.count;
            System.out.printf(" val = %d, idx = %d, count = %d \n", x.val, x.idx, x.count);
        }
        return new ArrayList<>(Arrays.asList(ans));
    }


    // for test
    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf315 test = new CountOfSmallerNumbersAfterSelf315();
        int[] data = {5, 2, 6, 1};
        test.countSmaller1(data);
    }
}
