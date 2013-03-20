/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/20/13
 * Time: 1:47 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class Subsets {
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        Arrays.sort(S);
        int n = 1 << S.length;
        for(int i = 0; i < n; i++) {
            int x = 0;
            ArrayList<Integer> list = new ArrayList<Integer>();
            while((1<<x) <= i) {
                if((i & (1<<x)) > 0)
                    list.add(S[x]);
                x++;
            }
            ret.add(list);
        }

        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
