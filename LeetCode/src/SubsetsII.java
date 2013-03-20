/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 2:34 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class SubsetsII {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {

        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        ret.add(new ArrayList<Integer>());

        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            if (i == 0 || num[i] != num[i - 1])
                helper(num, i, new ArrayList<Integer>(), ret);
        }
        return ret;
    }

    void helper(int[] num, int index, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ret) {
        if (index == num.length)
            return;

        list.add(num[index]);
        ret.add((ArrayList<Integer>) list.clone());

        helper(num, index + 1, list, ret);
        for (int i = index + 2; i < num.length; i++) {
            if (num[i] != num[i - 1])
                helper(num, i, list, ret);
        }
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
