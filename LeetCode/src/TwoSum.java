/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/22/13
 * Time: 12:11 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] ret = {-1,-1};
        for(int i = 0; i < numbers.length; i++) {
            int x = numbers[i];
            if(map.containsKey(x)) {
                if(2*x == target) {
                    ret[0] = map.get(x)+1;
                    ret[1] = i+1;
                    return ret;
                }
            } else {
                map.put(x, i);
            }
        }

        for(int i = 0; i < numbers.length; i++) {
            if(map.containsKey(target-numbers[i])) {
                ret[0] = i+1;
                ret[1] = map.get(target-numbers[i]) + 1;
                break;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
