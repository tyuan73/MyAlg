/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 10:33 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

public class SortColors {

    /**
     * Counting sort version. 2 pass.
     *
     * @param A
     */
    public void sortColors1(int[] A) {
        int[] count = {0, 0, 0};
        for (int i : A)
            count[i]++;

        int index = 0;
        for (int i = 0; i < 3; i++) {
            while (count[i]-- > 0)
                A[index++] = i;
        }
    }

    /**
     * One pass version.
     *
     * @param A
     */
    public void sortColors(int[] A) {
        int w = 0, b = A.length;
        for (int i = 0; i < b; i++) {
            if (A[i] == 2) {
                A[i--] = A[--b];
                A[b] = 2;
            } else if (A[i] == 0) {
                A[i] = 1;
                A[w++] = 0;
            }
        }
    }
}
