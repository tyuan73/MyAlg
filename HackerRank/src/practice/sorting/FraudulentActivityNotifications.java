package practice.sorting;

/**
 * Created by yuantian on 7/27/17.
 */

import java.util.*;

public class FraudulentActivityNotifications {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] count = new int[201];
        int total = 0;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (i >= k) {
                if (nums[i] >= 2 * median(count, k))
                    total++;
                count[nums[i - k]]--;
            }
            count[nums[i]]++;
        }
        System.out.println(total);
    }

    static private double median(int[] count, int k) {
        int m = (k + 1) / 2;
        int c = 0, i = 0, i1 = -1;
        while (i <= 200) {
            c += count[i];
            if (c >= m) {
                if ((k & 1) == 1 || c > m) return (double) i;
                i1 = i;
                while (count[++i] == 0) {
                }
                return (i1 + i) / 2.0;
            }
            i++;
        }
        return 0.0;
    }
}
