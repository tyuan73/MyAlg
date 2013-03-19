/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 1:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoveElement {
    public int removeElement(int[] A, int elem) {
        int l = 0, r = A.length - 1;
        while (l <= r) {
            if (A[l] != elem)
                l++;
            else
                A[l] = A[r--];
        }

        return l;
    }
}
