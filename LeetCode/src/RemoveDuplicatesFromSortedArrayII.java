/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 12:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoveDuplicatesFromSortedArrayII {
    /**
     * An easy to think of way. Not the best solution
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int count = 1, pre = 1, cur = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (cur++ == 1) {
                    count++;
                    nums[pre++] = nums[i];
                }
            } else {
                cur = 1;
                count++;
                nums[pre++] = nums[i];
            }
        }
        return count;
    }

    /**
     * A better way
     */
    public int removeDuplicates1(int[] A) {
        if (A.length <= 2)
            return A.length;
        int len = 2;
        for (int i = 2; i < A.length; i++) {
            if (A[i] != A[i - 1] || A[len - 1] != A[len - 2])
                A[len++] = A[i];
        }
        return len;
    }

    /**
     * A even better way
     */
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }
}
