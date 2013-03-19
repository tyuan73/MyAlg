/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    public int maxArea(int[] height) {
        int max = 0;
        int l = 0, r = height.length - 1;
        while (l < r) {
            int lx = height[l];
            int rx = height[r];
            max = Math.max(max, (r - l) * Math.min(lx, rx));
            if (lx <= rx)
                l++;
            else
                r--;
        }

        return max;
    }
}
