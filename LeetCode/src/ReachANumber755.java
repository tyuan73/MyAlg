/*

*/

import java.util.*;
import java.io.*;

public class ReachANumber755 {
    /**
     * The fastest. O(0) runtime and O(0) space.
     */
    public int reachNumber(int target) {
        target = Math.abs(target);
        int ans = (int) Math.ceil(((Math.sqrt(target * 8l + 1) - 1) / 2.0));
        int diff = (ans + 1) * ans / 2 - target;
        return diff % 2 == 0 ? ans : ans + 1 + (ans & 1);
    }

    /**
     * O(sqrt(target)).
     */
    public int reachNumber1(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0)
            target -= ++k;
        return target % 2 == 0 ? k : k + 1 + k % 2;
    }

    /**
     * Faster than reachNumber1.
     */
    public int reachNumber2(int target) {
        if (target < 0) target = -target;
        int ans = (int) Math.sqrt(target);
        while (true) {
            int sum = (ans + 1) * ans / 2;
            if (sum >= target && (sum - target) % 2 == 0) break;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        ReachANumber755 test = new ReachANumber755();
        int max = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i <= 1000000000; i++) {
            int a = test.reachNumber(i);
            //int b = test.reachNumber1(i);
            //int c = test.reachNumber2(i);
            //if (a != b)
            //    System.out.println(" i = " + i);
            //test.reachNumber(i);
            //System.out.println(i + ": " + a);
            max = Math.max(max, a);
        }
        //test.reachNumber(1000000000);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("max = " + max);
    }
}