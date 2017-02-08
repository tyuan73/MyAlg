/**
 * Created by yuantian on 2/8/17.
 */

import java.util.*;

public class CombinationSum39 {
    /**
     * Original AC solution, a few improvements below.
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        find(candidates, 0, 0, target, new ArrayList<Integer>(), ans);
        return ans;
    }

    void find(int[] can, int sum, int from, int target, List<Integer> list, List<List<Integer>> ans) {
        if (sum > target || from >= can.length) return;
        if (sum == target) {
            List<Integer> one = new ArrayList(list);
            ans.add(one);
            return;
        }

        for (int i = from; i < can.length; i++) {
            int x = can[i];
            list.add(x);
            find(can, sum + x, i, target, list, ans);
            list.remove(list.size() - 1);
        }
    }

    /**
     * The same as above with a few improvements.
     */
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        find(candidates, 0, target, new ArrayList<Integer>(), ans);
        return ans;
    }

    void find(int[] can, int from, int target, List<Integer> list, List<List<Integer>> ans) {
        if (target < 0) return;
        if (target == 0) {
            ans.add(new ArrayList(list));
            return;
        }

        for (int i = from; i < can.length; i++) {
            int x = can[i];
            list.add(x);
            find(can, i, target - x, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
