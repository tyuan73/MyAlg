/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/13/17
 * Time: 10:52 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class IncreasingSubsequences491 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        //List<List<Integer>> ans = new ArrayList<>();        // no need. List<List<Integer>> can be generated from HashSet directly
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            sub(nums, i, path, set);
        }
        return new ArrayList(set);
    }

    private void sub(int[] nums, int idx, List<Integer> path, Set<List<Integer>> set) {
        //if (idx == nums.length) return;    // no need. it will never happen.

        path.add(nums[idx]);
        if (path.size() >= 2)
            set.add(new ArrayList(path));    // List<Integer> can be generated from another List directly

        for (int i = idx + 1; i < nums.length; i++) {
            if (nums[i] < nums[idx]) continue;
            sub(nums, i, path, set);
        }
        path.remove(path.size() - 1);
    }

    /**
     * A little better solution
     */
    public List<List<Integer>> findSubsequences1(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        getSeq(nums, 0, path, ans);

        return new ArrayList(ans);
    }

    private void getSeq(int[] nums, int idx, List<Integer> path, Set<List<Integer>> ans) {
        //if (idx == nums.length) return;

        for (int i = idx; i < nums.length; i++) {
            if (path.size() > 0 && nums[i] < path.get(path.size() - 1)) continue;

            path.add(nums[i]);
            if (path.size() > 1) ans.add(new ArrayList(path));
            getSeq(nums, i + 1, path, ans);
            path.remove(path.size() - 1);
        }
    }
}
