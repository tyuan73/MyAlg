/**
 * Created by yuantian on 3/28/17.
 */

import java.util.*;

public class ReversePairs493 {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        int[] map = new int[n + 1];
        map[n] = Integer.MIN_VALUE;
        System.arraycopy(nums, 0, map, 0, n);
        Arrays.sort(map);

        int[] bit = new int[n + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            int y = x / 2;
            if (x < 0 || (x & 1) == 0)
                y--;
            ans += sum(bit, index(map, y));

            add(bit, index(map, x));
        }

        return ans;
    }

    private void add(int[] bit, int i) {
        for (; i < bit.length; i += i & -i)
            bit[i]++;
    }

    private int sum(int[] bit, int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i)
            sum += bit[i];
        return sum;
    }

    private int index(int[] map, int x) {
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

/*
Test cases:
[1,3,2,3,1]
[-2147483648,0]
[0, -2147483648]
[0, 3,-2147483648]
[7,4]
[8,4]
[9,4]
[-7,-4]
[-8,-4]
[-9, -4]
[-10,-4]

output:
2
0
1
2
0
0
1
1
0
0
0
 */
