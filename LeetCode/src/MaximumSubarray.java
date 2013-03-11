/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/7/13
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] A) {
        int max = Integer.MIN_VALUE;   // if empty subsequence is allowed, init max = 0;
        int cur = 0;
        for(int i = 0; i < A.length; i++) {
            cur += A[i];
            max = Math.max(max, cur);
            cur = Math.max(0, cur);
        }

        return max;
    }
}
