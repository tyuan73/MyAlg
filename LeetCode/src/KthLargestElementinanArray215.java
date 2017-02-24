/**
 * Created by yuantian on 2/24/17.
 */

import java.util.*;

public class KthLargestElementinanArray215 {
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        select(nums, 0, nums.length - 1, k - 1);
        return nums[k - 1];
    }

    void select(int[] nums, int from, int to, int k) {
        int l = from, r = to;
        int pivot = nums[l];
        while (l < r) {
            if (nums[r--] > pivot)
                swap(nums, ++r, ++l);
        }
        swap(nums, from, l);
        if (l < k)
            select(nums, l + 1, to, k);
        else if (l > k)
            select(nums, from, l - 1, k);
        // if l == k, we are done
    }

    void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    void shuffle(int[] nums) {
        Random rand = new Random();
        int n = nums.length;
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(nums, i, j);
        }
    }
}
