/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/19/13
 * Time: 2:05 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class SearchInsertionPoint {
    public int searchInsert(int[] A, int target) {
        int l = 0, r = A.length; // note: r = A.length not A.length-1;
        while (l < r) {           // l < r, not l <= r;
            int m = (l + r) / 2;
            if (A[m] >= target)
                r = m;
            else
                l = m + 1;
        }
        return r;                 // return l or r, since both have the same value
    }
}
