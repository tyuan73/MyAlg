/**
 * Created by yuantian on 2/28/17.
 */
public class RotateArray189 {
    /** 3 ways to solution this problem */

    /**
     * O(n) space
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] h = new int[n];
        System.arraycopy(nums, 0, h, 0, n);
        for (int i = 0; i < n; i++) {
            nums[(i + k) % n] = h[i];
        }
    }

    /**
     * by flipping, two pass
     */
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        flip(nums, 0, n - k - 1);
        flip(nums, n - k, n - 1);
        flip(nums, 0, n - 1);
    }

    private void flip(int[] nums, int l, int r) {
        for (; l < r; l++, r--) {
            int x = nums[l];
            nums[l] = nums[r];
            nums[r] = x;
        }
    }

    /**
     * one pass, but more complicated.
     */
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int g = gcd(n, k);
        for (int i = 0; i < g; i++) {
            int pre = nums[i];
            for (int j = 0, idx = (i + k) % n; j < n / g; j++, idx = (idx + k) % n) {
                int temp = nums[idx];
                nums[idx] = pre;
                pre = temp;
            }
        }
    }

    private int gcd(int n, int k) {
        if (k == 0) return n;
        return gcd(k, n % k);
    }
}
