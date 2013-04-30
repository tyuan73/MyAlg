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

    public int search2(int[] A, int target) {
        int l = 0, r = A.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (A[mid] == target)
                return mid;
            if (A[l] <= A[mid]) {  // left half is sorted
                if (A[l] <= target && target < A[mid])
                    r = mid - 1;
                else
                    l = mid + 1;
            } else {               // right half is sorted
                if (A[mid] < target && target <= A[r])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }

        return -1;
    }
}
