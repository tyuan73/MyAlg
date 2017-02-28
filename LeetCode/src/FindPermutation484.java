/**
 * Created by yuantian on 2/28/17.
 */
public class FindPermutation484 {
    public int[] findPermutation(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        ans[0] = 1;
        int start = 0;
        for (int i = 1; i <= n; i++) {
            ans[i] = i + 1;
            if (s.charAt(i - 1) == 'D') {
                if (i == n || s.charAt(i) == 'I') {
                    flip(ans, start, i);
                }
            } else {
                start = i;
            }
        }
        return ans;
    }

    void flip(int[] nums, int l, int r) {
        for (; l < r; l++, r--) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
    }
}
