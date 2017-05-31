/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/28/17
 * Time: 10:33 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class ArrayNesting565 {
    /**
     * Disjoint set or union find.
     */
    public int arrayNesting(int[] nums) {
        int n = nums.length;
        int[] ds = new int[n];
        Arrays.fill(ds, -1);
        int min = -1;
        for (int i = 0; i < n; i++) {
            int a = find(ds, i), b = find(ds, nums[i]); // "a = find(ds, i)" can be removed if we redesign how ds gets updated. See below.
            if (a != b) {
                ds[a] += ds[b];
                ds[b] = a;
                min = Math.min(min, ds[a]);
            }
        }
        return -min;
    }

    private int find(int[] ds, int i) {
        return ds[i] < 0 ? i : (ds[i] = find(ds, ds[i]));
    }

    /**
     * The same idea. Improved a little bit by removing "a = find(ds, i)" from above solution.
     */
    public int arrayNesting1(int[] nums) {
        int n = nums.length;
        int[] ds = new int[n];
        Arrays.fill(ds, -1);
        int min = -1;
        for (int i = 0; i < n; i++) {
            int a = find(ds, nums[i]);
            if (a != i) {
                ds[a] += ds[i];
                ds[i] = a;
                min = Math.min(min, ds[a]);
            }
        }
        return -min;
    }
}
