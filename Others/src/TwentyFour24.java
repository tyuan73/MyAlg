/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/22/17
 * Time: 9:56 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class TwentyFour24 {
    public static void main(String[] args) {
        int[][] test = {
                {2, 5, 5, 6},
                {1, 2, 3, 5},
                {7, 2, 4, 3},
                {2, 4, 3, 6},
                {1, 2, 5, 2},
                {4, 6, 7, 6},
                {3, 9, 1, 6},
                {1, 5, 5, 5}
        };
        int[] data = new int[4];
        int count = 0, total = 0;
        for (int i = 1; i < 10; i++) {
            data[0] = i;
            for (int j = i; j < 10; j++) {
                data[1] = j;
                for (int k = j; k < 10; k++) {
                    data[2] = k;
                    for (int q = k; q < 10; q++) {
                        data[3] = q;
                        total++;
                        if (!validate(data) && judgePoint24(data)) {
                            count++;
                            for (int x : data) {
                                System.out.print(x + " ");
                            }
                            System.out.println();
                        }
                    }
                }

            }
        }
        System.out.println(count + " / " + total);
        /*
        for(int i = 0; i < test.length; i++)
            if (validate((test[i])))
                System.out.println("yes");
            else
                System.out.println("no");
                */

        /*

        Random rand = new Random();
        //for(int[] t : test)
        int[] t = new int[4];
        int total = 0;
        for (int i = 0; i < 1000; i++) {
            //for(int[] t : test) {
            for (int j = 0; j < 4; j++)
                t[j] = rand.nextInt(10) + 1;

            if (!validate(t)) {
                //System.out.println("No");
                for (int j = 0; j < 4; j++)
                    System.out.print(t[j] + " ");
                System.out.println();
                total++;
            } else {
                //System.out.println("Yes");
            }
        }
        System.out.println("total = " + total);
        */
    }

    static private boolean validate(int[] nums) {
        if (nums.length == 1) {
            if (nums[0] == 24)
                return true;
            return false;
        }

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] next = new int[n - 1];
                int idx = 0;
                for (int k = 0; k < n; k++)
                    if (k != i && k != j)
                        next[idx++] = nums[k];

                for (int m : cal(nums[i], nums[j])) {
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
        ans.add(a * b);
        ans.add(a - b);
        ans.add(b - a);
        if (b != 0 && (a % b) == 0)
            ans.add(a / b);
        if (a != 0 && (b % a) == 0)
            ans.add(b / a);
        return ans;
    }

    /**
     * The following solution allow 8 / (3 - (8 / 3)).
     */
    static public boolean judgePoint24(int[] nums) {
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j) continue;
                    for (int q = 0; q < 4; q++) {
                        if (q == i || q == j || q == k) continue;
                        double a = nums[i], b = nums[j], c = nums[k], d = nums[q];
                        if (check3(a + b, c, d) || check3(a - b, c, d) || check3(a * b, c, d) || check3(a / b, c, d) || check3(b / a, c, d))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    static private boolean check3(double a, double b, double c) {
        if (check2(a + b, c) || check2(a - b, c) || check2(a * b, c) || (a != 0 && check2(b / a, c)))
            return true;
        if (check2(a + c, b) || check2(a - c, b) || check2(a * c, b) || (a != 0 && check2(c / a, b)))
            return true;
        if (check2(b + c, a) || check2(b - c, a) || check2(b * c, a) || (c != 0 && check2(b / c, a)))
            return true;
        return false;
    }

    static private boolean check2(double a, double b) {
        if (is24(a + b) || is24(a - b) || is24(a * b) || (a != 0 && is24(b / a)))
            return true;
        return false;
    }

    static private boolean is24(double a) {
        if (Math.abs(Math.abs(a) - 24) <= 0.01 || Math.abs(Math.abs(1 / a) - 24) <= 0.01)
            return true;
        return false;
    }
}
