/**
 * Created by miaomiao on 6/16/17.
 */

import java.util.*;
import java.util.stream.*;

public class SplitArraywithEqualSum548 {
    /**
     * So far the best solution - O(n^2)
     */
    public boolean splitArray1(int[] nums) {
        int n = nums.length;
        if (n < 7) return false;
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }

        for (int j = 3; j < n - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (nums[j - 1] - nums[i] == nums[i - 1]) {
                    set.add(nums[i - 1]);
                }
            }
            for (int k = j + 2; k < n - 1; k++) {
                if (nums[n - 1] - nums[k] == nums[k - 1] - nums[j] && set.contains(nums[n - 1] - nums[k]))
                    return true;
            }
        }
        return false;
    }

    /**
     * This is actually O(n^3) solution. It got AC simply because Leetcode test cases are weak.
     * If tested against:
     *      int[] data = new int[2000];
     *      data[1000] = 1;
     *      data[1001] = 1;
     * It runs about 3+ seconds.
     */
    public boolean splitArray(int[] nums) {

        if (nums.length < 7) {
            return false;
        }

        // calculate cumulative sum
        int[] sums = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < sums.length; i++) {
            sum += nums[i];
            sums[i] = sum;
        }

        // determine i and k first then looking for j
        // to reduce total amount of calculations
        for (int i = 1; i < sums.length - 5; i++) {
            // sum(0, i - 1)
            sum = sums[i] - nums[i];
            for (int k = sums.length - 2; k > 4 && k - i > 3; k--) {
                // sum(k + 1, n - 1)
                if (sum == sums[sums.length - 1] - sums[k]) {
                    for (int j = i + 2; j < k - 1; j++) {
                        // sum(j + 1, k - 1), sum(i + 1, j - 1)
                        if (sum == sums[k] - nums[k] - sums[j]
                                && sum == sums[j] - nums[j] - sums[i]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * O(n^3) - LTE error
     */
    public boolean splitArray2(int[] nums) {
        int n = nums.length;
        if (n < 7) return false;
        for (int i = 1; i < n; i++)
            nums[i] += nums[i - 1];

        for (int j = 3; j < n - 3; j++) {
            for (int i = 1; i < j - 1; i++) {
                if (nums[j - 1] - nums[i] == nums[i - 1]) {
                    for (int k = j + 2; k < n - 1; k++) {
                        if (nums[i - 1] == nums[k - 1] - nums[j] && nums[i - 1] == nums[n - 1] - nums[k]) {
                            System.out.println(i + ", " + j + ", " + k);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean splitArray3(int[] nums) {
        int n = nums.length;
        if ( n < 7) return false;
        Map<Integer, Integer> map = new HashMap<>();
        int[] sum1 = new int[n];
        sum1[0] = nums[0];
        int[] valid = new int[n];
        Arrays.fill(valid, Integer.MAX_VALUE);
        for(int i = 1; i < n -2; i++) {
            sum1[i] = sum1[i-1] + nums[i];
            if (i >= 2) {
                if (map.containsKey(sum1[i]))
                    valid[i+1] = map.get(sum1[i]);
            }
            map.put(sum1[i-1] + sum1[i], sum1[i-1]);
        }
        Map<Integer, Integer> map2 = new HashMap<>();
        int[] sum2 = new int[n];
        sum2[n-1] = nums[n-1];
        for(int i = n-2; i > 1; i--) {
            sum2[i] = sum2[i+1] + nums[i];

            if (i <= n-3 && map2.containsKey(sum2[i]) &&
            valid[i-1] == map2.get(sum2[i])) {
                    return true;
            }
            if (i == 14 || i == 13) {
                System.out.println("[" + i +"]"+" map sum = " + map2.get(sum2[i]) + " key = " + sum2[i]);
            }
            map2.put(sum2[i] + sum2[i+1], sum2[i+1]);
        }

        // debug
        System.out.println("sum1");
        for(int i = 0; i < n; i++) {
            System.out.print(sum1[i] + " ");
        }
        System.out.println();
        System.out.println("sum2");
        for(int i = 0; i < n; i++) {
            System.out.print(sum2[i] + " ");
        }
        System.out.println();

        System.out.println("valid");
        for(int i = 0; i < n; i++) {
            System.out.print(valid[i] + " ");
        }
        System.out.println();

        return false;
    }

    static public void main(String[] args) {

        SplitArraywithEqualSum548 test = new SplitArraywithEqualSum548();
        /*
        int[] data = new int[2000];
        //for(int i = 1900; i < 2000; i++)
        //    data[i] = 1;
        data[1000] = 1;
        data[1001] = 1;
        long start = System.currentTimeMillis();
        System.out.println(test.splitArray2(data));
        System.out.println(System.currentTimeMillis() - start);
        */
        //int[] data = {1,2,1,3,0,0,2,2,1,3,3};
        // 5, 13, 21
        int[] data = {1, 3, 1, 3, 2, 3, 0, 3, 0, 1, 2, 2, 2, -1, 2, 1, 0, 0, 2, 2, 3, -3, 1, 2, 2, 0, 1, 3, 1};
        System.out.println(test.splitArray3(data));
    }
}
