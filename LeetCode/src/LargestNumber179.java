/**
 * Created by yuantian on 2/8/17.
 */

import java.util.*;

public class LargestNumber179 {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++)
            strs[i] = Integer.toString(nums[i]);

        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String s1, String s2) {
                String c1 = s1 + s2;
                String c2 = s2 + s1;
                return c2.compareTo(c1);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String s : strs)
            sb.append(s);
        String ans = sb.toString();
        if (ans.charAt(0) == '0') return "0";
        return ans;
    }
}
