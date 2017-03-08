/**
 * Created by yuantian on 3/8/17.
 */
public class WiggleSort280 {
    public void wiggleSort(int[] nums) {
        boolean odd = true;
        for (int i = 1; i < nums.length; i++, odd = !odd) {
            // the following lines can be shorted:  if ((odd == (nums[i] < nums[i-1])))
            if ((odd && nums[i] < nums[i - 1]) || (!odd && nums[i] > nums[i - 1])) {
                swap(nums, i);
            }
        }
    }

    private void swap(int[] nums, int i) {
        int temp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = temp;
    }
}
