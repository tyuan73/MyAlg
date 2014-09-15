/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/21/13
 * Time: 11:54 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] ret = new int[triangle.size() + 1];
        Arrays.fill(ret, Integer.MAX_VALUE);
        ret[1] = 0;
        for (List<Integer> list : triangle) {
            for (int i = list.size(); i > 0; i--) {
                ret[i] = Math.min(ret[i - 1], ret[i]) + list.get(i - 1);
            }
        }

        int min = Integer.MAX_VALUE;
        for (int x : ret)
            min = Math.min(min, x);
        return min;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
