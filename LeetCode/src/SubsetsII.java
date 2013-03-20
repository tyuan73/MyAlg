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

    /**
     * A more elegant solution
     * @param num
     * @return
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup1(int[] num) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        Arrays.sort(num);
        helper1(num, 0, new ArrayList<Integer>(), ret);
        return ret;
    }

    void helper1(int[] num, int index, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ret) {
        ret.add((ArrayList<Integer>)list.clone());
        int pre = num[0]-1;
        for(int i = index; i < num.length; i++) {
            if(num[i] != pre) {
                pre = num[i];
                list.add(num[i]);
                helper1(num,i+1,list, ret);
                list.remove(list.size()-1);
            }
        }
    }

    /**
     * Iterative version.
     *
     * The basic idea is to add the same element (repeated "len" times) to each one of the previous unique subsets one
     * by one.
     *
     * @param num
     * @return
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup2(int[] num) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        /**
         * to find the unique elements. save the indexes to "next" array.
         * so, the element is repeated "next.get(i+1)-next.get(i)" times.
         */
        Arrays.sort(num);
        ArrayList<Integer> next = new ArrayList<Integer>();
        next.add(0);
        for(int i = 1; i < num.length; i++)
            if(num[i] != num[i-1])
                next.add(i);
        next.add(num.length);

        /**
         * take each one of previous saved subset, add repeated elements "len" times (len = next.get(i+1)-next.get(i))
         * and save the subsets along the way.
         */
        ret.add(new ArrayList<Integer>());
        for(int i = 0; i < next.size()-1; i++) {
            int len = next.get(i+1) - next.get(i);
            int cursize = ret.size();     // note: this is a fixed value while "ret.size()" is growing.
            for(int j = 0; j < cursize; j++) {  // don't use ret.size() here!!!
                ArrayList<Integer> list = ret.get(j);
                for(int k = 0; k < len; k++) {
                    list = new ArrayList<Integer>(list);
                    list.add(num[next.get(i)]);
                    ret.add(list);
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
