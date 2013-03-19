/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/7/13
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int A[], int B[]) {
        int n = A.length;
        int m = B.length;
        if (n > m)
            return findMedianSortedArrays(B, A);

        int k = (n + m - 1) / 2;
        int l = 0, r = Math.min(k, n); // r is n, NOT n-1, this is important!!
        while (l < r) {
            int midA = (l + r) / 2;
            int midB = k - midA;
            if (midB > k || A[midA] < B[midB])
                l = midA + 1;
            else
                r = midA;
        }

        int a = l > 0 ? A[l - 1] : Integer.MIN_VALUE;
        int b = k - l >= 0 ? B[k - l] : Integer.MIN_VALUE;
        a = Math.max(a, b);
        if (((n + m) % 2) == 1)
            return (double) a;

        int c = l < n ? A[l] : Integer.MAX_VALUE;
        int d = k - l + 1 < m ? B[k - l + 1] : Integer.MAX_VALUE;
        c = Math.min(c, d);
        return (a + c) / 2.0;
    }

}
