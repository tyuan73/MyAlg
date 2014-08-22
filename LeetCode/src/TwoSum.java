/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/22/13
 * Time: 12:11 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.HashMap;
import java.util.Scanner;

public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int[] ret = {-1, -1};
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = numbers.length-1; i >= 0; i--) {
            int x = numbers[i];
            if (map.containsKey(target - x)) {
                ret[0] = i+1;
                ret[1] = map.get(target-x) + 1;
                return ret;
            }
            map.put(x, i);
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
