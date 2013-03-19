/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/19/13
 * Time: 11:07 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class SearchForARange {
    public int[] searchRange(int[] A, int target) {
        int[] ret = {-1, -1};

        int l = 0, r = A.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (A[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }

        if (A[l] == target) {
            int i = l, j = A.length - 1;
            while (i < j) {
                int mid = (i + j + 1) / 2;
                if (A[mid] <= target)
                    i = mid;
                else
                    j = mid - 1;
            }
            ret[0] = l;
            ret[1] = i;
        }

        return ret;
    }
}
