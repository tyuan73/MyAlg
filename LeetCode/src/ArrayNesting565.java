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
            int a = find(ds, i), b = find(ds, nums[i]);
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
}
