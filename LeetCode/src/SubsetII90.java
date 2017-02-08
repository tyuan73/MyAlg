/**
 * Created by yuantian on 2/8/17.
 */

import java.util.*;

public class SubsetII90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        subset(nums, 0, new ArrayList<Integer>(), ans);
        return ans;
    }

    void subset(int[] nums, int from, List<Integer> list, List<List<Integer>> ans) {
        ans.add(new ArrayList<Integer>(list));

        if (from >= nums.length)    // this two lines are not required.
            return;

        for (int i = from; i < nums.length; i++) {
            if (i != from && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            subset(nums, i + 1, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
