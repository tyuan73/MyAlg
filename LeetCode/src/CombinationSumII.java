/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/2/13
 * Time: 1:36 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class CombinationSumII {
    static int count = 0;
    static int count1 = 0;
    static int mycount = 0;
    static int mycount1 = 0;

    public static void main(String[] args) {
        //int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,42,41,43,44};
        //int t = 100;
        int[] a = {1,2,4,8,16,32};
        int t = 24;

        CombinationSumII test = new CombinationSumII();
        test.combinationSum2(a, t);
        test.combinationSumRecursive(a, t);
    }

    /**
     *  combinationSumRecursive is backtracking solution which has a running time at about O(n!)
     *
     * @param num
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> combinationSumRecursive(int[] num, int
            target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> item = new ArrayList<Integer>();
        Arrays.sort(num);
        if (num != null || num.length > 0)
            combinations(num, target, result, item, 0);

        /*
        for (ArrayList<Integer> r : result) {
            for (int x : r)
                System.out.printf("%5d", x);
            System.out.println();
        }
        */
        System.out.println("Total = " + result.size());
        System.out.println("count = " + count);
        System.out.println("count1 = " + count1);

        return result;
    }

    public void combinations(int num[], int target, ArrayList<ArrayList<
            Integer>> result, ArrayList<Integer> item, int startIndex) {
        // for debug and comparing with the other solution
        count++;
        if (target < 0 || startIndex > num.length)
            return;
        if (target == 0) {
            result.add((ArrayList<Integer>) item.clone());
            return;
        }

        // for debug and comparing with the other solution
        count1++;
        for (int i = startIndex; i < num.length; i++) {
            item.add(num[i]);
            combinations(num, target - num[i], result, item, i + 1);
            item.remove(item.size() - 1);
            while (i + 1 < num.length && num[i] == num[i + 1]) {
                i++;
            }
        }
    }

    /**
     * combinationSum2 is a better solution which use a dp table to record possible ways and
     * then employs backing tracking to search through the dp table. I think the running time is
     * about O(n*target) for dp, and O(m*len) where m is the total number of unique combinations and
     * len is the average length of all combinations.
     *
     * @param num
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        int n = num.length;
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;

        Arrays.sort(num);
        int[] multi = new int[n];
        multi[0] = 1;
        for (int i = 1; i < n; i++)
            if (num[i] == num[i - 1])
                multi[i] = multi[i - 1] + 1;
            else
                multi[i] = 1;

        for (int i = 1; i <= n; i++) {
            int x = num[i - 1] * multi[i - 1];
            dp[i][0] = 1;
            for (int j = 1; j <= target; j++) {
                if (j < x)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - x];
            }
        }
        /*
        for (int[] x : dp) {
            for (int y : x)
                System.out.printf("%5d", y);
            System.out.println();
        }
        */

        getSum(n, target, num, dp, new ArrayList<Integer>(), ret);

        /*
        for (ArrayList<Integer> r : ret) {
            for (int x : r)
                System.out.printf("%5d", x);
            System.out.println();
        }
        */
        System.out.println("total = " + ret.size());
        System.out.println("mycount = " + mycount);
        System.out.println("mycount1 = " + mycount1);
        return ret;
    }

    void getSum(int i, int j, int[] num, int[][] dp, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> ret) {
        // for debug and comparing with the other solution
        mycount++;
        if (j == 0) {
            ArrayList<Integer> clone = new ArrayList<Integer>();
            for (int k = path.size() - 1; k >= 0; k--)
                clone.add(path.get(k));
            ret.add(clone);
            return;
        }

        // for debug and comparing with the other solution
        mycount1++;

        if (dp[i][j] > dp[i - 1][j]) {
            path.add(num[i - 1]);
            getSum(i - 1, j - num[i - 1], num, dp, path, ret);
            path.remove(path.size() - 1);
        }
        if(dp[i-1][j] > 0)
            getSum(i - 1, j, num, dp, path, ret);
    }
}
