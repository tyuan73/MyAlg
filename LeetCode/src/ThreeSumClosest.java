/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/26/13
 * Time: 4:28 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        long ret = Integer.MAX_VALUE;           // <== this is long, not int. because target could be negative.
        for (int i = 0; i < num.length - 2; i++) {
            int l = i + 1, r = num.length - 1;
            while (l < r) {
                int sum = num[i] + num[l] + num[r];
                if (Math.abs(sum - target) < Math.abs(ret - target))
                    ret = sum;
                if (sum == target)
                    return (int) ret;
                if (sum > target)
                    r--;
                else
                    l++;
            }
        }

        return (int) ret;
    }
}
