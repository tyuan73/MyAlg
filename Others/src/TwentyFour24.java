/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/22/17
 * Time: 9:56 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class TwentyFour24 {
    public static void main(String[] args)
    {
        int[][] test = {
                {2,5,5,6},
                {1,2,3,5},
                {7,2,4,3},
                {2,4,3,6},
                {1,2,5,2},
                {4,6,7,6},
                {3,9,1,6}
        };

        Random rand = new Random();
        //for(int[] t : test)
        int[] t = new int[4];
        int total = 0;
        for(int i = 0; i <1000; i++) {
            //for(int[] t : test) {
            for(int j = 0; j < 4; j++)
                t[j] = rand.nextInt(10)+1;

            if (!validate(t)) {
                //System.out.println("No");
                for(int j = 0; j < 4; j++)
                    System.out.print(t[j]+ " ");
                System.out.println();
                total++;
            } else {
                //System.out.println("Yes");
            }
            //System.out.println("total = " + total);
        }
        System.out.println("total = " + total);
    }

    static private boolean validate(int[] nums) {
        if (nums.length == 1) {
            if (nums[0] == 2)
                return true;
            return false;
        }

        int n = nums.length;
        //int[] next = new int[n];
        //System.copyarray(nums, 0, next, 0, n);
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int[] next = new int[n-1];
                int idx = 0;
                for(int k = 0; k < n; k++)
                    if (k != i && k != j)
                        next[idx++] = nums[k];

                for(int m : cal(nums[i], nums[j])) {
                    next[idx] = m;
                    if (validate(next))
                        return true;
                }
            }
        }

        return false;
    }

    static private List<Integer> cal(int a, int b) {
        List<Integer> ans = new ArrayList<>();
        ans.add(a + b);
        ans.add(a*b);
        ans.add(a-b);
        ans.add(b-a);
        if (b!= 0 &&(a%b) ==0)
            ans.add(a/b);
        if (a!=0 &&(b%a) == 0)
            ans.add(b/a);
        return ans;
    }
}
