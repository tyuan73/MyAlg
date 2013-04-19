/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/19/13
 * Time: 11:07 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class SearchForARange {
    public int[] searchRange(int[] A, int target) {
        int n = A.length;
        int[] ret = {-1, -1};

        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (A[mid] < target)
                l = mid + 1;
            else
                r = mid;
        }

        if (A[l] != target)
            return ret;

        ret[0] = l;
        r = n - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (A[mid] > target)
                r = mid - 1;
            else
                l = mid;
        }
        ret[1] = r;
        return ret;
    }
}
