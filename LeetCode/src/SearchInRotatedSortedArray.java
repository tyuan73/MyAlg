/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/19/13
 * Time: 1:02 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class SearchInRotatedSortedArray {
    public int search(int[] A, int target) {
        int l = 0, r = A.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (A[m] == target) {
                return m;
            }

            if (A[m] > target) {
                if (A[m] < A[0])
                    r = m - 1;
                else {
                    if (target >= A[0])
                        r = m - 1;
                    else
                        l = m + 1;
                }
            } else {
                if (A[m] >= A[0])
                    l = m + 1;
                else {
                    if (target >= A[0])
                        r = m - 1;
                    else
                        l = m + 1;

                }
            }
        }

        return -1;
    }
}
