/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/21/13
 * Time: 11:41 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class OJ42TrappingRainWater {
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int rain = 0;
        int lmax = Integer.MIN_VALUE, rmax = Integer.MIN_VALUE;
        while (l < r) {
            if (height[l] < height[r]) {
                lmax = Math.max(lmax, height[l]);
                rain += lmax - height[l];
                l++;
            } else {
                rmax = Math.max(rmax, height[r]);
                rain += rmax - height[r];
                r--;
            }
        }
        return rain;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
