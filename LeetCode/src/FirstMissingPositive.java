/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class FirstMissingPositive {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    public int firstMissingPositive(int[] A) {
        int l = 0, r = A.length - 1;
        while (l <= r) {
            int x = A[l];
            if (x == l + 1) {
                l++;
                continue;
            }

            /**
             * 1. x <= l means the current number is too small, out of boundary
             * 2. x > r+1 means the current number is too big, out of boundary
             * 3. A[x-1] == x means duplicate numbers
             *
             * when any of the above happened, move it the end and decrease right boundary.
             * we don't really need to write the bad number to the end, we just decrease
             * the right boundary, since any number to the right of right boundary is out of
             * sight, it does not matter if it has the correct value or not.
             *
             */
            if (x <= l || x > r + 1 || A[x - 1] == x) {
                A[l] = A[r--];
            } else {
                A[l] = A[x - 1];
                A[x - 1] = x;
            }
        }
        return l + 1;
    }
}
