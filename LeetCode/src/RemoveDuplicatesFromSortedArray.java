/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 12:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] A) {
        if(A.length <= 1)
            return A.length;
        int len = 1;
        for(int i = 1; i < A.length; i++) {
            if(A[i] != A[i-1])
                A[len++] = A[i];
        }

        return len;
    }
}
