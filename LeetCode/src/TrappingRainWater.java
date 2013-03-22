/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/21/13
 * Time: 11:41 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class TrappingRainWater {
    public int trap(int[] A) {
        if(A == null || A.length < 3)
            return 0;

        int ret = 0;
        int l = 0, r = A.length-1;
        int maxL = A[l], maxR = A[r];
        while(l < r) {
            if(maxL < maxR) {
                l++;
                maxL = Math.max(maxL, A[l]);
                ret += maxL - A[l];
            } else {
                r--;
                maxR = Math.max(maxR, A[r]);
                ret += maxR - A[r];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
