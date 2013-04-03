/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/6/13
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class MaximAndRestaurant {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();
        int p = in.nextInt();

        Arrays.sort(a);
        int[] sum = new int[n + 1];
        int last = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + a[i];
            if (sum[i + 1] > p) {
                last = i - 1;
                break;
            }
        }

        int[] dp = new int[n + 1];
        dp[last + 1] = n - 1 - last;
        //int curLast = last;
        for (int i = last; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int x = a[i];
                a[i] = a[j];
                a[j] = x;

                for (int k = i; k <= j; k++) {
                    sum[k + 1] = sum[k] + a[k];
                    if (sum[k + 1] <= p) last = k;
                }

                dp[i + 1] += n - 1 - last;
            }
            // copy the max ele (a[i]) to the end, and shift the rest to left
            int y = a[i];
            for (int j = i; j < n - 1; j++)
                a[j] = a[j + 1];
            a[n - 1] = y;
        }

        for (int x : dp)
            System.out.printf("%5d", x);
        System.out.println();

        double ret = 0;
        long base = n;
        for (int i = 1; i < n; i++) {
            ret += ((double) dp[i]) / base;
            base *= n - i;
        }
        System.out.println(ret);
    }
}
