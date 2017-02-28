/**
 * Created by yuantian on 2/28/17.
 */
public class MaxConsecutiveOnesII487 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ones1 = 0, ones2 = 0;
        int max = 0;
        for (int x : nums) {
            if (x == 1)
                ones2++;
            else {
                max = Math.max(max, ones1 + ones2 + 1);
                ones1 = ones2;
                ones2 = 0;
            }
        }
        // Math.min(nums.length, ones1 + ones2 + 1) is to check if all numbers are 1s
        return Math.max(max, Math.min(nums.length, ones1 + ones2 + 1));
    }
}
