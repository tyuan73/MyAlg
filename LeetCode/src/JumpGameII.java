/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class JumpGameII {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    /**
     * Assume there is always a way you can get to the end.
     */
    public int jump(int[] A) {
        if(A == null || A.length == 0)
            return 0;

        int max = 0;
        int count = 0;
        int nextmax = 0;
        for(int i = 0; i <= max && i < A.length-1; i++) {
            nextmax = Math.max(nextmax, i+A[i]);
            if(i == max) {
                max = nextmax;
                count++;
            }
        }
        return count ;
    }
}
