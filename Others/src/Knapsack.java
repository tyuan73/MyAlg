import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/19/13
 * Time: 12:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class Knapsack {
    public static void main(String[] args) {

        int[] value = {12, 10, 8, 11, 14, 7, 9};
        int[] weight = {4,  6, 5,  7,  3, 1, 6};
        int cap = 18;

        System.out.println("0-1 problem");
        solve01Problem(cap, weight, value);
        System.out.println("unbounded problem - unlimited items");
        solveUnboundedProblem(cap, weight, value);
        System.out.println("exact 0-1 problem");
        solveExact01Problem(cap, weight, value);
    }

    static void solve01Problem(int cap, int[] weight, int[] value) {
        int n = weight.length;
        int[] dp = new int[cap+1];
        for(int i = 0; i < n; i ++) {
            for(int j = cap; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            printArray(dp);
        }

        printArray(dp);
    }

    static void solveExact01Problem(int cap, int[] weight, int[] value) {
        int n = weight.length;
        int[] dp = new int[cap+1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for(int i = 0; i < n; i ++) {
            for(int j = cap; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            printArray(dp);
        }

        printArray(dp);
    }

    static void solveUnboundedProblem(int cap, int[] weight, int[] value) {
        int n = weight.length;
        int[] dp = new int[cap+1];
        for(int i = 0; i < n; i ++) {
            for(int j = weight[i]; j <= cap; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            printArray(dp);
        }

        printArray(dp);
    }

    static void printArray(int[] arr)  {
        if(arr == null || arr.length == 0)
            System.out.println("The array is empty!");
        for(int x : arr)
            System.out.printf("%5d", x);
        System.out.println();
    }
}
