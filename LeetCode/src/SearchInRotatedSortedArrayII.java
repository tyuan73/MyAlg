/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/19/13
 * Time: 1:42 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */


public class SearchInRotatedSortedArrayII {
    /**
     * Non-recursive version. very complicated.
     *
     * @param A
     * @param target
     * @return
     */
    public boolean search(int[] A, int target) {
        int l = 0, r = A.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (A[m] == target) {
                return true;
            }

            if (A[m] > A[l]) {
                if (A[m] < target)
                    l = m + 1;
                else {
                    if (target >= A[l])
                        r = m - 1;
                    else
                        l = m + 1;
                }
            } else if (A[m] < A[l]) {
                if (A[m] > target)
                    r = m - 1;
                else {
                    if (target >= A[l])
                        r = m - 1;
                    else
                        l = m + 1;
                }
            } else {  // A[m] == A[l]
                int i = m;
                while (i > l && A[i - 1] == A[m])
                    i--;
                if (i == l) {
                    l = m + 1;
                } else if (A[i] > A[l]) {
                    if (A[m] < target) {
                        l = m + 1;
                    } else {
                        if (target >= A[l]) {
                            r = m - 1;
                        } else
                            l = m + 1;
                    }
                } else {
                    if (A[m] > target) {
                        r = m - 1;
                    } else {
                        if (target >= A[l])
                            r = m - 1;
                        else
                            l = m + 1;
                    }
                }
            }
        }

        return false;
    }


    /**
     * Recursive version. Note: in the worst case, each call only remove one element from possible set.
     *
     * @param A
     * @param target
     * @return
     */
    public boolean searchRecursive(int[] A, int target) {
        return search(A, 0, A.length - 1, target);
    }

    boolean search(int[] A, int l, int r, int target) {
        //int l = 0, r = A.length-1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (A[m] == target) {
                return true;
            }

            if (A[m] > A[l]) {
                if (A[m] < target)
                    l = m + 1;
                else {
                    if (target >= A[l])
                        r = m - 1;
                    else
                        l = m + 1;
                }
                return search(A, l, r, target);
            } else if (A[m] < A[l]) {
                if (A[m] > target)
                    r = m - 1;
                else {
                    if (target >= A[l])
                        r = m - 1;
                    else
                        l = m + 1;
                }
                return search(A, l, r, target);
            } else {
                return search(A, l, m - 1, target) || search(A, m + 1, r, target);
            }
        }

        return false;
    }

    /**
     * a very neat solution.
     */
    public boolean search2(int[] A, int target) {
        int n = A.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (A[m] == target) return true; //return m in Search in Rotated Array I
            if (A[l] < A[m]) { //left half is sorted
                if (A[l] <= target && target < A[m])
                    r = m - 1;
                else
                    l = m + 1;
            } else if (A[l] > A[m]) { //right half is sorted
                if (A[m] < target && target <= A[r])
                    l = m + 1;
                else
                    r = m - 1;
            } else l++;
        }
        return false;
    }
}
