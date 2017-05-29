/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 5/28/17
 * Time: 9:48 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class MinimumIndexSumofTwoLists599 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        int n = list1.length, m = list2.length;
        List<String> ans = new ArrayList<>();
        for (int sum = 0; ans.size() == 0; sum++)
            for (int i = Math.max(0, sum - m + 1); i <= Math.min(n - 1, sum); i++)
                if (list1[i].equals(list2[sum - i])) ans.add(list1[i]);
        return ans.toArray(new String[ans.size()]);
    }
}
