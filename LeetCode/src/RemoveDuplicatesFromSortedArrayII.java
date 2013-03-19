/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 12:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] A) {
        if (A.length <= 2)
            return A.length;
        int len = 2;
        for (int i = 2; i < A.length; i++) {
            if (A[i] != A[i - 1] || A[len - 1] != A[len - 2])
                A[len++] = A[i];
        }
        return len;
    }
}
