/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */

public class JumpGameII {

    public int jump(int[] A) {
        if (A == null || A.length == 0)
            return 0;

        int count = 0, max = 0;
        for (int i = 0, nextMax = 0; i <= max && i < A.length - 1; i++) {
            nextMax = Math.max(nextMax, i + A[i]);
            if (i == max) {
                max = nextMax;
                count++;
            }
        }
        // if there is no way to get to the end, return -1
        return max >= A.length - 1 ? count : -1;
    }

    public static void main(String[] args) {
        int[] test = {3, 2, 1, 0, 4};
        JumpGameII jg = new JumpGameII();
        System.out.println(jg.jump(test));
    }

}
