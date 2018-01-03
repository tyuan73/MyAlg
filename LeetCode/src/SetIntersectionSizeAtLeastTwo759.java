/*

*/

import java.util.*;
import java.io.*;

public class SetIntersectionSizeAtLeastTwo759 {
    /**
     * This solution has a little bug! It fails at this test case:
     * [[1,5],[4,5],[5,9],[7,9],[9,10]]
     *
     * The correct answer is 5, but the output of this solution is 4!
     */
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        //Arrays.sort(intervals, new Comparator<int[]>() {
        //    public int compare(int[] a, int[] b) {return a[1] - b[1];}
        //});
        int l = -1, r = -1, ans = 0;
        for (int[] cur : intervals) {
            if (cur[0] > r) {
                ans += 2;
                l = cur[1] - 1;
                r = cur[1];
            } else if (cur[0] > l) {
                ans++;
                l = r;
                r = cur[1];
            }
        }
        return ans;
    }

    /**
     * This is the correct answer!
     */
    public int intersectionSizeTwo1(int[][] intervals) {
        //Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int l = -1, r = -1, ans = 0;
        for (int[] cur : intervals) {
            if (cur[0] > r) {
                ans += 2;
                l = cur[1] - 1;
                r = cur[1];
            } else if (cur[0] > l) {
                ans++;
                l = r == cur[1] ? cur[1] - 1 : r; // this line is critical!!!!
                r = cur[1];
            }
        }
        return ans;
    }

    /**
     * Another way to fix the bug is to change sorting.
     */
    public int intersectionSizeTwo2(int[][] intervals) {
        //Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]; // make sure [7,9] goes before [5,9]
            }
        });
        int l = -1, r = -1, ans = 0;
        for (int[] cur : intervals) {
            if (cur[0] > r) {
                ans += 2;
                l = cur[1] - 1;
                r = cur[1];
            } else if (cur[0] > l) {
                ans++;
                l = r;
                r = cur[1];
            }
        }
        return ans;
    }
}