/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class JumpGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    public boolean canJump(int[] A) {
        int max = 0;
        for (int i = 0; i <= max && i < A.length; i++) {
            max = Math.max(max, i + A[i]);
        }

        return max >= A.length - 1 ? true : false;
    }
}
