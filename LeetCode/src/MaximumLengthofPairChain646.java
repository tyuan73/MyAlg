/**
 * Created by yuantian on 7/25/17.
 */

import java.util.*;

public class MaximumLengthofPairChain646 {
    public int findLongestChain(int[][] pairs) {
        //Arrays.sort(pairs, (a, b) -> (a[1] - b[1]));
        /**
         * in Leetcode, somehow, the regular sort is much faster than lambda version above.
         */
        Arrays.sort(pairs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int len = 1, end = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > end) {
                len++;
                end = pairs[i][1];
            }
        }
        return len;
    }
}
