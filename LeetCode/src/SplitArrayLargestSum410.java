/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 2/16/17
 * Time: 11:51 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class SplitArrayLargestSum410 {
    /**
     * Two binary search!!!
     *
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int max = 0;
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            sum[i + 1] = sum[i] + nums[i];
        }
        long l = max, r = sum[n];
        while (l < r) {
            long mid = (l + r) / 2;
            if (valid(sum, m, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    boolean valid(long[] sum, int m, long max) {
        int l = 0;
        long target = max;
        while (m-- > 0) {
            int r = sum.length - 1;
            while (l < r) {
                int mid = (l + r + 1) / 2;
                if (sum[mid] > target)
                    r = mid - 1;
                else
                    l = mid;
            }
            if (l == sum.length - 1)
                return true;
            target = sum[l] + max;
        }
        return false;
    }

    public static void main(String[] args) {
        SplitArrayLargestSum410 test = new SplitArrayLargestSum410();
        int[] data = {1, 2147483647};
        System.out.println(test.splitArray(data, 2));
    }

    /**
     *
     [7,2,5,10,8]
     2
     ==> 18

     [10,5,13,4,8,4,5,11,14,9,16,10,20,8]
     8
     ==> 25

     [1,2147483647]
     2
     ==> 2147483647

     [1,2147483646]
     1
     ==> 2147483646

     */
}
