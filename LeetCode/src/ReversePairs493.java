/**
 * Created by yuantian on 3/28/17.
 */

import java.util.*;

public class ReversePairs493 {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        long[] map = new long[n + 2];
        map[n] = Long.MIN_VALUE;
        map[n + 1] = Long.MAX_VALUE;
        for (int i = 0; i < n; i++)
            map[i] = nums[i];
        Arrays.sort(map);

        int[] bit = new int[n + 3];
        int ans = 0;
        for (int x : nums) {
            int i = index(map, x);
            int j = index(map, x * 2l);
            ans += sum(bit, j);
            add(bit, i);
        }

        return ans;
    }

    private void add(int[] bit, int i) {
        for (; i < bit.length; i += i & -i)
            bit[i]++;
    }

    private int sum(int[] bit, int i) {
        int sum = 0, j = bit.length - 1;
        while (i != j) {
            if (i > j) {
                sum -= bit[i];
                i -= i & -i;
            } else {
                sum += bit[j];
                j -= j & -j;
            }
        }
        return sum;
    }

    private int index(long[] map, long x) {
        int l = 0, r = map.length - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (map[mid] > x) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
}
