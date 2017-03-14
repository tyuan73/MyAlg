/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/13/17
 * Time: 9:47 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class FactorCombinations254 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        get(n, new ArrayList<Integer>(), ans);
        return ans;
    }

    private void get(int n, List<Integer> list, List<List<Integer>> ans) {
        if (n == 1) {
            if (list.size() > 1)
                ans.add(new ArrayList(list));
            return;
        }
        int min = list.size() == 0 ? 2 : list.get(list.size() - 1);
        //int max = (int) Math.sqrt(n);
        for (int i = min; i <= n; i++) {
            if ((n % i) != 0) continue;
            list.add(i);
            get(n / i, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
