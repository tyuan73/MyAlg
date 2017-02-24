
import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int sum = -nums[i];
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int val = nums[j] + nums[k];
                if (val == sum) {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j + 1] == nums[j]) j++;
                    j++;
                    k--;        // no need
                } else if (val < sum)
                    j++;
                else
                    k--;
            }
        }
        return ans;
    }
}